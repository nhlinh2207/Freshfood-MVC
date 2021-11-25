package com.linh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FreshFood {
    public static void main(String[] args) {
		SpringApplication.run(FreshFood.class, args);
	}
    
    @Bean
    CommandLineRunner run() {
    	return arg -> {
    		System.out.println("hello Freshfood !");
    	};
    }
}
