package com.linh.service;

import com.linh.dto.request.RatingRequest;

import java.util.List;
import java.util.Map;

public interface IRatingService {

    String save(RatingRequest request);

    List<Map<String, String>> getTopAvgRatingValue();

    Integer getTotalCount();

    Map<String, String> ratingProductInfo(Integer productId);
}
