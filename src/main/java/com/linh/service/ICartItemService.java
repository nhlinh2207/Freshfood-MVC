package com.linh.service;

import java.util.List;

import com.linh.model.Cart;
import com.linh.model.CartItem;

public interface ICartItemService {

    CartItem save(CartItem cartItem);

    List<CartItem> findByCart(Cart cart);

    CartItem findById(Integer id);

    void delete(Integer id);
}
