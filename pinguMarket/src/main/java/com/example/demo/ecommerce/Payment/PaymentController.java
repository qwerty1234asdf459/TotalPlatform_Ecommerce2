package com.example.demo.ecommerce.Payment;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;
import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.service.LmsCouponService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {
	
	private final LmsCouponService couponService;
	private final UserService userService;
	private final ProductService productService;
	
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
	public String paymentTest(Model model) {
		
		
		return "Payment/paymentPage";
	}
	
	@GetMapping("/paymentView")
	public String payment() {
		return "Payment/paymentPage";
	}
	
	
}
