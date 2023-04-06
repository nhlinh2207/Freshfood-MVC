package com.linh.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.linh.model.Cart;
import com.linh.model.User;
import org.springframework.data.repository.query.Param;

public interface ICartRepo extends JpaRepository<Cart, Integer> {
     
	List<Cart> findByUser(User user);

	@Query("DELETE FROM Cart c WHERE c.user.id = :id")
	@Modifying
	void deleteByUser(Integer id);

	@Query("SELECT c FROM Cart  c where c.orderTime >= :startDate AND c.orderTime <= :endDate AND c.status IS NOT NULL")
	List<Cart> findByYear(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
