package com.linh.api.admin;

import java.util.List;

import javax.transaction.Transactional;

import com.linh.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.linh.model.Category;
import com.linh.service.ICategoryService;

@RestController
@AllArgsConstructor
public class CatalogAPI {
      
	private final ICategoryService categoryService;
	
	@GetMapping(value = "/freshfood/category/getall")
	public List<CategoryDto> findAllCategories(){
		return categoryService.findAll();
	}
	
	@PostMapping(value = "/freshfood/category/add")
	@Transactional
	public Integer saveCategory(@RequestBody Category cate) {
		categoryService.save(cate);;
		return cate.getId();
	}
	
	@DeleteMapping(value = "/freshfood/category/xoa/{id}")
	@Transactional
	public Integer deleteCategory(@PathVariable("id") Integer id) {
		categoryService.delete(id);
		return id;
	}
	
	@PutMapping(value = "/freshfood/category/update")
	@Transactional
	public Integer updateCategory(@RequestBody Category cate) {
		categoryService.update(cate.getId(), cate.getName());
		return cate.getId();
	}
}
