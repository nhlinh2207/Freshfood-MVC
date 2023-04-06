package com.linh.service.imp;

import com.linh.model.Message;
import com.linh.model.User;
import com.linh.respository.IMessageRepo;
import com.linh.service.IMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService implements IMessageService {

    private final IMessageRepo messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findByUser(User user) {
        return messageRepository.findByUser(user);
    }

    @Override
    public void delete(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findById(Integer id) {
        return messageRepository.findById(id).get();
    }
}
