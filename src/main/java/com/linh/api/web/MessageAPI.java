package com.linh.api.web;

import com.linh.model.Message;
import com.linh.model.User;
import com.linh.service.IUserService;
import com.linh.service.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MessageAPI {

    private final IUserService userService;
    private final IMessageService messageService;

    @GetMapping(value = "/freshfood/message/all")
    public List<Map<String, String>> getByUser(){

        SimpleDateFormat smf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        User user = userService.getCurrentLoginUser();
        List<Map<String, String>> result = new ArrayList<Map<String,String>>();
        List<Message> messages = messageService.findByUser(user);
        for (Message m : messages) {
            Map<String, String> item = new HashMap<>();
            item.put("id", m.getId()+"");
            item.put("title", m.getTitle());
            item.put("content", m.getContent());
            item.put("time", smf.format(m.getCreateTime()));
            result.add(item);
        }
        return result;
    }

    @DeleteMapping(value = "/freshfood/message/delete/{id}")
    public String deleteMessage(@PathVariable("id") Integer id){
        try {
            messageService.delete(id);
            return "OK";
        }catch (Exception e){
            return "Failed";
        }
    }
}
