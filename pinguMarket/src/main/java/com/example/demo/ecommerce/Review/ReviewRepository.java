package com.example.demo.ecommerce.Review;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Review;



public interface ReviewRepository extends JpaRepository<Review, Integer>{

	
	@Query(value = "select * "
			+ "from review "
			+ "where user_id = :userId "
			+ "and product_id = :productId", nativeQuery = true)
	Optional<Review> findByUserProduct(@Param("userId") Integer userId, @Param("productId") Integer productId);
//	사용자가 해당 상품을 이미 리뷰했는지 조회하는 쿼리
	
	@Query(value = "select * "
			+ "from review "
			+ "where user_id = :userId "
			+ "and review_id = :reviewId", nativeQuery = true)
	Optional<Review> findByUserReview(@Param("userId") Integer userId, @Param("reviewId") Integer reviewId);
//  사용자가 해당 리뷰를 작성했는지 조회하는 쿼리
}
