package com.linh.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linh.model.ChatMessage;
import com.linh.model.ChatRoom;
import com.linh.model.ConnectedUser;
import com.linh.service.IChatMessageService;
import com.linh.service.IChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChatController {

    private final IChatRoomService chatRoomService;
    private final IChatMessageService messageService;

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/chatroom")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.save(chatRoom);
    }


    @RequestMapping("/chat")
    public ModelAndView getRooms() {
        ModelAndView modelAndView = new ModelAndView("web/chat");
        List<ChatRoom> chatRooms = chatRoomService.findAll();
        modelAndView.addObject("chatRooms", chatRooms);
        return modelAndView;
    }

    @RequestMapping("/chatroom/{chatRoomId}")
    public ModelAndView join(@PathVariable String chatRoomId, Principal principal) {
        System.out.println(principal.getName());
        ModelAndView modelAndView = new ModelAndView("chatroom");
        modelAndView.addObject("chatRoom", chatRoomService.findById(Integer.valueOf(chatRoomId)));
        return modelAndView;
    }

    @SubscribeMapping("/connected.users")
    public List<ConnectedUser> listChatRoomConnectedUsersOnSubscribe(SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
        ChatRoom chatRoom = chatRoomService.findById(Integer.valueOf(chatRoomId));
        return new ObjectMapper().readValue(chatRoom.getConnectedUsers(), ArrayList.class);
    }

    @SubscribeMapping("/old.messages")
    public List<ChatMessage> listOldMessagesFromUserOnSubscribe(Principal principal,
                                                                SimpMessageHeaderAccessor headerAccessor) {
        String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
        return messageService.findAllInstantMessagesFor(principal.getName(), chatRoomId);
    }

    @MessageMapping("/send.message")
    public void sendMessage(@Payload ChatMessage instantMessage, Principal principal,
                            SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();
        instantMessage.setFromUser(principal.getName());
        instantMessage.setChatRoomId(Integer.valueOf(chatRoomId));

        if (instantMessage.isPublic()) {
            System.out.println("Public from : "+instantMessage.getFromUser());
            chatRoomService.sendPublicMessage(instantMessage);
        } else {
            System.out.println(instantMessage.getFromUser()+"  -->  "+instantMessage.getToUser());
            chatRoomService.sendPrivateMessage(instantMessage);
        }
    }
}
