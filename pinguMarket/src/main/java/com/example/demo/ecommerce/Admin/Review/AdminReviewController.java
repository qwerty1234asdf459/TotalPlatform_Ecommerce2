package com.example.demo.ecommerce.Admin.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ecommerce.Entity.Cart;
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

	//---------------관리자페이지 > 리뷰 관리(리스트)-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/Reviews") 
    public String AdminReviews(Model model) {
		List<Review> RList = this.rer.findAll();
        model.addAttribute("RList", RList);
        return "/Admin/AdminReviews";
    }
	
	//---------------관리자페이지 > 리뷰 관리(다중 선택 삭제)-----------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/Reviews/delete")
	@ResponseBody
	public Map<String, Object> AdminReviewDelete(@RequestBody Map<String, List<String>> payload) {
	    Map<String, Object> response = new HashMap<>();
	    List<String> ids = payload.get("ids"); //ids = id값들을 저장한 리스트
	    System.out.println("Received IDs: " + ids);
	try {
	        for (String reviewIdStr : ids) {
	            int reviewId = Integer.parseInt(reviewIdStr); 	//저장한 json타입의 id값을 Integer타입으로 변환해 reviewId에 할당
	            System.out.println("id확인: " + reviewId);
	            Review r = this.rs.getReview(reviewId); 		//reviewId로 리뷰 데이터를 받아옴
	            this.rs.delete(r);								//받아온 리뷰 데이터 삭제
	        }
	        response.put("success", true);						//성공적으로 삭제
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);						//삭제 실패 시 false 값을 넘김(실패했습니다 알림창)
	    }
	    return response;
    }	
	
	//---------------관리자페이지 > 리뷰관리 > 상세 페이지(조회)---------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/Reviews/detail/{reviewId}") 
    public String AdminNotice(Model model, @PathVariable("reviewId") Integer reviewId) throws CanNotFoundException {
		//reviewId로 조회해서 가져오기
		Review r = this.rs.getReview(reviewId);
        model.addAttribute("r", r);
        return "/Admin/AdminReviews_detail";
    }
	
	//---------------관리자페이지 > 리뷰관리 > 상세 페이지(개별삭제)----------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/Reviews/detail/delete/{reviewId}")
	public String reviewDelete(@PathVariable("reviewId") Integer reviewId) throws CanNotFoundException {
		Review r = rs.getReview(reviewId);
		this.rs.delete(r);
		return "redirect:/admin/Reviews";
	
  }
}
