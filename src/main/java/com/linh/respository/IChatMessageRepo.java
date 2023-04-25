package com.linh.respository;

import com.linh.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChatMessageRepo extends JpaRepository<ChatMessage, Integer> {

    List<ChatMessage> findByChatRoomId(Integer chatRoomId);

}
