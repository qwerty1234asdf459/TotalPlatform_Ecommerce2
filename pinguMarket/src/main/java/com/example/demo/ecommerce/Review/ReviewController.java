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
		User u = this.us.getUser(2);
		Product p = this.ps.getProduct(productId);
		
		ReviewForm reviewForm = new ReviewForm();
		model.addAttribute("user", u);
		model.addAttribute("reviewForm", reviewForm);
		
		
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
			@Valid ReviewForm reviewForm, BindingResult bindingResult,
			Principal principal) throws CanNotFoundException {
//		Product p = this.ps.getProduct(productId);
//		User u = this.us.getUser(principal.getName());
		
		Product p = this.ps.getProduct(productId);
		User u = this.us.getUser(2);
		
		if(bindingResult.hasErrors()) {
			return "/Mypage/reviewform";
		}

//		추후 해당 유저에게 결제내역이 있는지 확인하는 절차 필요
		
		this.rs.reviewCreate(p, u,
	             reviewForm.getScope(),
                 reviewForm.getTitle(),
	             reviewForm.getContents());

//		return String.format("redirect:/product/%s", productId);
		return "redirect:/myreview";
//		리뷰를하고 나서 어디로 리다이렉트 할지는 좀 생각을 해봐야 할 것 같음
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/reviewmodify/{reviewId}")
	public String reviewModify(Model model,
			@PathVariable ("reviewId") Integer reviewId,
			ReviewForm reviewform) throws CanNotFoundException {
		
		User u = this.us.getUser(2);
		
		try {
			Review review = this.rs.getReviewModify(u.getUserId(), reviewId);
//			유저 id와 reviewId로 review 테이블을 조회
			if(review == null) {
				return "redirect:/myreview";
//				해당 userId로 해당 reviewId에 해당이 안 되면 myreview로 리다이렉트
			}else {
				reviewform.setScope(review.getScope());
				reviewform.setTitle(review.getTitle());
				reviewform.setContents(review.getContents());
				
				model.addAttribute("user", u);
				model.addAttribute("reviewForm", reviewform);
				
				return "/Mypage/reviewform";
//				reviewform 페이지에 기존 리뷰의 제목, 내용, 평점을 세팅하고 reviewform으로 리턴
			}
	       
	    } catch (CanNotFoundException e) {
	        // 리뷰가 없으면 예외가 발생해서 myreview로 보냄
	        return "redirect:/myreview";
	    }
		} 
		

		
	
	
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/reviewmodify/{reviewId}")
	public String reviewModify(Model model,
			@PathVariable ("reviewId") Integer reviewId,
			@Valid ReviewForm reviewForm, BindingResult bindingResult,
			Principal principal) throws CanNotFoundException {
		
		Review r = rs.getReview(reviewId);
		
		if(bindingResult.hasErrors()) {
			return "/Mypage/reviewform";
		}
		
		this.rs.reviewModify(r,
				 reviewForm.getScope(),
                 reviewForm.getTitle(),
	             reviewForm.getContents());
		
		return "redirect:/myreview";
//		리뷰를 하고 나서 어디로 리다이렉트 할지는 좀 생각을 해봐야 할 것 같음
	}
	
	@GetMapping("/reviewdelete/{reviewId}")
	public String reviewDelete(@PathVariable("reviewId") Integer reviewId) throws CanNotFoundException {
		Review r = rs.getReview(reviewId);
		this.rs.delete(r);
//		리뷰 삭제
		return "redirect:/myreview";
	
  }
	

}
