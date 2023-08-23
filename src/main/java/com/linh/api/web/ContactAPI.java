package com.linh.api.web;

import com.linh.dto.request.ContactRequest;
import com.linh.model.User;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RestController
@AllArgsConstructor
public class ContactAPI {
      
	private final IUserService userService;

	@PostMapping(value = "/freshfood/contact")
	public Integer sendmail(@RequestBody ContactRequest contactRequest) throws MessagingException, UnsupportedEncodingException {
		User currentUser = userService.findByEmail(userService.getCurrentLoginUser().getEmail());
		userService.contact(contactRequest, currentUser);
		return 1;
	}
}
