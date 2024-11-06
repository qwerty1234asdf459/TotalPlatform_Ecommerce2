package com.example.demo.ecommerce.Coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	@Query(value = "select * "
			+ "from coupon "
			+ "where use_yn = 'n' and "
			+ "user_id = :userId", nativeQuery = true)
	List<Coupon> findByUserUseYn(@Param("userId") Integer userId);

	Coupon findByCouponId(Integer couponId);
}
