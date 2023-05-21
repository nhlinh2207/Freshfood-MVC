package com.linh.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Message", columnDefinition = "LONGTEXT CHARACTER SET utf8")
    private String message;

    @Column(name = "IsView")
    private Boolean isView;

    @Column(name = "IsNew")
    private Boolean isNew;

    @Column(name = "Action")
    private String action;

    @Column(name = "TenantId")
    private UUID tenantId;

    @Column(name = "RawData")
    private String rawData;

    @Column(name = "NotificationLink")
    private String notificationLink;

    @Column(name = "NotificationText", columnDefinition = "LONGTEXT CHARACTER SET utf8")
    private String notificationText;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "ModifiedTime")
    private Date modifiedTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "LastModificationTime ")
    private Date lastModificationTime ;
}
