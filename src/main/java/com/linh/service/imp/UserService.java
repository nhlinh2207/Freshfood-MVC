package com.linh.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.linh.entity.AuthProvider;
import com.linh.entity.UserEntity;
import com.linh.respository.InUserRes;
import com.linh.service.InUserService;
import com.linh.utils.UserPrincipal;

@Service
public class UserService implements InUserService {
    
	@Autowired
	private InUserRes user;
		
	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return user.findByEmail(email).isPresent();
	}

	@Override
	@Transactional
	public UserEntity save(UserEntity userEntity) {
		// TODO Auto-generated method stub
		return user.save(userEntity);
	}

	@Override
	public UserEntity getLoggingInUsser() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //SecurityContextHolder là nơi lưu trữ thông tin về bối cảnh bảo mật hiện tại của ứng dụng
		//trong đó bao gồm chủ thể đang sử dụng ứng dụng
		//đối tượng Authentication đc dùng để thể hiện nhgx thông tin này
		//getContext trả về đối tượng là 1 thể hiện của interface SecurityContext 
		UserEntity userEntity= null;
		if (principal instanceof UserPrincipal) {
		 userEntity = ((UserPrincipal)principal).getUser();
		}
		return userEntity;
	}

	@Override
	public UserEntity findOneByEmail(String email) {
		// TODO Auto-generated method stub
		return user.findOneByEmail(email);
	}

	@Override
	public void updateUser(String email, String name, AuthProvider authprovider) {
		UserEntity userEntity = user.findOneByEmail(email);
		userEntity.setFullname(name);
		userEntity.setAuthProvider(authprovider);;
		user.save(userEntity);
	}

	@Override
	public List<String> getEmails() {
		// TODO Auto-generated method stub
		return user.getEmails();
	}

}
