package com.example.demo.ecommerce.Payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ecommerce.Cart.CartService;
import com.example.demo.ecommerce.Coupon.CouponService;
import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.PaymentDetail.PaymentDetailService;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;
import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.service.LmsCouponService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {
	
	private final CouponService couponService;
	private final UserService userService;
	private final CartService cartService;
	private final PaymentService paymentService;
	private final PaymentDetailService paymentDetailService;
	
	@GetMapping("/apitest")
	public String Apitest() {
		return "Payment/test";
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/payments/request")
	public String requestPayment(@RequestBody PaymentRequest request) throws IOException {
	    return paymentService.requestPayment(request);
	}
	@PostMapping("/payments/confirm")
	public String confirmPayment(@RequestParam String paymentKey,
	                             @RequestParam String orderId,
	                             @RequestParam Long amount) throws IOException {
	    return paymentService.confirmPayment(paymentKey, orderId, amount);
	}
	@GetMapping("/payments/{paymentKey}")
	public String getPayment(@PathVariable String paymentKey) throws IOException {
	    return paymentService.getPayment(paymentKey);
	}
	
	///////////////////////////////////////////////////////////////////////////////
	@GetMapping("/payment")
	public String payment(Model model, @RequestParam("cartData")String cartData
			,@RequestParam("countData")String countData) throws Exception {
			User u = this.userService.getUser(1);
			List<Coupon> couponList = this.couponService.getCoupon(u.getUserId());
		 List<String> cartIdList = new ObjectMapper().readValue(cartData, new TypeReference<List<String>>() {});
		 List<String> countList = new ObjectMapper().readValue(countData, new TypeReference<List<String>>() {});
		 
		 for(int i =0; i < cartIdList.size();i++) {
			 cartService.modifyCart(cartService.getCart(Integer.parseInt(cartIdList.get(i))),
					 Integer.parseInt(countList.get(i)));
		 }
		 
		 List<Cart> cartList = this.cartService.getCartByList(cartIdList);
		 
		 model.addAttribute("cartList",cartList);
		 model.addAttribute("couponList",couponList);
		 model.addAttribute("user", u);
		
		 
		return "Payment/paymentPage";
	}
	
	@PostMapping("/payment")
	public String payment(@RequestParam("address")String address,@RequestParam(required = false,value="couponId")Integer couponId,
			@RequestParam("cartData")String cartData,@RequestParam("delRequest")String delRequest) throws Exception{
		
		User u = this.userService.getUser(1);
		Coupon c = new Coupon();
		if(couponId!=null) {
			c = this.couponService.getCouponById(couponId);
		}else {
			c = null;
		}
		
		List<String> cartIdList = new ObjectMapper().readValue(cartData, new TypeReference<List<String>>() {});
		List<Cart> cartList = this.cartService.getCartByList(cartIdList);
		
		
		Payment p =this.paymentService.createPayment(u, c, address, delRequest);
		this.couponService.useCoupon(c);
		
		for(int i=0; i<cartList.size();i++) {
			this.cartService.delete(cartList.get(i));
			this.paymentDetailService.createPaymentDetail(p, cartList.get(i));
		}
		
		return "redirect:/cart";
	}
	
	
}
