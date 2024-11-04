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
import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;
import com.example.demo.lms.entity.LmsCoupon;
import com.example.demo.lms.service.LmsCouponService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {
	
	private final LmsCouponService couponService;
	private final UserService userService;
	private final ProductService productService;
	private final CartService cartService;
	
	
	@GetMapping("/payment")
	public String paymentTest(Model model, @RequestParam("cartData")String productData
			,@RequestParam("countData")String countData) throws Exception {
			User u = this.userService.getUser(1);
		 List<String> cartIdList = new ObjectMapper().readValue(productData, new TypeReference<List<String>>() {});
		 List<String> countList = new ObjectMapper().readValue(countData, new TypeReference<List<String>>() {});
		 
		 for(int i =0; i < cartIdList.size();i++) {
			 cartService.modifyCart(cartService.getCart(Integer.parseInt(cartIdList.get(i))),
					 Integer.parseInt(countList.get(i)));
		 }
		 
		 List<Cart> cartList = this.cartService.getCartByList(cartIdList);
		 
		 model.addAttribute("cartList",cartList);
		 model.addAttribute("user", u);
		
		 
		return "Payment/paymentPage";
	}
	
	@GetMapping("/paymentView")
	public String payment() {
		return "Payment/paymentPage";
	}
	
	
}
