package com.example.demo.ecommerce.Payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Cart.CartService;
import com.example.demo.ecommerce.Coupon.CouponService;
import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
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
		
		Payment p = new Payment();
		
		List<String> cartIdList = new ObjectMapper().readValue(cartData, new TypeReference<List<String>>() {});
		List<Cart> cartList = this.cartService.getCartByList(cartIdList);
		
		
		this.paymentService.createPayment(u, c, address, delRequest);
		this.couponService.useCoupon(c);
		
		for(int i=0; i<cartList.size();i++) {
			this.cartService.delete(cartList.get(i));
		}
		
		return "redirect:/cart";
	}
	
	
}
