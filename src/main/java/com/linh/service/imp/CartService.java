package com.linh.service.imp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.linh.respository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.Cart;
import com.linh.model.User;
import com.linh.respository.ICartRepo;
import com.linh.service.ICartService;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

	private final ICartRepo cartRepository;
	private final IUserRepository userRepository;

	@Override
	public List<Cart> findByUser(User user) {
		// TODO Auto-generated method stub
		return cartRepository.findByUser(user);
	}

	@Override
	public Cart save(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> findAll(String type) {
		if (type.equals("unsent"))
		   return cartRepository.findAll().stream().filter(c -> c.getStatus().equals("UNSENT") && c.getReceiverName() != null).collect(Collectors.toList());
	    else
			return cartRepository.findAll().stream().filter(c -> c.getStatus().equals("SENT")).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		cartRepository.deleteById(id);
	}

	@Override
	public Cart findOneById(Integer id) {
		// TODO Auto-generated method stub
		return cartRepository.findById(id).get();
	}

	@Override
	public void deleteByUser(Integer id) {
		cartRepository.deleteByUser(id);
	}

	@Override
	public void assignToStaff(Integer cartId, Integer staffId) {
		Cart cart = cartRepository.findById(cartId).get();
		User staff = userRepository.findById(staffId).get();
		cart.setStaff(staff);
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> findByStaff(String type, User staff) {
		if (type.equals("unsent"))
			return cartRepository.findAll().stream().filter(c -> c.getStatus().equals("UNSENT") && c.getReceiverName() != null && c.getStaff() == staff).collect(Collectors.toList());
		else
			return cartRepository.findAll().stream().filter(c -> c.getStatus().equals("SENT") && c.getStaff() == staff).collect(Collectors.toList());
	}
}
