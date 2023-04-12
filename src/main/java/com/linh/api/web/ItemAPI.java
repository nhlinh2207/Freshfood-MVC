package com.linh.api.web;

import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.linh.model.CartItem;
import com.linh.model.Product;
import com.linh.service.ICartItemService;
import com.linh.utils.MoneyFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.linh.model.Cart;
import com.linh.model.User;
import com.linh.service.ICartService;
import com.linh.service.IProductService;
import com.linh.service.IUserService;

@RestController
@AllArgsConstructor
public class ItemAPI {

	private final IProductService productService;
	private final IUserService userService;
	private final ICartService cartService;
	private final ICartItemService cartDetailService;
        
	@GetMapping(value = "/freshfood/cart/total")
	 public Map<String, String> getTotalQuantity(HttpServletRequest request){
		Map<String, String> aMap = new HashMap<String, String>();
		User user = userService.getCurrentLoginUser();
		int total = 0;
        if (user != null) {
			List<Cart> carts = cartService.findByUser(user);
			Cart currentCart = carts.get(carts.size()-1);
        	total = currentCart.getCartItems().size();
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
    		total = cart == null ? 0 : cart.size();
		}
        aMap.put("total", Integer.toString(total));
        return aMap;
	}
	
	@GetMapping(value = "/freshfood/cart/all")
	public List<Map<String, String>> findAllItemsByUser(HttpServletRequest request){
		User user = userService.getCurrentLoginUser();
        List<Map<String, String>> cartDetails = new ArrayList<Map<String,String>>();
		if (user != null) {
			List<Cart> carts = cartService.findByUser(user);
			Cart currentCart = carts.get(carts.size()-1);
			 for (CartItem c : currentCart.getCartItems()) {
					Map<String, String> i = new HashMap<String, String>();
					i.put("image", c.getProduct().getProductExtraImagePath1());
					i.put("name", c.getProduct().getName());
					i.put("price", MoneyFormatUtil.format(c.getProduct().getPrice()));
					i.put("quantity",c.getQuantity().toString());
					i.put("totalprice", Integer.toString(c.getProduct().getPrice()*c.getQuantity()));
					i.put("totalMoney", MoneyFormatUtil.format(c.getProduct().getPrice()*c.getQuantity()));
					i.put("id",c.getId().toString());
					i.put("proid", c.getProduct().getId().toString());
					i.put("mainimg", c.getProduct().getProductImagePath());
					cartDetails.add(i);
			}
		}else {
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {
				return cartDetails;
			}
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
    		int id = 0;
    		for (Entry<Integer, Integer> item: cart.entrySet()) {
				Product p = productService.findById(item.getKey());
				Map<String, String> i = new HashMap<String, String>();
				i.put("image", p.getProductExtraImagePath1());
				i.put("name", p.getName());
				i.put("price", MoneyFormatUtil.format(p.getPrice()));
				i.put("quantity",item.getValue()+"");
				i.put("totalprice", Integer.toString(p.getPrice() * item.getValue()));
				i.put("totalMoney", MoneyFormatUtil.format(p.getPrice() * item.getValue()));
				i.put("id", id+"");
				id++;
				i.put("proid", p.getId()+"");
				i.put("mainimg", p.getProductImagePath());
				cartDetails.add(i);
			}
		}
       
		return cartDetails;
	}
	
