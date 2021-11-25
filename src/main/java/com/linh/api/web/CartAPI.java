package com.linh.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;
import com.linh.service.InCartItemService;
import com.linh.service.InProductService;
import com.linh.service.InUserService;

@RestController
public class CartAPI {
	
	@Autowired
	private InProductService productservice;
	
	@Autowired
	private InUserService userservice;
	
	@Autowired
	private InCartItemService cartItemService;
        
	@GetMapping(value = "/freshfood/cart/total")
	 public Map<String, String> getTotal(HttpServletRequest request){
		Map<String, String> aMap = new HashMap<String, String>();
		UserEntity userEntity = userservice.getLoggingInUsser();
		int total = 0;
        if (userEntity != null) {
        	total = cartItemService.findByUser(userEntity).size();
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
    		if (cart == null) {
				total = 0;
			}else {
				total = cart.size();
			}
		}
		
        aMap.put("total", Integer.toString(total));
        return aMap;
	}
	
	@GetMapping(value = "/freshfood/cart/all")
	public List<Map<String, String>> findall(HttpServletRequest request){
		UserEntity userEntity = userservice.getLoggingInUsser();
        List<Map<String, String>> all = new ArrayList<Map<String,String>>();
		if (userEntity != null) {
			 for (CartItemEntity c: cartItemService.findByUser(userEntity)) {
					Map<String, String> i = new HashMap<String, String>();
					i.put("image", c.getProduct().getProductExtraImagePath1());
					i.put("name", c.getProduct().getName());
					i.put("price", c.getProduct().getPrice().toString());
					i.put("quantity",c.getQuantity().toString());
					i.put("totalprice", Long.toString(c.getProduct().getPrice()*c.getQuantity()));
					i.put("id",c.getId().toString());
					i.put("proid", c.getProduct().getId().toString());
					i.put("mainimg", c.getProduct().getProductImagePath());
					all.add(i);
			}
		}else {
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {
				return all;
			}
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
    		int id = 0;
    		for (Entry<Integer, Integer> item: cart.entrySet()) {
				ProductEntity p = productservice.findOneById(item.getKey());
				Map<String, String> i = new HashMap<String, String>();
				i.put("image", p.getProductExtraImagePath1());
				i.put("name", p.getName());
				i.put("price", p.getPrice().toString());
				i.put("quantity",item.getValue()+"");
				i.put("totalprice", Long.toString(p.getPrice() * item.getValue()));
				i.put("id", id+"");
				id++;
				i.put("proid", p.getId()+"");
				i.put("mainimg", p.getProductImagePath());
				all.add(i);
			}
		}
       
		return all;
	}
	
	@PostMapping(value = "/freshfood/cart/add")
	public Map<String, String> cart(HttpServletRequest request) {
		
		String idString = request.getParameter("pro-id");
		String qu = request.getParameter("quantity");
		Map<String, String> a = new HashMap<String, String>();
		
		a.put("id", idString);
		a.put("quantity", qu);
		
		ProductEntity product = productservice.findOneById(Integer.parseInt(idString));
		UserEntity userEntity = userservice.getLoggingInUsser();
		
		if (product.getQuantity() < Integer.parseInt(qu)) {
			a.put("error", "Số lượng bạn đặt quá số lượng còn trong kho !");
		    return a;
		}
		
		if(userEntity != null) {
			a.put("success", "Bạn đã thêm "+product.getName()+" thành công !");
			cartItemService.save(product,userservice.getLoggingInUsser(), Integer.parseInt(qu));
			int total = cartItemService.findByUser(userEntity).size();
			a.put("total", Integer.toString(total));
		}else {
			HttpSession session = request.getSession();
			Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			if (cart == null) {
				cart = new LinkedHashMap<Integer, Integer>();
			}
			cart.put(Integer.valueOf(idString), Integer.valueOf(qu));
			session.setAttribute("cart", cart);
		    a.put("success", "Bạn đã thêm "+product.getName()+" thành công !");
		    a.put("total", cart.size()+"");
		}
		
		return a;
	}
	
	@DeleteMapping(value = "/freshfood/cart/delete/{id}")
	public Map<String, String> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		Map<String, String> a = new HashMap<String, String>();
		UserEntity userEntity = userservice.getLoggingInUsser();
		if (userEntity != null) {
			CartItemEntity c = cartItemService.findOneById(id);
			String name = c.getProduct().getName();
			a.put("success", "Bạn đã xóa "+name+" khỏi giỏ hàng !");
			cartItemService.delete(id);
			int total = cartItemService.findByUser(userEntity).size();
			a.put("total", total+"");
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			int index = 0;
			for (Entry<Integer, Integer> e: cart.entrySet()) {
				if (index == id) {
					a.put("success","Bạn đã xóa "+productservice.findOneById(e.getKey()).getName()+" khỏi giỏ hàng !");
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
	public Map<String, String> update(@PathVariable("id") Integer id,
			                          @PathVariable("val") Integer val,
			                          HttpServletRequest request){
		Map<String, String> aMap = new HashMap<String, String>();
		if (userservice.getLoggingInUsser() != null) {
			CartItemEntity cartItemEntity = cartItemService.findOneById(id);
			if(val > cartItemEntity.getProduct().getQuantity()) {
				aMap.put("error", "Quá số lượng hco phép !");
			}else {
				aMap.put("success", "Cập nhật thành công !");
				cartItemEntity.setQuantity(val);
				cartItemService.save(cartItemEntity.getProduct(), cartItemEntity.getUser(), cartItemEntity.getQuantity());
			}
		}else {
			HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
		    int index = 0;
		    for (Entry<Integer, Integer> e: cart.entrySet()) {
				if (id == index) {
					 if(val > productservice.findOneById(e.getKey()).getQuantity()) {
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
	
	@PostMapping(value = "/freshfood/cart/order")
	public Integer[] order(@RequestBody Integer ids[]) {
	     for(Integer id: ids) {
	    	System.out.println(id+" ");
	     }
	     return ids;
	}
}
