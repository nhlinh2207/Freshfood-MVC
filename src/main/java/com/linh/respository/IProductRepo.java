package com.linh.respository;

import java.util.List;

import com.linh.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linh.model.Category;

public interface IProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(Category category);

	@Query("SELECT p FROM Product p WHERE p.name LIKE %:search%")
	Page<Product> findAll(@Param("search") String search, Pageable pageable);

}
