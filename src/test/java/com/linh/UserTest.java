package com.linh;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.linh.entity.UserEntity;
import com.linh.respository.InCountryRes;
import com.linh.respository.InRoleRes;
import com.linh.respository.InUserRes;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class UserTest {
      
	@Autowired
	private InUserRes user ;
	
	@Autowired
	private InCountryRes country;
	
	@Autowired
	private InRoleRes role;
	
	
	@Test
	public void save() {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddress("aaaa");
		userEntity.setCity("Hà Nội");
		userEntity.setCountry(country.findOneById(1));
		userEntity.setFullname("linh");
		userEntity.setEmail("linh@gmail.com");
		userEntity.setPhone("0372983207");
		userEntity.setPassword("123456");
		userEntity.setStatus(1);
		userEntity.setProvince("");
		userEntity.getRoles().add(role.findOneByName("ADMIN"));
	    role.findOneByName("ADMIN").getUsers().add(userEntity);
		user.save(userEntity);
	}
	
	@Test
	public void findRoleByEmail() {
	   user.findOneByEmail("nguyenhoailinh2207@gmail.com").getRoles().forEach(
	      i -> System.out.println(i.getName())
	   );
	}
	
	@Test
	public void findByEm() {
		System.out.println(
		   user.findByEmail("nguyenhoailinh2207@gmail.com").stream().findFirst()
		   .get().getPhone()
		);
	}
}
