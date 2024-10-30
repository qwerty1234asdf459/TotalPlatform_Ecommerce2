package com.example.demo.ecommerce.Coupon;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.MyPage.CouponOverlappingException;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.User.UserService;
import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.service.LmsCouponService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CouponService {
	private final CouponRepository cr;
	private final LmsCouponService lcs;
	private final UserService us;
	
	public List<Coupon> getCoupon(Integer userId) throws CanNotFoundException {
		List<Coupon> coupon = this.cr.findByUserUseYn(userId);
			return coupon;
	}
	
	public void createCoupon(String code) throws CanNotFoundException, CouponOverlappingException{
		
		User user = this.us.getUser(1);
		Coupon c = new Coupon();
		LmsCoupon lc = this.lcs.findByCode(code);
		
		c.setCode(lc.getCode());
		c.setUser(user);
		c.setCreateDate(lc.getCreateDate());
		c.setDiscount(lc.getDiscount());
		c.setUseYn(lc.getUseYn());
		
		this.cr.save(c);
	}
	
}