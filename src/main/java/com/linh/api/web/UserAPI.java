package com.linh.api.web;

import com.linh.dto.SendEmailDTO;
import com.linh.dto.request.ChangPasswordRequest;
import com.linh.dto.request.ChangeCurrentPasswordRequest;
import com.linh.dto.request.ChangeProfileRequest;
import com.linh.dto.request.ResetPasswordRequest;
import com.linh.model.Address;
import com.linh.respository.IUserRepository;
import com.linh.service.imp.SendEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.linh.model.User;
import com.linh.service.IUserService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
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

	@PutMapping(path = "/freshfood/api/change-profile")
	public Map<String, String> changePròile(@RequestBody ChangeProfileRequest request){
		Map<String, String> res = new LinkedHashMap<>();
		System.out.println(request.getCityId()+" city");
		try{
			User currentUser = userService.findById(request.getId());
			currentUser.setFirstName(request.getFirstName());
			currentUser.setFirstName(request.getMiddleName());
			currentUser.setFirstName(request.getLastName());
			currentUser.setEmail(request.getEmail());
			currentUser.setPhoneNumber(request.getPhone());

			Address oldAddress = currentUser.getAddress();
			oldAddress.setCityId(request.getCityId());
			oldAddress.setCountryId(request.getCountryId());
			oldAddress.setFullAddress(request.getAddress());

			currentUser.setAddress(oldAddress);
			userService.update(currentUser);

			res.put("success", "success");
			return res;
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			res.put("error", "error");
			return res;
		}
	}

	@GetMapping(path = "/freshfood/users/getall")
	public List<Map<String, String>> getAllUsers(){
		List<Map<String, String>> result = new ArrayList<>();
		List<User> allActiveUsers = userService.getAllActiveUsers();
		for (User u : allActiveUsers){
			Map<String, String> item = new LinkedHashMap<>();
			item.put("id", u.getId()+"");
			item.put("fullName", u.getFullName());
			item.put("email", u.getEmail());
			item.put("phoneNumber", u.getPhoneNumber());
			item.put("status", u.getStatus());
			result.add(item);
		}
		return result;
	}

	@PutMapping(path = "/freshfood/api/change-current-password")
	public Map<String, String> changeCurrentPassword(@RequestBody ChangeCurrentPasswordRequest request){
		Map<String, String> res = new LinkedHashMap<>();
		try{
			User currentUser = userService.findById(request.getId());
			if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())){
				res.put("error", "error");
				return res;
			}
			currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
			userService.update(currentUser);
			res.put("success", "success");
			return res;
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			res.put("error", "error");
			return res;
		}
	}

	@PutMapping(path = "/freshfood/change-password")
	public Map<String, String> changePassword(@RequestBody ChangPasswordRequest request){
		Map<String, String> res = new LinkedHashMap<>();
		try{
			User user = userService.findByToken(request.getToken());
			String newPassword = passwordEncoder.encode(request.getNewPassword());
			user.setPassword(newPassword);
			userService.update(user);
			res.put("success", "success");
			return res;
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			res.put("error", "error");
			return res;
		}
	}

	@GetMapping(path = "/freshfood/staffs/getall")
	public List<Map<String, String>> getAllStaffs(){
		List<Map<String, String>> result = new ArrayList<>();
		List<User> allActiveStaffs = userService.getAllStaffs();
		for (User u : allActiveStaffs){
			Map<String, String> item = new LinkedHashMap<>();
			item.put("id", u.getId()+"");
			item.put("fullName", u.getFullName());
			item.put("email", u.getEmail());
			item.put("phoneNumber", u.getPhoneNumber());
			item.put("status", u.getStatus());
			result.add(item);
		}
		return result;
	}

	@DeleteMapping(path = "/freshfood/users/delete/{id}")
	public Map<String, String> deleteUser(@PathVariable Integer id){
		Map<String, String> result = new LinkedHashMap<>();
		try{
			User user = userService.findById(id);
			user.setStatus("INACTIVE");
			userService.update(user);
			result.put("message" , "success");
			return result;
		}catch (Exception e){
			result.put("message" , "error");
			return result;
		}
	}
}
