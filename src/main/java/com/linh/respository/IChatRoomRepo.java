package com.linh.respository;

import com.linh.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChatRoomRepo extends JpaRepository<ChatRoom, Integer> {

    ChatRoom findByUserId(Integer userId);
}
