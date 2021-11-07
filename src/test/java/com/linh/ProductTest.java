package com.linh;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.linh.entity.ProductEntity;
import com.linh.respository.InCategoryRes;
import com.linh.respository.InProductRes;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {
 
	@Autowired
	private InProductRes product;
	
	@Autowired
	private InCategoryRes category;
	
	@Test
	@Rollback(false)
	public void createProduct() {
		ProductEntity productEntity = new ProductEntity("Tôm","tom.jpg",Long.valueOf(2000),10, new Date(),category.findOneById(2));
	    product.save(productEntity);
	}
	
	@Test
	public void getCurrency() {
		ProductEntity productEntity = product.findOneById(2031);
		System.out.println(productEntity.getPriceCurrency());
	}
}
