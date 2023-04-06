package com.linh.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.linh.utils.LoginSuccessHandler;
import com.linh.utils.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserPrincipalDetailService userprincipaldetailservice;
	
//	@Autowired
//	private OauthUserPrincipalDetailService oauthservice;
	
	private final LoginSuccessHandler loginSuccessHandler;
	
//	@Autowired
//	private OauthLoginSuccessHandler oauthloginsuccess;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
	    auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
//		        .antMatchers("/oauth/**").permitAll()
		        .antMatchers("/bookstore/trang-chu").permitAll()
				.antMatchers("/freshfood/admin/**").hasRole("ADMIN")
				.antMatchers("/freshfood/staff/**").hasRole("STAFF")
				.antMatchers("/**").permitAll()
		         .and()
		         .formLogin()
				        .loginProcessingUrl("/signin")
		                .loginPage("/freshfood/dang-nhap")
		                .permitAll()
		                .usernameParameter("email") 
		                .passwordParameter("password") 
		                .failureUrl("/freshfood/dang-nhap?error")
		                .successHandler(loginSuccessHandler)
				.and()
//		   	.oauth2Login().loginPage("/freshfood/dang-nhap")
//		   	              .userInfoEndpoint().userService(oauthservice)
//		   	              .and()
//		   	              .successHandler(oauthloginsuccess)
//		    .and()
				.logout().logoutUrl("/logout")
		              .logoutSuccessUrl("/freshfood/trang-chu")
	                  .deleteCookies("JSESSIONID")
		              .invalidateHttpSession(true)
	                  .clearAuthentication(true);
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userprincipaldetailservice);
		return daoAuthenticationProvider;
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
