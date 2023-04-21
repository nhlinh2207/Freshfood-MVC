package com.linh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linh.model.ChatMessage;

import java.util.List;

public interface IChatMessageService {
    void appendInstantMessageToConversations(ChatMessage message) throws JsonProcessingException;
    List<ChatMessage> findAllInstantMessagesFor(String username, String chatRoomId);

}
