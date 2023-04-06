package com.linh.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "tbl_category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
    
    @Column(name = "Name", columnDefinition = "NVARCHAR(255)")
    private String name;

	@Column(name = "Description", columnDefinition = "LONGTEXT CHARACTER SET utf8")
	private String description;

    @Column(name = "Status")
    private String status;
    
    @OneToMany(
    	mappedBy = "category",
    	cascade = CascadeType.ALL
    )
    @JsonIgnore
    List<Product> products;
}
