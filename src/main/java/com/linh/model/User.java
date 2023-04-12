package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.enums.AuthProvider;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "FullName", columnDefinition = "NVARCHAR(255)")
	@NotBlank(message = "Tên không đc trống :))")
	private String fullName;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Email")
	@NotBlank(message = "email không được trống")
	@Email(regexp = "^(.+)@(.+)$", message = "email không hợp lệ")
	private String email;
	
	@Column(name = "Password")
	@Size(min = 6, message = "password phải có ít nhất 6 kí tự")
	private String password;
	
	@Column(name = "PhoneNumber")
	@Pattern(regexp = "^\\d{10}$", message = "số điện thoại không hợp lệ")
	@Size(min = 3, max = 11, message = "số điện thoại từ 3 đến 11 kí tự")
	private String phoneNumber;

	@Column(name = "Age")
	private Integer age;

	@Column(name = "Gender")
	private String gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "authprovider")
	private AuthProvider authProvider;

	@Column(name = "Status")
	private String status;

	@Column(name = "Type")
	private String type;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CreateTime")
	private Date createTime;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
         name = "tbl_user_role",
         joinColumns =@JoinColumn(name = "UserId"),
         inverseJoinColumns = @JoinColumn(name = "RoleId")
	)
	private List<Role> roles;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL
	)
	List<Rank> ranks;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL
	)
	List<Comment> comments;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(
	   mappedBy = "user",
	   cascade = CascadeType.ALL
    )
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "staff")
	private List<Cart> deliveryCart = new ArrayList<>();

	@OneToMany(
			mappedBy = "user",
			cascade =  CascadeType.ALL
	)
	private List<Message> messages;
}
