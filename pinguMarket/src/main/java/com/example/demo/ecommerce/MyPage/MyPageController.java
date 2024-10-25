package com.example.demo.ecommerce.MyPage;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Payment.PaymentService;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.Review.ReviewService;
import com.example.demo.ecommerce.User.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	private final ReviewService rs;
	private final UserService us;
	private final PaymentService ps;
	
	@GetMapping("/myorder")
	public String myOrderPage(Model model, Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(2);
//		List<Payment> p = this.ps.getPayment(2);
//		model.addAttribute("payment", p);
		return "Mypage/myorder";
		
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/myreview")
	public String myReviewPage(Model model, Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(2);
//		확인용 코드 로그인 생기면 위에 걸로 교체
		
		model.addAttribute("user", u);
		return "Mypage/myreview";
	}
	
}
