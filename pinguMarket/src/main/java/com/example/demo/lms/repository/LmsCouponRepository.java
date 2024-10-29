package com.example.demo.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.LmsCoupon;



public interface LmsCouponRepository extends JpaRepository<LmsCoupon, Integer> {
	@Query(value = "SELECT * FROM coupon where code like %:code% ", 
			  nativeQuery = true)
	LmsCoupon findByCode(@Param("code")String code);
}

