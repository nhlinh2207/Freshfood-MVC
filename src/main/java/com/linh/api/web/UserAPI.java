package com.linh.api.web;

import com.linh.dto.SendEmailDTO;
import com.linh.dto.request.ResetPasswordRequest;
import com.linh.respository.IUserRepository;
import com.linh.service.imp.SendEmailService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.linh.model.User;
import com.linh.service.IUserService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class UserAPI {

//	@Value("${spring.mail.username}")
//	private String mailFrom;
	private final IUserService userService;
	private final PasswordEncoder passwordEncoder;
	private final IUserRepository userRepository;
	private final SendEmailService mailService;
	
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

	@PutMapping(path = "/freshfood/send-reset-mail")
	public Map<String, String> sendResetPassMail(@RequestBody ResetPasswordRequest request){
		Map<String, String> result = new LinkedHashMap<>();
		String email = request.getEmail();
//		if (!userService.existsByEmail(email)){
//			result.put("error", "error");
//			return result;
//		}
		User user = userService.findByEmail(email);
		String resetPassToken = RandomString.make(20);
		user.setResetPassToken(resetPassToken);
		userService.update(user);
		//Send mail
		SendEmailDTO emailDTO = SendEmailDTO.builder()
				.from("nguyenhoailinh2207@gmail.com")
				.to(request.getEmail())
				.subject("Freshfood, Reset email !")
				.build();
		mailService.sendResetPassMail(resetPassToken, emailDTO);
		result.put("success", "success");
		return result;
	}
}
