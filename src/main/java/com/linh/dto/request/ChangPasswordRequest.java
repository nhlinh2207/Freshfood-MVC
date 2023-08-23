package com.linh.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangPasswordRequest {

    private String newPassword;
    private String token;
}
