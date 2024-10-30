package com.example.demo.ecommerce.Coupon;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CouponService {

	private CouponRepository cr;
	
//	public Coupon getCoupon(Integer userId, String useYn) {
//
//		Optional<Coupon> coupon = this.cr.findByUserUseYn(userId, useYn);
//		if(coupon.isPresent()) {
//			return coupon.get();
//		}
//		else {
//			throw new CanNotFoundException("존재하지 않는 유저입니다");
//		}
//	}
	
	
	
	
}
