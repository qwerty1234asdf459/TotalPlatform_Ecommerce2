package com.example.demo.ecommerce.Product;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.ecommerce.Admin.Product.AdminProductService;
import com.example.demo.ecommerce.Cart.CartService;
import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.User.UserService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {
	
	private final CartService carts;
	private final UserService us;
	private final ProductService ps;
    private final AdminProductService aps;
	
	
//	---------------------------------------------장바구니----------------------------------------------------------------------------------------------
	@GetMapping("/product/{productId}/addcart")
	public String addcart(@PathVariable(value = "productId")Integer productId, Model model, Principal principal) throws Exception {
		User u = this.us.getUser(principal.getName());
		Product p = this.ps.getProduct(productId);
		
		try {
			Cart ct = this.carts.getCart(u.getUserId(), productId);
			if(!u.getCartList().contains(ct)) {
				this.carts.createCart(u, p);
			}
		} catch (Exception e) {
			this.carts.createCart(u, p);
		}
		model.addAttribute("user", u);
		return "Cart";
	}
  
	
	@GetMapping("/product/{productId}") //상품상세페이지
    public String ProductDetail(Model model, @PathVariable("productId") Integer productId) throws CanNotFoundException {
		//product_id로 조회해서 가져오기
		Product p = this.ps.getProduct(productId);
        model.addAttribute("p", p);
        return "/Product/ProductDetailPage"; 
    }
	
	
}
