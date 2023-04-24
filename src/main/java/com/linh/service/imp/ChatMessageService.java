package com.linh.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linh.model.ChatMessage;
import com.linh.model.ChatRoom;
import com.linh.respository.IChatMessageRepo;
import com.linh.respository.IChatRoomRepo;
import com.linh.service.IChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final IChatMessageRepo messageRepo;
    private final IChatRoomRepo chatRoomRepo;

    @Override
    public void appendInstantMessageToConversations(ChatMessage message) throws JsonProcessingException {
        if (message.isPublic()) {
            ChatRoom chatRoom = chatRoomRepo.findById(message.getChatRoomId()).get();

            List<LinkedHashMap<String, String>> connectedUsers = new ObjectMapper().readValue(chatRoom.getConnectedUsers(), ArrayList.class);
            for (LinkedHashMap<String, String> user : connectedUsers){
                messageRepo.save(ChatMessage.builder()
                        .chatRoomId(message.getChatRoomId())
                        .content(message.getContent())
                        .fromUser(message.getFromUser())
                        .username(user.get("username"))
                        .build()
                );
            }
        } else {
            messageRepo.save(ChatMessage.builder()
                    .chatRoomId(message.getChatRoomId())
                    .content(message.getContent())
                    .fromUser(message.getFromUser())
                    .toUser(message.getToUser())
                    .username(message.getFromUser())
                    .build()
            );

            messageRepo.save(ChatMessage.builder()
                    .chatRoomId(message.getChatRoomId())
                    .content(message.getContent())
                    .fromUser(message.getFromUser())
                    .toUser(message.getToUser())
                    .username(message.getToUser())
                    .build()
            );
        }
    }

    @Override
    public List<ChatMessage> findAllInstantMessagesFor(String username, String chatRoomId) {
        return messageRepo.findByUsernameAndChatRoomId(username, Integer.valueOf(chatRoomId));
    }
}
