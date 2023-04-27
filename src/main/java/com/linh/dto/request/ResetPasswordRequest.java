package com.linh.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    private String email;
}
