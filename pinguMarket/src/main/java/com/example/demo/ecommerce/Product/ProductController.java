package com.example.demo.ecommerce.Product;




import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ecommerce.Authuser.Authuser;
import com.example.demo.ecommerce.Cart.CartService;
import com.example.demo.ecommerce.CsQuestion.UserException;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.LoginCheck.LoginCheck;
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
			@RequestParam("product")Integer productId, Model model, @Authuser User user) throws CanNotFoundException, UserException {
		User u = this.us.getUser(user.getUserId()); // 유저정보 강제 입력(추후 principal.getName()으로 변경해야 함
		Product p = this.ps.getProduct(productId);
		try {
			if(carts.cartOverlappingCheck(p, u)) {
				this.carts.createCart(u, p, count);
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 장바구니에 등록되어있는 상품 입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("장바구니에 등록되었습니다.");
	}
	
	
    @LoginCheck
	@GetMapping("/cart") // 장바구니 전체
    // ModelAndView : 컴포넌트 방식. ModelAndView 객체를 생성해서 객체 형태로 반환
	public ModelAndView viewcart(Model model, @Authuser User user) throws  UserException, CanNotFoundException {
		ModelAndView mv = new ModelAndView(); 
    	
    	try {
		User u = this.us.getUser(user.getUserId()); // 유저정보 강제 입력(추후 principal.getName()으로 변경해야 함
		//model.addAttribute("user", u);
		mv.addObject(u); // 값을 넣을땐 addObject() 사용
		mv.setViewName( "Cart/cartPage"); // 값을 보낼땐 setViewName() 사용
		}catch(InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			mv.addObject(e);
			mv.setViewName("Login/loginPage");
		}
		
		return mv;
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
