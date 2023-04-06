package com.linh.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import com.linh.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.linh.model.Category;
import com.linh.respository.IProductRepo;
import com.linh.service.IProductService;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

	private final IProductRepo productRepo;
	
	@Override
	@Transactional
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public List<Product> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return productRepo.findByCategory(category);
	}

	@Override
	public Page<Product> findAll(int pageNumber, String search, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort;
		if(sortBy.equals("creTime")) sort = Sort.by("createTime");
		else if(sortBy.equals("name")) sort = Sort.by("name");	
		else sort = Sort.by("price");
		sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber -1 , 12, sort);
		Page<Product> findAll = (search == null) ? productRepo.findAll(pageable) : productRepo.findAll(search, pageable);
		return findAll;
	}

	@Override
	public void delete(Integer id) {
		productRepo.deleteById(id);
	}
 
}
