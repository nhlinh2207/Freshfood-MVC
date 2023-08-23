package com.linh.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.linh.dto.mapper.Mapper;
import com.linh.dto.request.ContactRequest;
import com.linh.dto.request.RegistryRequest;
import com.linh.model.*;
import com.linh.respository.ICitytRepo;
import com.linh.respository.ICountryRepo;
import com.linh.respository.IRoleRepository;
import com.linh.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.linh.respository.IUserRepository;
import com.linh.service.IUserService;
import com.linh.utils.UserPrincipal;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
	private final IUserRepository userRepository;
	private final ICitytRepo cityRepository;
	private final ICountryRepo countryRepository;
	private final IRoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final ICartService cartService;
	private final JavaMailSender mailSender;

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User registry(RegistryRequest req) {
		User user = Mapper.convertToUserEntity(req);
		//Tạo địa chỉ
		Address address = Address.builder()
				.fullAddress(req.getAddress())
				.countryId(countryRepository.findById(req.getCountryId()).get().getId())
				.cityId(cityRepository.findById(req.getCountryId()).get().getId())
				.createTime(new Date())
				.user(user)
				.type("RESIDENT ADDRESS")
				.build();

		user.setAddress(address);
		user.setPassword(passwordEncoder.encode(req.getPassword()));

		//Tạo role cho user
		List<Role> roles = new ArrayList<>();
//		roles.add(roleRepository.findByName("ADMIN"));
		roles.add(roleRepository.findByName("USER"));
		user.setRoles(roles);
		user = userRepository.saveAndFlush(user);

		Cart cart = Cart.builder()
				.user(user)
				.status("UNSENT")
				.build();
		cartService.save(cart);
		return user;
	}

	@Override
	public User getCurrentLoginUser() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //SecurityContextHolder là nơi lưu trữ thông tin về bối cảnh bảo mật hiện tại của ứng dụng
		//trong đó bao gồm chủ thể đang sử dụng ứng dụng
		//đối tượng Authentication đc dùng để thể hiện nhgx thông tin này
		//getContext trả về đối tượng là 1 thể hiện của interface SecurityContext 
		User user= null;
		if (principal instanceof UserPrincipal) {
		 user = ((UserPrincipal)principal).getUser();
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void update(User user) {
		userRepository.saveAndFlush(user);
	}


	@Override
	public List<String> getEmails() {
		return userRepository.getEmails();
	}

	@Override
	public List<User> getFreeStaff() {
		return userRepository.getFreeStaff();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getAllActiveUsers() {
         return userRepository.getAllActiveUsers();
	}

	@Override
	public List<User> getAllStaffs() {
		List<User> all = userRepository.getAllActiveStaff();
		return userRepository.getAllActiveStaff();
	}

	@Override
	public User findByToken(String token) {
		return userRepository.findByResetPassToken(token);
	}

	@Override
	@Async("SCMExecutor")
	public void contact(ContactRequest request, User currentUser) throws MessagingException, UnsupportedEncodingException {

		String name = request.getName();
		String title = request.getTitle();
		String message = request.getMessage();

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true); //nếu dùng multipart thì tham số thứ 2 là true

		//Gửi đến với tên linh
		helper.setFrom(currentUser.getEmail(), currentUser.getFullName());
		helper.setTo("nguyenhoailinh2207@gmail.com");


		String mimeSubject = name + "đã gửi lời nhắn";
		String mimeContent = "<p><b>Tên: <b/><i>"+name+"</i></p>";
		mimeContent += "<p><b>Email: </b><i>"+currentUser.getEmail()+"</i></p>";
		mimeContent += message;

		helper.setSubject(mimeSubject);
		//để cấu hình html , tham số thứ 2 là true
		helper.setText(mimeContent, true);

		mailSender.send(mimeMessage);

	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

}
