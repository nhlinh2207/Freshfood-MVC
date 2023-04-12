package com.linh.api.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.linh.service.IProductService;

@RestController
@AllArgsConstructor
public class ProductAPI {

	private final IProductService productService;
	
	@DeleteMapping("/freshfood/product/xoa/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		productService.delete(id);
		return id.toString();
	}
}
