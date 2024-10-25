package com.example.demo.ecommerce.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.Review;



public interface ReviewRepository extends JpaRepository<Review, Integer>{
	

}
