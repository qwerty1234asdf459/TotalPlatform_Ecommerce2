package com.example.demo.ecommerce.Coupon;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.ecommerce.Entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

//	@Query()
//	Optional<Coupon> findByUserUseYn(Integer userId, String useYn);

}
