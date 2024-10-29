package com.example.demo.ecommerce.Payment;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.service.LmsCouponService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {
	
	private final LmsCouponService couponService;
	
	@GetMapping("/coupon")
	public String useCoupon() {
		return "Payment/couponUseTest";
	}
	
	@PostMapping("/coupon")
	@CrossOrigin(origins = "http://localhost:8080/coupon/input")
	public ResponseEntity<String> useCoupon(@RequestBody LmsCoupon coupon) {
		System.out.println(coupon.getCode());
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/payment")
	public String paymentTest(@RequestParam(value = "code",defaultValue = "") String code, Model model) {
		LmsCoupon c = couponService.findByCode(code);
		model.addAttribute("coupon", c);
		return "Payment/CouponInfoTest";
	}
}
