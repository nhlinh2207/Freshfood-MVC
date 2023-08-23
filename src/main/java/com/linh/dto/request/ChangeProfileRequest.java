package com.linh.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangeProfileRequest {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private Integer countryId;
    private Integer cityId;
    private String address;
}

