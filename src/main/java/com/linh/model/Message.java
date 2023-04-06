package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_message")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Status")
    private String status;

    @Column(name = "Title")
    private String title;

    @Column(name = "Content")
    private String content;

    @Column(name = "CartId")
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateTime")
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "ModifiedTime")
    private Date modifiedTime;
}