	@PostMapping(value = "/freshfood/cart/add")
	public Map<String, String> saveCart(HttpServletRequest request) {
		
		String fastFoodId = request.getParameter("pro-id");
		String qu = request.getParameter("quantity");
		Map<String, String> a = new HashMap<String, String>();
		
		a.put("id", fastFoodId);
		a.put("quantity", qu);
		
		Product fastFood = productService.findById(Integer.parseInt(fastFoodId));
		User userEntity = userService.getCurrentLoginUser();
		
		if (fastFood.getQuantity() < Integer.parseInt(qu)) {
			a.put("error", "Số lượng bạn đặt quá số lượng còn trong kho !");
		    return a;
		}
		if(userEntity != null) {
			a.put("success", "Bạn đã thêm "+fastFood.getName()+" thành công !");
			List<Cart> carts = cartService.findByUser(userEntity);
			Cart currentCart = carts.get(carts.size()-1);
			for (CartItem item : currentCart.getCartItems()){
				if (item.getProduct() == fastFood){
					item.setQuantity(Integer.parseInt(qu));
					item.setTotalPrice(Integer.parseInt(qu) * fastFood.getPrice());
					cartDetailService.save(item);
					a.put("total", currentCart.getCartItems().size()+"");
					return a;
				}
			}
			CartItem cartDetail = CartItem.builder()
					.cart(currentCart)
					.product(fastFood)
					.quantity(Integer.parseInt(qu))
					.orderTime(new Date())
					.totalPrice(Integer.parseInt(qu) * fastFood.getPrice())
					.build();
			cartDetail = cartDetailService.save(cartDetail);
			currentCart.getCartItems().add(cartDetail);
			a.put("total", cartDetail.getCart().getCartItems().size()+"");
			return a;
		}else {
			HttpSession session = request.getSession();
			Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			if (cart == null) {
				cart = new LinkedHashMap<>();
			}
			cart.put(Integer.valueOf(fastFoodId), Integer.valueOf(qu));
			session.setAttribute("cart", cart);
		    a.put("success", "Bạn đã thêm "+fastFood.getName()+" thành công !");
		    a.put("total", cart.size()+"");
			return a;
		}
	}
	
	@DeleteMapping(value = "/freshfood/cart/delete/{id}")
	public Map<String, String> deleteCart(@PathVariable("id") Integer id, HttpServletRequest request) {
		Map<String, String> a = new HashMap<String, String>();
		User user = userService.getCurrentLoginUser();
		if (user != null) {
			List<Cart> carts = cartService.findByUser(user);
			Cart currentCart = carts.get(carts.size()-1);
			CartItem c = cartDetailService.findById(id);
			String name = c.getProduct().getName();
			a.put("success", "Bạn đã xóa "+name+" khỏi giỏ hàng !");
			cartDetailService.delete(id);
			int total = currentCart.getCartItems().size();
			a.put("total", total+"");
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			int index = 0;
			for (Entry<Integer, Integer> e: cart.entrySet()) {
				if (index == id) {
					a.put("success","Bạn đã xóa "+productService.findById(e.getKey()).getName()+" khỏi giỏ hàng !");
				    cart.remove(e.getKey());
				    break;
				}
				index++;
			}
		    session.setAttribute("cart", cart);
		    int total = cart.size();
			a.put("total", total+"");
		}
		
		return a;
	}
	
	@PutMapping(value = "/freshfood/cart/update/{id}/{val}")
	public Map<String, String> updateCart(@PathVariable("id") Integer id,
			                          @PathVariable("val") Integer val,
			                          HttpServletRequest request){
		Map<String, String> aMap = new HashMap<String, String>();
		if (userService.getCurrentLoginUser()!= null) {
			CartItem cartDetail = cartDetailService.findById(id);
			if(val > cartDetail.getProduct().getQuantity()) {
				aMap.put("error", "Quá số lượng hco phép !");
			}else {
				aMap.put("success", "Cập nhật thành công !");
				cartDetail.setQuantity(val);;
				cartDetailService.save(cartDetail);
			}
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
		    int index = 0;
		    for (Entry<Integer, Integer> e: cart.entrySet()) {
				if (id == index) {
					 if(val > productService.findById(e.getKey()).getQuantity()) {
						aMap.put("error", "Quá số lượng hco phép !");
					 }else {
						aMap.put("success", "Cập nhật thành công !");
						cart.put(e.getKey(), val);
					}
					break;
				}
				index++;
			}
		    session.setAttribute("cart", cart);
		}
		
		return aMap;
	}
}
