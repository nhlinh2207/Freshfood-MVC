package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;

public interface InCartItemRes extends JpaRepository<CartItemEntity, Integer> {
     
	List<CartItemEntity> findByUser(UserEntity user);      
	
	CartItemEntity findOneByProductAndUser(ProductEntity product, UserEntity user);

	CartItemEntity findOneById(Integer id);
	
	@Query("DELETE FROM CartItemEntity c WHERE c.user.id = :id")
	@Modifying
	void deleteByUser(Integer id);
}
