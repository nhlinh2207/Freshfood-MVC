package com.linh.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.linh.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.Category;
import com.linh.respository.ICategoryRepo;
import com.linh.service.ICategoryService;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

	private final ICategoryRepo categoryRepository;
	
	@Override
	@Transactional
	public Category save(Category categoryEntity) {
		return categoryRepository.save(categoryEntity);
	}

	@Override
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).get();
	}

	@Override
	public List<CategoryDto> findAll() {
		// TODO Auto-generated method stub
		List<CategoryDto> data = new ArrayList<>();
		categoryRepository.findAll().forEach(i -> {
			data.add(
					CategoryDto.builder().id(i.getId())
							.name(i.getName())
							.description(i.getDescription())
							.totalProducts(i.getProducts().size())
							.build()
			);
		});
		return data;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Override
	public Category update(Integer id, String name) {
		// TODO Auto-generated method stub
		Category categoryEntity = categoryRepository.findById(id).get();
		categoryEntity.setName(name);
		return categoryRepository.save(categoryEntity);
	}
    
}
