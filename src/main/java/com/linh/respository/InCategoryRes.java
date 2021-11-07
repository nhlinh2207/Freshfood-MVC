package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.CategoryEntity;

public interface InCategoryRes extends JpaRepository<CategoryEntity, Integer> {
	 CategoryEntity findOneById(Integer id);
}
