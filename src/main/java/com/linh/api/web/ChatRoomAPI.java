package com.linh.api.web;

import com.linh.service.IChatRoomService;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/freshfood")
public class ChatRoomAPI {

    private final IUserService userService;
    private final IChatRoomService chatRoomService;

    @GetMapping(path = "/chatroom/findByUser")
    public Integer findChatRoomByUser(Principal principal){
        System.out.println(principal.getName());
        Integer userId = userService.findByEmail(userService.getCurrentLoginUser().getEmail()).getId();
        return chatRoomService.findByUserId(userId).getId();
    }
}
