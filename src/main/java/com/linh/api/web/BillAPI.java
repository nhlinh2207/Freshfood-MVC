package com.linh.api.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.linh.entity.BillEntity;
import com.linh.entity.BillForm;
import com.linh.entity.CartItemEntity;
import com.linh.entity.ProductEntity;
import com.linh.entity.UserEntity;
import com.linh.service.InBillService;
import com.linh.service.InCartItemService;
import com.linh.service.InCityService;
import com.linh.service.InCountryService;
import com.linh.service.InUserService;

@RestController
public class BillAPI {
    
	@Autowired
	private InCountryService countryService;
	
	@Autowired
	private InCityService cityService;
	
	@Autowired
	private InUserService userservice;
	
	@Autowired
	private InCartItemService cartitemservice;
	
	@Autowired
	private InBillService billService;
	
	@PostMapping(value = "/freshfood/bill/add")
	public void add(@RequestBody BillForm billForm) {
		BillEntity b = new BillEntity();
		b.setFullname(billForm.getFirstname());
		b.setCountry(countryService.findOneById(billForm.getCountry_id()).getName());
		b.setProvince(cityService.findOneById(Integer.valueOf(billForm.getZone_id())).getName());
		b.setTelephone(billForm.getTelephone());
		b.setEmail(billForm.getEmail());
		b.setWard(billForm.getWard_id());
		b.setMess(billForm.getComment());
		b.setShipaddress(billForm.getAddress_1());
		b.setCreate_time(new Date());
		
		UserEntity user = userservice.getLoggingInUsser();
		List<CartItemEntity> cart = cartitemservice.findByUser(user);
		for (CartItemEntity c : cart) {
			ProductEntity p = c.getProduct();
			b.getProductlist().add(p);
			p.getBills().add(b);
		}
		
		billService.save(b);
		cartitemservice.deleteByUser(user.getId());
	}
}
