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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final IChatMessageRepo messageRepo;

    @Override
    public void appendInstantMessageToConversations(ChatMessage message) throws JsonProcessingException {
        if (message.isPublic()) {

            messageRepo.save(ChatMessage.builder()
                        .chatRoomId(message.getChatRoomId())
                        .content(message.getContent())
                        .fromUser(message.getFromUser())
                        .username(message.getFromUser())
                        .senderType(message.getSenderType())
                        .createTime(new Date())
                        .build());
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
    public List<ChatMessage> findByChatRoomId(Integer chatRoomId) {
        return messageRepo.findByChatRoomId(chatRoomId);
    }
}
