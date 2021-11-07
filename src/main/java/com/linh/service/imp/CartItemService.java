package com.linh.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;
import com.linh.respository.InCartItemRes;
import com.linh.service.InCartItemService;

@Service
public class CartItemService implements InCartItemService {
	
	@Autowired
	private InCartItemRes cartitem;

	@Override
	public List<CartItemEntity> findByUser(UserEntity user) {
		// TODO Auto-generated method stub
		return cartitem.findByUser(user);
	}

	@Override
	@Transactional
	public CartItemEntity save(ProductEntity product, UserEntity user, Integer quantity) {
		// TODO Auto-generated method stub
		CartItemEntity oldcart = cartitem.findOneByProductAndUser(product, user);
		if(oldcart != null) {
			oldcart.setQuantity(quantity);
		}else {
			oldcart = new CartItemEntity();
			oldcart.setUser(user);
			oldcart.setProduct(product);
			oldcart.setQuantity(quantity);
		}
		return cartitem.save(oldcart);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		cartitem.deleteById(id);
	}

	@Override
	public CartItemEntity findOneById(Integer id) {
		// TODO Auto-generated method stub
		return cartitem.findOneById(id);
	}

	@Override
	@Transactional
	public void deleteByUser(Integer id) {
		// TODO Auto-generated method stub
		cartitem.deleteByUser(id);
	}
}
