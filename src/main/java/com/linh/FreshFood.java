package com.linh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FreshFood {

	@Autowired
	private ServletContext servletContext;

    public static void main(String[] args) {
		SpringApplication.run(FreshFood.class, args);
	}
    
    @Bean
    CommandLineRunner run() {
    	return arg -> {
    		System.out.println("hello FreshFood !");
			System.out.println("Path : "+servletContext.getRealPath("/"));
    	};
    }
}
