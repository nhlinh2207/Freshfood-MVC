package com.linh.service;

import java.util.List;

import com.linh.dto.request.RegistryRequest;
import com.linh.model.User;

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
}
