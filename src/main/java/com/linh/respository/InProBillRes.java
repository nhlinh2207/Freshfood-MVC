package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.ProdBillEntity;

public interface InProBillRes extends JpaRepository<ProdBillEntity, Integer> {

}
