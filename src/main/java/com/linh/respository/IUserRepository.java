package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.linh.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	User findByEmail(String email);

	@Query("SELECT e FROM User e WHERE e.status <> 'INACTIVE' AND (e.type is null or e.type <> 'STAFF')")
	List<User> getAllActiveUsers();

	@Query("SELECT e FROM User e WHERE e.type = 'STAFF' AND e.status <> 'INACTIVE'")
	List<User> getAllActiveStaff();

	@Query("SELECT e.email FROM User e")
	List<String> getEmails();

	@Query("SELECT s FROM User s WHERE s.type = 'STAFF' AND s.status = 'FREE'")
	List<User> getFreeStaff();

	User findByResetPassToken(String token);
}
