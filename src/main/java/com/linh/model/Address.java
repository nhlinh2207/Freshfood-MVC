package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_address")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Commune")
    private String commune;

    @Column(name = "District")
    private String district;

    @Column(name = "CityId")
    private Integer cityId;

    @Column(name = "CCountryId")
    private Integer countryId;

    @OneToOne
    @JoinColumn(name = "UserId")
    private User user;

    @OneToOne
    @JoinColumn(name = "CartId")
    private Cart cart;

    @Column(name = "FullAddress", columnDefinition = "LONGTEXT CHARACTER SET utf8")
    private String fullAddress;

    @Column(name = "Type")
    private String type;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateDate")
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "ModifiedTime")
    private Date modifiedTime;
}
