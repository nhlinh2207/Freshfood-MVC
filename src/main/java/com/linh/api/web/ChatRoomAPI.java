package com.linh.api.web;

import com.linh.model.ChatMessage;
import com.linh.model.ChatRoom;
import com.linh.model.User;
import com.linh.service.IChatMessageService;
import com.linh.service.IChatRoomService;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/freshfood")
public class ChatRoomAPI {

    private final IUserService userService;
    private final IChatRoomService chatRoomService;

    @GetMapping(path = "/chatroom/findByUser")
    public Integer findChatRoomByUser(Principal principal){
        Integer userId = userService.findByEmail(userService.getCurrentLoginUser().getEmail()).getId();
        return chatRoomService.findByUserId(userId).getId();
    }
}
