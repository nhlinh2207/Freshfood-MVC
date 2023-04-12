package com.linh.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.linh.utils.enums.AuthProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.linh.model.User;
//UserDetails đc dùng để xây dựng đối tượng Authentication 
//đc luu trong SecurityContextHolder
//và UserDetailService dùng để tạo ra UserDetails dựa theo username đc đưa vào
public class UserPrincipal implements UserDetails{

	private final User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
	   List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	   this.user.getRoles().forEach(
	         r -> {
	        	 String role = r.getName();
	        	 GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);
	        	 roles.add(grantedAuthority);
	         }
	   );
	   return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	public String getEmail() {
		return this.user.getEmail();
	}
	
	public User getUser() {
		return this.user;
	}
	
	public AuthProvider getauthprovider() {
		return this.user.getAuthProvider();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getFullName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
//		return this.user.getStatus().equals("ACTIVE");
		return true;
	}
}
