package com.example.demo.ecommerce.Admin.Review;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ecommerce.Entity.Review;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.Review.ReviewRepository;
import com.example.demo.ecommerce.Review.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class AdminReviewController {
	
	private final ReviewService rs;
	private final ReviewRepository rer;

	//---------------관리자페이지 > 리뷰 관리-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/Reviews") 
    public String AdminReviews(Model model) {
		List<Review> RList = this.rer.findAll();
        model.addAttribute("RList", RList);
        return "/Admin/AdminReviews";
    }
	
	
	//---------------관리자페이지 > 리뷰관리 > 상세 페이지-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/Reviews/{reviewId}") 
    public String AdminNotice(Model model, @PathVariable("reviewId") Integer reviewId) throws CanNotFoundException {
		//reviewId로 조회해서 가져오기
		Review r = this.rs.getReview(reviewId);
        model.addAttribute("r", r);
        return "/Admin/AdminReviews_detail";
    }
}
