package com.linh.service;

import java.util.List;

import com.linh.model.Product;
import org.springframework.data.domain.Page;

import com.linh.model.Category;

public interface IProductService {

     Product save(Product product);

     Product findById(Integer id);

     List<Product> findAll();

     List<Product> findByCategory(Category category);

     Page<Product> findAll(int pageNumber, String search, String sortBy, String sortDir);

     void delete(Integer id);
}
