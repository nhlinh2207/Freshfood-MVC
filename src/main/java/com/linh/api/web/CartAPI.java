package com.linh.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	 public Map<String, String> getTotal(){
		Map<String, String> aMap = new HashMap<String, String>();
		UserEntity userEntity = userservice.getLoggingInUsser();
        int total = cartItemService.findByUser(userEntity).size();
        aMap.put("total", Integer.toString(total));
        return aMap;
	}
	
	@GetMapping(value = "/freshfood/cart/all")
	public List<Map<String, String>> findall(){
		UserEntity userEntity = userservice.getLoggingInUsser();
        List<Map<String, String>> all = new ArrayList<Map<String,String>>();
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
		if(product.getQuantity() >= Integer.parseInt(qu)) {
			if(userEntity != null) {
			  a.put("success", "Bạn đã thêm "+product.getName()+" thành công !");
			  cartItemService.save(product,userservice.getLoggingInUsser(),Integer.parseInt(qu));
			  int total = cartItemService.findByUser(userEntity).size();
			  a.put("total", Integer.toString(total));
			}
		}else {
			a.put("error", "Số lượng bạn đặt quá số lượng còn trong kho !");
		}
		return a;
	}
	
	@DeleteMapping(value = "/freshfood/cart/delete/{id}")
	public Map<String, String> delete(@PathVariable("id") Integer id) {
		Map<String, String> a = new HashMap<String, String>();
		UserEntity userEntity = userservice.getLoggingInUsser();
		CartItemEntity c = cartItemService.findOneById(id);
		String name = c.getProduct().getName();
		a.put("success", "Bạn đã xóa "+name+" khỏi giỏ hàng !");
		cartItemService.delete(id);
		int total = cartItemService.findByUser(userEntity).size();
		a.put("total", Integer.toString(total));
		return a;
	}
	
	@PutMapping(value = "/freshfood/cart/update/{id}/{val}")
	public Map<String, String> update(@PathVariable("id") Integer id,
			                          @PathVariable("val") Integer val){
		Map<String, String> aMap = new HashMap<String, String>();
		CartItemEntity cartItemEntity = cartItemService.findOneById(id);
		if(val > cartItemEntity.getProduct().getQuantity()) {
			aMap.put("error", "Quá số lượng hco phép !");
		}else {
			aMap.put("success", "Cập nhật thành công !");
			cartItemEntity.setQuantity(val);
			cartItemService.save(cartItemEntity.getProduct(), cartItemEntity.getUser(), cartItemEntity.getQuantity());
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
