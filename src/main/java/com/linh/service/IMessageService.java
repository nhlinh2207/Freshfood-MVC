package com.linh.service;

import com.linh.model.Message;
import com.linh.model.User;

import java.util.List;

public interface IMessageService {

    Message save(Message message);

    List<Message> findByUser(User user);

    void delete(Integer id);

    Message findById(Integer id);
}
