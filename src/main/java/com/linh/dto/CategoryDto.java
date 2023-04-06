package com.linh.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
    private Integer totalProducts;
}
