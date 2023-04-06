package com.linh.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateCartRequest {
    private String receiverName;
    private String receiverPhone;
    private String receiverEmail;
    private Integer countryId;
    private Integer cityId;
    private String deliveryAddress;
    private String comment;
}
