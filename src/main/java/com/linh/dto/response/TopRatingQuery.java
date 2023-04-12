package com.linh.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TopRatingQuery {

    private Integer productId;
    private Double ratingAverage;
}
