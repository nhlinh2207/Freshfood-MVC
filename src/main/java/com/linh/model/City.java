package com.linh.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_city")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Name", columnDefinition = "NVARCHAR(255)")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "CountryId")
	private Country country;

}
