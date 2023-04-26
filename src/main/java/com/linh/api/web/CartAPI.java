package com.linh.api.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.linh.model.*;
import com.linh.utils.MoneyFormatUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.linh.dto.CreateCartRequest;
import com.linh.service.ICartItemService;
import com.linh.service.ICartService;
import com.linh.service.ICityService;
import com.linh.service.ICountryService;
import com.linh.service.IProductService;
import com.linh.service.IUserService;

@RestController
@AllArgsConstructor
@Slf4j
public class CartAPI {

	private final ICountryService countryService;
	private final ICityService cityService;
	private final IUserService userService;
	private final ICartService cartService;
	private final ICartItemService cartDetailService;
	private final IProductService fastFoodService;

	@PostMapping(value = "/freshfood/bill/add")
	public void saveOrder(@RequestBody CreateCartRequest request, HttpServletRequest req) {
		User user = userService.getCurrentLoginUser();
		Address address = Address.builder()
				.cityId(cityService.findById(request.getCityId()).getId())
				.countryId(countryService.findOneById(request.getCountryId()).getId())
				.fullAddress(request.getDeliveryAddress())
				.createTime(new Date())
				.type("DELIVERY ADDRESS")
				.build();

		if (user != null){
			List<Cart> carts = cartService.findByUser(user);
			Cart currentCart = carts.get(carts.size() - 1);
			currentCart.setAddress(address);
			// Set cart for address
			address.setCart(currentCart);
			//Update current cart info
			currentCart.setReceiverName(request.getReceiverName());
			currentCart.setReceiverPhoneNumber(request.getReceiverPhone());
			currentCart.setReceiverEmail(request.getReceiverEmail());
			currentCart.setOrderTime(new Date());
			currentCart.setTotalPrice((float) currentCart.getCartItems().stream().mapToInt(cItem -> cItem.getProduct().getPrice() * cItem.getQuantity()).sum());
			currentCart.setStatus("UNSENT");
			cartService.save(currentCart);
			// Add new Cart
			cartService.save(Cart.builder()
					.user(user)
					.status("UNSENT")
					.build()
			);
		}else{
			Cart newCart = Cart.builder()
					.address(address)
					.receiverName(request.getReceiverName())
					.receiverPhoneNumber(request.getReceiverPhone())
					.receiverEmail(request.getReceiverEmail())
					.orderTime(new Date())
					.status("UNSENT")
					.build();
			address.setCart(newCart);
			newCart = cartService.save(newCart);
			HttpSession session = req.getSession();
			float totalPrice = 0;
			Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			for (Entry<Integer, Integer> item : cart.entrySet()) {
				CartItem cartDetail = new CartItem();
				cartDetail.setProduct(fastFoodService.findById(item.getKey()));
				cartDetail.setQuantity(item.getValue());
				cartDetail.setCart(newCart);
				cartDetailService.save(cartDetail);
				totalPrice += cartDetail.getProduct().getPrice() * cartDetail.getQuantity();
			}
			newCart.setTotalPrice(totalPrice);
			cartService.save(newCart);
		}
	}

	@GetMapping(path = "/freshfood/bill/assignToStaff/{cartId}/{staffId}")
	public Map<String, String> assignOrderToStaff(@PathVariable Integer cartId, @PathVariable Integer staffId){
		Map<String, String> result = new LinkedHashMap<>();
		try {
			cartService.assignToStaff(cartId, staffId);
			result.put("success", "success");
		}catch (Exception e){
			result.put("success", "success");
		}
		return result;
	}
	
	@GetMapping(value = "/freshfood/bill/all/{type}")
	public List<Map<String, String>> findAllOrder(@PathVariable String type){
		List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
		List<Cart> carts = cartService.findAll(type);
		for (int i = 0; i < carts.size(); i++) {
			Map<String, String> cartItems = new LinkedHashMap<String, String>();
			cartItems.put("name", carts.get(i).getReceiverName());
			cartItems.put("phone", carts.get(i).getReceiverPhoneNumber());
			cartItems.put("bill-id", carts.get(i).getId()+"");
			List<CartItem> cartDetails =  cartDetailService.findByCart(carts.get(i));
			Integer tongtien = 0;
            for (CartItem item: cartDetails) {
				tongtien += item.getProduct().getPrice() * item.getQuantity();
			}			
			cartItems.put("tongtien", MoneyFormatUtil.format(tongtien));
			cartItems.put("time", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(carts.get(i).getOrderTime()));
			maps.add(cartItems);
		}
		return maps;
	}
	
	@GetMapping(value = "/freshfood/billitem/{billid}")
	public List<Map<String, String>> findAllOrderItems(@PathVariable("billid") Integer billid){
		List<Map<String, String>> res = new ArrayList<>();
		List<CartItem> cartDetails =  cartDetailService.findByCart(cartService.findOneById(billid));
		for (CartItem item : cartDetails) {
			Map<String, String> a = new LinkedHashMap<String, String>();
			Product p = item.getProduct();
			a.put("image", p.getProductExtraImagePath1());
			a.put("name",p.getName());
			a.put("price", p.getPriceCurrency());
			a.put("solg", item.getQuantity()+"");
			a.put("tonggia", (item.getQuantity() * p.getPrice())+"");
			a.put("totalMoney", MoneyFormatUtil.format(item.getQuantity() * p.getPrice()));
		    res.add(a);
		}
		return res;
	}
}
