package com.linh.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {

    private String ratingName;
    private String ratingContent;
    private Integer ratingValue;
    private Integer productId;
}
