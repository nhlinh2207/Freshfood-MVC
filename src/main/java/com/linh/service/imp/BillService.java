package com.linh.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linh.entity.BillEntity;
import com.linh.respository.InBillRes;
import com.linh.service.InBillService;

@Service
public class BillService implements InBillService{

	@Autowired
	private InBillRes bill;
	
	@Override
	@Transactional
	public BillEntity save(BillEntity b) {
		// TODO Auto-generated method stub
		return bill.save(b);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		bill.deleteById(id);;
	}

	@Override
	public List<BillEntity> findAll() {
		// TODO Auto-generated method stub
		return bill.findAll();
	}

	@Override
	public BillEntity findOneById(Integer id) {
		// TODO Auto-generated method stub
		return bill.findOneById(id);
	}
	
}
