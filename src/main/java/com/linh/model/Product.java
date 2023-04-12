package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Name", columnDefinition = "NVARCHAR(255)")
	private String name;

	@Column(name = "Description", columnDefinition = "LONGTEXT CHARACTER SET utf8")
	private String description;
	
	@Column(name = "Image", columnDefinition = "VARCHAR(255)")
	private String image;

	@Column(name = "Price")
	private Integer price;

	@Column(name = "Discount")
	private Integer discount;

	@Column(name = "BuyingCount")
	private Integer buyingCount;

	@Column(name = "Quantity")
	private Integer quantity;

	@Column(name = "Status")
	private String status;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CreateTime")
	private Date createTime;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "modifiedTme")
	private Date modifiedTime;
	
	@Column(name = "ExtraImage1", columnDefinition = "VARCHAR(255)")
	private String extra_img1;
	
	@Column(name = "ExtraImage2", columnDefinition = "VARCHAR(255)")
	private String extra_img2;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;
	
	@OneToMany(
	     mappedBy = "product",
	     cascade = CascadeType.ALL
	)
	List<CartItem> cartItems;

	@OneToMany(
			mappedBy = "product",
			cascade = CascadeType.ALL
	)
	List<Rank> ranks;

	@OneToMany(
			mappedBy = "product",
			cascade = CascadeType.ALL
	)
	List<Comment> comments;

	@Transient
	public String getProductImagePath() {
		return (id == null || image == null) ? null : "/image/san-pham/"+id+"/"+image;
	}
	
	@Transient
	public String getProductExtraImagePath1() {
		return (id == null || image == null) ? null : "/image/san-pham/"+id+"/"+extra_img1;
	}
	
	@Transient
	public String getProductExtraImagePath2() {
		return (id == null || image == null) ? null : "/image/san-pham/"+id+"/"+extra_img2;
	}
	
	@Transient
	public String getPriceCurrency() {
		StringBuilder s = new StringBuilder(price.toString());
		for(int i = price.toString().length() -3; i>=0; i-=3) {
			if(i == 0) continue;
			s.insert(i, ',');
		}
		s.append(" đ");
		return s.toString();
	}
}
