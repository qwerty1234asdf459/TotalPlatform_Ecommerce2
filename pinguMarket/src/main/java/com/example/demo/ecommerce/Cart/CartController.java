package com.example.demo.ecommerce.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final CartService cas;
	private final UserService us;
	private final ProductService ps;
	
	
	
	
//	---------제품 삭제-------------------
	@GetMapping("/cart/delete/{id}")
	public String delcart(@PathVariable(value = "id")Integer cartId) {
		try {
			Cart c = this.cas.getCart(cartId); 
			this.cas.delete(c);
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
		return "redirect:Cart/cartPage";
		
	}
	

}
