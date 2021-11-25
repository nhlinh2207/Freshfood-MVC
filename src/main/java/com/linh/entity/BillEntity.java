package com.linh.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "NVARCHAR(100)")
	private String fullname;
	
	@Column(columnDefinition = "VARCHAR(100)")
	private String email;
	
	@Column(columnDefinition = "VARCHAR(20)")
	private String telephone;
	
	@Column(columnDefinition = "NVARCHAR(100)")
	private String country;
	
	@Column(columnDefinition = "NVARCHAR(100)")
    private String province;
	
	@Column(columnDefinition = "NVARCHAR(100)")
    private String ward;
	
	@Column(columnDefinition = "NTEXT")
    private String mess;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String shipaddress;
	
	private Date create_time;
	
	@OneToMany(
			mappedBy = "bill",
	    	cascade = CascadeType.ALL
	)
	private List<BillDetailEntity> billdetailList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	
}
