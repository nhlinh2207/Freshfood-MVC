package com.linh.service;

import java.util.List;

import com.linh.entity.BillDetailEntity;
import com.linh.entity.BillEntity;

public interface InBillDetailService {
    void save(BillDetailEntity billDetailEntity);
    List<BillDetailEntity> findByBill(BillEntity bill);
}
