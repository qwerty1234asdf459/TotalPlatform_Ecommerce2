package com.example.demo.ecommerce.Product;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Cart.CartService;
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
    
	//	---------------------------------------------상품리스트 페이지----------------------------------------------------------------------------------------------
    @GetMapping("/category")
    public String productList() {
    	return "Category/categoryPage";
    }
    
	
//	---------------------------------------------장바구니----------------------------------------------------------------------------------------------
	@PostMapping("/product/addcart")
	public String addcart(@RequestParam("cart_count")Integer count,
			@RequestParam("product")Integer productId, Model model, Principal principal) throws Exception {
		User u = this.us.getUser(1); // 유저정보 강제 입력(추후 principal.getName()으로 변경해야 함
		Product p = this.ps.getProduct(productId);
		try {
			if(carts.cartOverlappingCheck(p, u)) {
				this.carts.createCart(u, p, count);
			}else {
				return "redirect:/product/"+Integer.toString(productId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cart";
	}
	
	
	
	
	@GetMapping("/cart") // 장바구니 전체
	public String viewcart(Model model, Principal principal) throws Exception {
		User u = this.us.getUser(1); // 유저정보 강제 입력(추후 principal.getName()으로 변경해야 함
		
		model.addAttribute("user", u);
		
		return "Cart/cartPage";
	}
   
//	---------------------------------------------상품상세페이지----------------------------------------------------------------------------------------------	
	@GetMapping("/product/{productId}")
    public String ProductDetail(Model model, @PathVariable("productId") Integer productId) throws CanNotFoundException {
		//product_id로 조회해서 가져오기
		Product p = this.ps.getProduct(productId);
        model.addAttribute("p", p);
        return "/Product/ProductDetailPage"; 
    }
	
	
}
