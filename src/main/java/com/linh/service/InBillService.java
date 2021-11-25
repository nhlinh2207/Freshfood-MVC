package com.linh.service;

import java.util.List;

import com.linh.entity.BillEntity;

public interface InBillService {
   
	BillEntity save(BillEntity b);
	void delete(Integer id);
	List<BillEntity> findAll();
	BillEntity findOneById(Integer id);
}
