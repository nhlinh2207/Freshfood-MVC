package com.linh.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private String name;
    private String title;
    private String message;
}
