package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.model.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {
}
