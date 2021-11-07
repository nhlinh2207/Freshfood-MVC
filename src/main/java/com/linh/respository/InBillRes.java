package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linh.entity.BillEntity;
import com.linh.entity.ProductEntity;

public interface InBillRes extends JpaRepository<BillEntity, Integer>{
    @Query("SELECT  b.productlist FROM BillEntity b WHERE b.id = :id")
    List<ProductEntity> findallProduct(@Param("id") Integer id);
    
    BillEntity findOneById(Integer id);
}
