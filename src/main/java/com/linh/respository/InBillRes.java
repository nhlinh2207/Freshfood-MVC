package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.BillEntity;

public interface InBillRes extends JpaRepository<BillEntity, Integer>{
    
    BillEntity findOneById(Integer id);
}
