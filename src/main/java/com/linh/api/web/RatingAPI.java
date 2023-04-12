package com.linh.api.web;

import com.linh.dto.request.RatingRequest;
import com.linh.service.IRatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
public class RatingAPI {

    private final IRatingService ratingService;

    @PostMapping(path = "/freshfood/rating/save")
    public Map<String, String> saveRating(@RequestBody RatingRequest req){
        Map<String, String> response = new LinkedHashMap<>();
        try{
            ratingService.save(req);
            response.put("success", "success");
           return  response;
        }catch (Exception e){
            response.put("fail", "fail");
            return  response;
        }
    }

    @GetMapping(path = "/freshfood/rating/topAvg")
    public List<Map<String, String>> getTopAvgRatingValue(){
        List<Map<String, String>> response = new ArrayList<>();
        try{
             response = ratingService.getTopAvgRatingValue();
             return response;
        }catch (Exception e){
            e.printStackTrace();
            return  response;
        }
    }

    @GetMapping(path = "/freshfood/rating/info/{productId}")
    public Map<String, String> getRatingInfoOfProduct(@PathVariable Integer productId){
        Map<String, String> response = new LinkedHashMap<>();
        try{
            response = ratingService.ratingProductInfo(productId);
            return  response;
        }catch (Exception e){
            e.printStackTrace();
            return  response;
        }
    }

}
