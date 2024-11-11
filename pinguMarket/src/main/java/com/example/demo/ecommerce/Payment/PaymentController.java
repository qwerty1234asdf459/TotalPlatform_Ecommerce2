package com.example.demo.ecommerce.Payment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Authuser.Authuser;
import com.example.demo.ecommerce.Cart.CartService;
import com.example.demo.ecommerce.Coupon.CouponService;
import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.LoginCheck.LoginCheck;
import com.example.demo.ecommerce.PaymentDetail.PaymentDetailService;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
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
	private final ProductService productService;
	
	@GetMapping("/apitest")
	public String Apitest() {
		return "Payment/test";
	}


	///////////////////////////////////////////////////////////////////////////////
	@LoginCheck
	@GetMapping("/payment")
	public String payment(@Authuser User u,Model model, @RequestParam("cartData")String cartData
			,@RequestParam("countData")String countData) throws Exception {
		
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
	
	@LoginCheck
	@PostMapping("/payment")
	public ResponseEntity<String> createPayment(@Authuser User u,@RequestParam("address")String address,@RequestParam(required = false,value="couponId")Integer couponId,
			@RequestParam("cartData")String cartData,@RequestParam("delRequest")String delRequest,@RequestParam("orderId")String orderId) throws Exception{
		
		Coupon c = new Coupon();
		if(couponId!=null) {
			c = this.couponService.getCouponById(couponId);
		}else {
			c = null;
		}
		
		List<String> cartIdList = new ObjectMapper().readValue(cartData, new TypeReference<List<String>>() {});
		List<Cart> cartList = this.cartService.getCartByList(cartIdList);
		
		
		Payment p =this.paymentService.createPayment(u, c, address, delRequest, orderId);
		this.couponService.useCoupon(c);
		
		for(int i=0; i<cartList.size();i++) {
			this.cartService.delete(cartList.get(i));
			this.paymentDetailService.createPaymentDetail(p, cartList.get(i));
		}
		return ResponseEntity.ok("주문 생성");
	}
	
	@PostMapping("/paymentAmountCheck")
	public ResponseEntity<String> AmountCheck(@RequestParam("cartData")String cartData) throws Exception{
		List<String> cartIdList = new ObjectMapper().readValue(cartData, new TypeReference<List<String>>() {});
		List<Cart> cartList = this.cartService.getCartByList(cartIdList);
	
		for(int i = 0 ; i < cartList.size(); i++) {
			Product p = cartList.get(i).getProduct();
			Integer amount = cartList.get(i).getProductCount();
			System.out.println(p.getAmount()-amount);
			if(p.getAmount()-amount < 0) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품수량이 부족합니다.");
			}else {
				return ResponseEntity.ok("주문 가능");
			}
		}
		return ResponseEntity.ok("주문 가능");
	}
	
}
