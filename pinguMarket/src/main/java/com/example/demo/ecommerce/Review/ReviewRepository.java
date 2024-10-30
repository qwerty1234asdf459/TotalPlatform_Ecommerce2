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
	

}
