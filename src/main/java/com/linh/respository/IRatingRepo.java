package com.linh.respository;

import com.linh.dto.response.TopRatingQuery;
import com.linh.model.Product;
import com.linh.model.Rank;
import com.linh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRatingRepo extends JpaRepository<Rank, Integer> {
    Rank findByProductAndUser(Product product, User user);

    @Query(value = "SELECT new com.linh.dto.response.TopRatingQuery(r.product.id, AVG(r.rankNumber)) FROM Rank r GROUP BY r.product.id")
    List<TopRatingQuery> topAverageRating();

    @Query(value = "SELECT COUNT(r) FROM Rank r")
    Integer countTotal();

    Integer countByProduct(Product product);

    List<Rank> findByProduct(Product product);

    @Query(value = "SELECT AVG(r.rankNumber) FROM Rank r WHERE r.product = :product GROUP BY r.product.id")
    Double averageByProduct(@Param("product") Product product);
}
