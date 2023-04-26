package com.linh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linh.model.ChatMessage;
import com.linh.model.ChatRoom;
import com.linh.model.ConnectedUser;

import java.util.List;

public interface IChatRoomService {

    ChatRoom save(ChatRoom chatRoom);
    List<ChatRoom> findAll();
    ChatRoom findByUserId(Integer userId);
    List<ChatRoom> findByAdminId(Integer adminId);
    ChatRoom findById(Integer id);
    ChatRoom join(Integer chatRoomId, ConnectedUser user);
    ChatRoom leave(Integer chatRoomId, ConnectedUser user) throws JsonProcessingException;
    void sendPublicMessage(ChatMessage message) throws JsonProcessingException;
    void sendPrivateMessage(ChatMessage message) throws JsonProcessingException;
    void loadOldMessage(Integer chatRoomId, String username);
}
