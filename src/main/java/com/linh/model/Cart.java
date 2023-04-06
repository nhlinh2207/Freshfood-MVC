package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_cart")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
	private Integer id;

	@Column(name = "TotalPrice")
	private Float totalPrice;

	@Column(name = "ReceiverName")
	private String receiverName;

	@Column(name = "ReceiverPhoneNumber")
	private String receiverPhoneNumber;

	@Column(name = "ReceiverEmail")
	private String receiverEmail;

	@Column(name = "Status")
	private String status;

	@Column(name = "PaymentType")
	private String paymentType;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "OrderTime")
	private Date orderTime;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "DeliverTime")
	private Date deliverTime;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(
			mappedBy = "cart",
			cascade = CascadeType.ALL
	)
	private List<CartItem> cartItems;

	@ManyToOne
	@JoinColumn(name = "StaffId")
	private User staff;
}
