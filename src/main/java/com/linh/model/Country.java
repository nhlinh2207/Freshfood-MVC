package com.linh.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tbl_country")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
    
    @Column(name = "Name", columnDefinition = "NVARCHAR(255)")
	private String name;
    
    @OneToMany(
        mappedBy = "country",
        cascade = CascadeType.ALL
    )
    private List<City> cities;

} 
