package com.linh.respository;

import java.util.List;

import com.linh.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linh.model.CartItem;

public interface ICartItemRepo extends JpaRepository<CartItem, Integer>{
    
	@Query("SELECT t FROM CartItem t WHERE t.cart = :cart")
	List<CartItem> findByCart(@Param("cart") Cart cart);
}
