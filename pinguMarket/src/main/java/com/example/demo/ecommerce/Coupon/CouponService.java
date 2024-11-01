package com.example.demo.ecommerce.Coupon;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository cr;
	
	public List<Coupon> getCoupon(Integer userId) throws CanNotFoundException {

		List<Coupon> coupon = this.cr.findByUserUseYn(userId);
			return coupon;
	}
	
	
	
	
}
