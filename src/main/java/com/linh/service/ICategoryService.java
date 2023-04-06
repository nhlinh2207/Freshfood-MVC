package com.linh.service;

import java.util.List;

import com.linh.dto.CategoryDto;
import com.linh.model.Category;

public interface ICategoryService {

    Category save(Category category);

    Category findById(Integer id);

    List<CategoryDto> findAll();

    void delete(Integer id);

    Category update(Integer id, String name);
}
