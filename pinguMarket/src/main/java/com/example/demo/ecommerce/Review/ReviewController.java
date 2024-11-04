package com.example.demo.ecommerce.Review;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Entity.PaymentDetail;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.Review;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.PaymentDetail.PaymentDetailService;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.User.UserService;

import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {
	
	private final ProductService ps;
	private final UserService us;
	private final ReviewService rs;
	private final PaymentDetailService pds;
	
	
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/product/{productId}/createreview")
	@GetMapping("/reviewcreate/{productId}")
	public String reviewCreate(Model model,
			@PathVariable ("productId") Integer productId,
			Principal principal) throws CanNotFoundException {
		User u = this.us.getUser(1);
		Product p = this.ps.getProduct(productId);
		
		ReviewCreateForm reviewform = new ReviewCreateForm();
		model.addAttribute("user", u);
		model.addAttribute("reviewCreateForm", reviewform);
		
		
		try {
			Review review = this.rs.getReview(u.getUserId(), productId);
//			유저 id와 productid로 review 테이블을 조회
			PaymentDetail paymentDetail = this.pds.getPaymentDetail(u.getUserId(), productId);
//			유저 id와 productid로 payment_detail 테이블을 조회
			if(review != null) {
				return "redirect:/myreview";
//				이미 review가 있다면 myreview로 리다이렉트
			}else if(paymentDetail == null) {
				return "redirect:/myreview";
//				해당 상품을 구매한 적이 없다면 myreview로 리다이렉트
			}else {
				return "/Mypage/reviewform";
			}
	       
	    } catch (CanNotFoundException e) {
	        // 리뷰가 있으면 예외가 발생해서 myreview로 보냄
	        return "redirect:/myreview";
	    }
		} 
			

	
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/reviewcreate/{productId}")
	public String reviewCreate(Model model,
			@PathVariable ("productId") Integer productId,
			@Valid ReviewCreateForm reviewCreateForm, BindingResult bindingResult,
			Principal principal) throws CanNotFoundException {
//		Product p = this.ps.getProduct(productId);
//		User u = this.us.getUser(principal.getName());
		
		Product p = this.ps.getProduct(productId);
		User u = this.us.getUser(1);
		
		if(bindingResult.hasErrors()) {
			return "/Mypage/reviewform";
		}

//		추후 해당 유저에게 결제내역이 있는지 확인하는 절차 필요
		
		this.rs.reviewCreate(p, u,
	             reviewCreateForm.getScope(),
                 reviewCreateForm.getTitle(),
	             reviewCreateForm.getContents());

//		return String.format("redirect:/product/%s", productId);
		return "redirect:/myreview";
//		리뷰를하고 나서 어디로 리다이렉트 할지는 좀 생각을 해봐야 할 것 같음
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/reviewmodify/{reviewId}")
	public String reviewModify(Model model,
			@PathVariable ("reviewId") Integer reviewId,
			ReviewCreateForm reviewCreateform) throws CanNotFoundException {
		
		User u = us.getUser(1);
		Review review = rs.getReview(reviewId);
		reviewCreateform.setScope(review.getScope());
		reviewCreateform.setTitle(review.getTitle());
		reviewCreateform.setContents(review.getContents());
		
		model.addAttribute("user", u);
		model.addAttribute("reviewCreateForm", reviewCreateform);
		
		return "/Mypage/reviewform";
		
	}
	
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/reviewmodify/{reviewId}")
	public String reviewModify(Model model,
			@PathVariable ("reviewId") Integer reviewId,
			@Valid ReviewCreateForm reviewCreateForm, BindingResult bindingResult,
			Principal principal) throws CanNotFoundException {
		
		Review r = rs.getReview(reviewId);
		
		if(bindingResult.hasErrors()) {
			return "/Mypage/reviewform";
		}
		
		this.rs.reviewModify(r,
				 reviewCreateForm.getScope(),
                 reviewCreateForm.getTitle(),
	             reviewCreateForm.getContents());
		
		return "redirect:/myreview";
//		리뷰를하고 나서 어디로 리다이렉트 할지는 좀 생각을 해봐야 할 것 같음
	}
	
	@GetMapping("/reviewdelete/{reviewId}")
	public String reviewDelete(@PathVariable("reviewId") Integer reviewId) throws CanNotFoundException {
		Review r = rs.getReview(reviewId);
		this.rs.delete(r);
		return "redirect:/myreview";
	
  }
	

}
