package com.linh.api.web;

import com.linh.respository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.linh.model.User;
import com.linh.service.IUserService;

@RestController
@AllArgsConstructor
public class UserAPI {
      
	private final IUserService userService;
	private final PasswordEncoder passwordEncoder;
	private final IUserRepository userRepository;
	
	@PostMapping(value = "/freshfood/user/update")
	public void updateUserInfo(@RequestBody User user) {
		System.out.println(user.getFullName()+ " " + user.getEmail());
	}

	@GetMapping(value = "/freshfood/user/test/{staffId}/{pass}")
	public void testChangePass(@PathVariable Integer staffId,@PathVariable String pass){
		User user = userService.findById(staffId);
		user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
	}
}
