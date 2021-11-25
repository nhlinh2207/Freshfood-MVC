package com.linh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.linh.entity.BillDetailEntity;
import com.linh.entity.BillEntity;
import com.linh.respository.InBillDetailRes;
import com.linh.respository.InBillRes;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BillDetailTest {
      
	@Autowired
	private InBillRes bill;
	
	@Autowired
	private InBillDetailRes billdetail;
	
	@Test
	public void findbybill() {
		BillEntity billEntity = bill.findOneById(10);
		System.out.println(billdetail.findByBill(billEntity).size());
	}
}
