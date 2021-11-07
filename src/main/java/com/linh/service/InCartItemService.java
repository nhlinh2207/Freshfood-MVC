package com.linh.service;

import java.util.List;

import javax.transaction.Transactional;

import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;

public interface InCartItemService {
	
      List<CartItemEntity> findByUser(UserEntity userEntity);
      
      @Transactional
      CartItemEntity save(ProductEntity product, UserEntity user, Integer quantity);
      
      CartItemEntity findOneById(Integer id);
      
      @Transactional
      void delete(Integer id);
      
      void deleteByUser(Integer id);
}
