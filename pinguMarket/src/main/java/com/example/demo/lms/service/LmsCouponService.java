package com.example.demo.lms.service;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.entity.LmsUser;
import com.example.demo.lms.repository.LmsCouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LmsCouponService {
	
	private final LmsCouponRepository couponRepository;
	private final LmsUserService userService;
	
	public void createCoupon(String id) throws Exception {
		LmsCoupon c = new LmsCoupon();
		LmsUser u = this.userService.getUser("user3");
		String code = RandomStringUtils.randomAlphanumeric(16);
		
		while(true) {
			if(findByCode(code)==null) {
				break;
			}else {
				code = RandomStringUtils.randomAlphanumeric(16);
			}
		}
		
		c.setCode(code);
		c.setUser(u);
		c.setCreateDate(LocalDateTime.now());
		c.setDiscount(3000);
		c.setUseYn("n");
		
		couponRepository.save(c);
	}
	
	public LmsCoupon findByCode(String code) {
		return couponRepository.findByCode(code);
	}
	
	
	public boolean useCheck(String code) {
		LmsCoupon c = couponRepository.findByCode(code);
		
		if(c.getUseYn()=="y") {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean existCoupon(String code) {
		LmsCoupon c = couponRepository.findByCode(code);
		
		if(c == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void useCoupon(String code) {
		LmsCoupon c = couponRepository.findByCode(code);
		c.setUseYn("y");
		couponRepository.save(c);
	}
}
