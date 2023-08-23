package com.linh.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCurrentPasswordRequest {

    private Integer id;
    private String oldPassword;
    private String newPassword;
}
