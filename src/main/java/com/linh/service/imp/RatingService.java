package com.linh.service.imp;

import com.linh.dto.request.RatingRequest;
import com.linh.dto.response.TopRatingQuery;
import com.linh.model.Product;
import com.linh.model.Rank;
import com.linh.model.User;
import com.linh.respository.IRatingRepo;
import com.linh.service.IProductService;
import com.linh.service.IRatingService;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RatingService implements IRatingService {

    private final IUserService userService;
    private final IProductService productService;
    private final IRatingRepo ratingRepo;

    @Override
    public String save(RatingRequest request) {
        try {
            User currentUser = userService.findByEmail(userService.getCurrentLoginUser().getEmail());
            Product product = productService.findById(request.getProductId());
            Rank oldRank = ratingRepo.findByProductAndUser(product, currentUser);
            if (oldRank != null){
                oldRank.setModifiedTime(new Date());
                oldRank.setRankContent(request.getRatingContent());
                oldRank.setRankNumber(request.getRatingValue());
                oldRank.setRankCustomerName(request.getRatingName());
                ratingRepo.save(oldRank);
            }else{
                Rank rank = Rank.builder()
                        .rankCustomerName(request.getRatingName())
                        .rankContent(request.getRatingContent())
                        .rankNumber(request.getRatingValue())
                        .createTime(new Date())
                        .modifiedTime(new Date())
                        .product(productService.findById(request.getProductId()))
                        .user(currentUser)
                        .build();
                ratingRepo.save(rank);
            }
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }

    @Override
    public List<Map<String, String>> getTopAvgRatingValue() {
        List<Map<String, String>> result = new ArrayList<>();
        List<TopRatingQuery> data = ratingRepo.topAverageRating().stream().sorted(Comparator.comparing(TopRatingQuery::getRatingAverage, Comparator.reverseOrder())).collect(Collectors.toList());
        for (TopRatingQuery topRatingQuery : data){
            System.out.println(topRatingQuery.getProductId() + " " + topRatingQuery.getRatingAverage());
        }
        if (data.size() > 6)
             data = data.subList(0, 6);

        for (TopRatingQuery topRatingQuery : data){
            Product product = productService.findById(topRatingQuery.getProductId());
            int totalRating = product.getRanks().size();
            int topRatingTotal = (int)product.getRanks().stream().filter(r -> r.getRankNumber() >= 4).count();
            Integer topRatingPercent = Math.round( ((float)topRatingTotal / totalRating) * 100 );
            Map<String, String> item = new LinkedHashMap<>();
            item.put("productName", product.getName());
            item.put("topRatingPercent", topRatingPercent+"");
            result.add(item);
        }
        return result;
    }

    @Override
    public Integer getTotalCount() {
        return ratingRepo.countTotal();
    }

    @Override
    public Map<String, String> ratingProductInfo(Integer productId) {
        Map<String, String> result = new LinkedHashMap<>();
        Product product = productService.findById(productId);
        Integer totalRatingByProduct = ratingRepo.countByProduct(product);
        Double averageValue = ratingRepo.averageByProduct(product);
        Integer averageRatingValue = (int)Math.round(averageValue == null ? 0 : averageValue);

        result.put("totalCount", totalRatingByProduct+"");
        result.put("averageValue", averageRatingValue+"");
        return  result;
    }

}
