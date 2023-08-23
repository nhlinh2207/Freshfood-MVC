package com.linh.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.linh.dto.request.ContactRequest;
import com.linh.dto.request.RegistryRequest;
import com.linh.model.User;

import javax.mail.MessagingException;

public interface IUserService {
	
      boolean existsByEmail(String email);

      User getCurrentLoginUser();

      User registry(RegistryRequest request);
      
      User findByEmail(String email);
      
      User findById(Integer id);
      
      void update(User user);
      
      List<String> getEmails();

      List<User> getFreeStaff();

      List<User> findAll();

      List<User> getAllActiveUsers();

      List<User> getAllStaffs();

      User findByToken(String token);

      void contact(ContactRequest request, User currentUser) throws MessagingException, UnsupportedEncodingException;
}
