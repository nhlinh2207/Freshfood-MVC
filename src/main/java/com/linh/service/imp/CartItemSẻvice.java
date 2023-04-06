package com.linh.service.imp;

import java.util.List;

import com.linh.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.CartItem;
import com.linh.respository.ICartItemRepo;
import com.linh.service.ICartItemService;

@Service
@AllArgsConstructor
public class CartItemSẻvice implements ICartItemService {

	private final ICartItemRepo cartDetailRepository;

	@Override
	public CartItem save(CartItem billDetailEntity) {
		// TODO Auto-generated method stub
		return cartDetailRepository.save(billDetailEntity);
	}

	@Override
	public List<CartItem> findByCart(Cart cart) {
		return cartDetailRepository.findByCart(cart);
	}

	@Override
	public CartItem findById(Integer id) {
		return cartDetailRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
        cartDetailRepository.deleteById(id);
	}

}
