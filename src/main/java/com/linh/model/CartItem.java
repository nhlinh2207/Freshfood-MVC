package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_cart_item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "CartId")
	private Cart cart;

	@Column(name = "Quantity")
	private Integer quantity;

	@Column(name = "TotalPrice")
	private Integer totalPrice;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "OrderTime")
	private Date orderTime;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "ModifiedTime")
	private Date modifiedTime;
}
