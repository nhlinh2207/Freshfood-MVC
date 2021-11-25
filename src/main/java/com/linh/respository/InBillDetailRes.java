package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linh.entity.BillDetailEntity;
import com.linh.entity.BillEntity;

public interface InBillDetailRes extends JpaRepository<BillDetailEntity, Integer>{
    
	@Query("SELECT b FROM BillDetailEntity b WHERE b.bill = :bill")
	List<BillDetailEntity> findByBill(@Param("bill") BillEntity bill);
}
