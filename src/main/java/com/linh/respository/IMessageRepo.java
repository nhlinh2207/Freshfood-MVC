package com.linh.respository;

import com.linh.model.Message;
import com.linh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepo extends JpaRepository<Message, Integer> {

    List<Message> findByUser(User user);
}
