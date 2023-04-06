package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.linh.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
	User findByEmail(String email);
	
	@Query("SELECT e.email FROM User e")
	List<String> getEmails();

	@Query("SELECT s FROM User s WHERE s.type = 'STAFF' AND s.status = 'FREE'")
	List<User> getFreeStaff();
}
