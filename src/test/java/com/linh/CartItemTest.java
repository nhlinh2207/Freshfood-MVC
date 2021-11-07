package com.linh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;
import com.linh.respository.InCartItemRes;
import com.linh.respository.InProductRes;
import com.linh.respository.InUserRes;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CartItemTest {
    
	@Autowired
	private InCartItemRes cart;
	
	@Autowired
	private InUserRes user;
	
	@Autowired
	private InProductRes pro;
	
    @Test	
    @Rollback(false)
	public void savecart() {
		 UserEntity u = user.findOneByEmail("nguyenhoailinh2207@gmail.com");
		 ProductEntity p = pro.findOneById(2029);
		 
		 CartItemEntity cartItemEntity = new CartItemEntity();
		 cartItemEntity.setProduct(p);
		 cartItemEntity.setUser(u);
		 cartItemEntity.setQuantity(10);
		 cart.save(cartItemEntity);
	}
    
    @Test
    public void findbyproanduer() {
    	UserEntity u = user.findOneByEmail("nguyenhoailinh2207@gmail.com");
		ProductEntity p = pro.findOneById(2029);
		CartItemEntity cartItemEntity = cart.findOneByProductAndUser(p, u);
		if(cartItemEntity == null) {
			System.out.println("không có");
		}
    }
    
    @Test
    public void findOneById() {
    	System.out.println(cart.findOneById(1084).getProduct().getName());
    }
}
