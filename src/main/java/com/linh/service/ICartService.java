package com.linh.service;

import java.util.List;

import com.linh.model.Cart;
import com.linh.model.User;

public interface ICartService {
	
      List<Cart> findByUser(User userEntity);
      
      Cart save(Cart cart);

      List<Cart> findAll(String type);
      
      Cart findOneById(Integer id);
      
      void delete(Integer id);
      
      void deleteByUser(Integer id);

      void assignToStaff(Integer cartId, Integer staffId);

      List<Cart> findByStaff(String type, User staff);
}
