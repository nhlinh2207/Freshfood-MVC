package com.linh.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linh.entity.BillDetailEntity;
import com.linh.entity.BillEntity;
import com.linh.respository.InBillDetailRes;
import com.linh.service.InBillDetailService;

@Service
public class BillDetailService implements InBillDetailService{
	
	@Autowired
	private InBillDetailRes billdetail;

	@Override
	@Transactional
	public void save(BillDetailEntity billDetailEntity) {
		// TODO Auto-generated method stub
		billdetail.save(billDetailEntity);
	}

	@Override
	public List<BillDetailEntity> findByBill(BillEntity bill) {
		// TODO Auto-generated method stub
		return billdetail.findByBill(bill);
	}

}
