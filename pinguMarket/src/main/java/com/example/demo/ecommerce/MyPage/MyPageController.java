package com.example.demo.ecommerce.MyPage;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.ecommerce.Coupon.CouponService;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Payment.PaymentService;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.Review.ReviewService;
import com.example.demo.ecommerce.User.UserModifyForm;
import com.example.demo.ecommerce.User.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	private final ReviewService rs;
	private final UserService us;
	private final PaymentService ps;
	private final CouponService cs;
	
	@GetMapping("/myorder")
	public String myOrderPage(Model model, Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(2);
		List<Payment> payment = this.ps.getPayment(u.getUserId());
		
		List<String> productNames = payment.stream()
	            .map(ps::getFirstProductName)
	            .toList();
		
		List<Integer> totalPrice = payment.stream()
				.map(ps::getTotalPrice)
				.toList();
		
		model.addAttribute("user", u);
		model.addAttribute("paymentList", payment);
		model.addAttribute("productNames", productNames);
		model.addAttribute("totalprice", totalPrice);
		return "Mypage/myorder";
		
	}
	
	@GetMapping("/myorder/detail/{paymentId}")
	public String myOrderDetailPage(Model model,
			@PathVariable ("paymentId") Integer id, Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(2);
		Payment p = this.ps.getPayment1(id);
//		
		model.addAttribute("user", u);		
		model.addAttribute("payment", p);
		return "Mypage/myorderdetail";
		
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/myreview")
	public String myReviewPage(Model model,
			Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(1);
	
		model.addAttribute("user", u);		
		return "Mypage/myreview";
	}
	
	@GetMapping("myPage")
	public String myPage(Model model) throws CanNotFoundException {
		
		User u = this.us.getUser(2);
		model.addAttribute("user", u);
		return "Mypage/myPage";
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/usermodify") 
	public String myInfoModifyPage(Model model, Principal principal) throws CanNotFoundException  {
		
//		User user = this.us.getUser(principal.getName());
//		로그인 생기면 위에걸로 수정
		User user = this.us.getUser(1);
		
		UserModifyForm form = new UserModifyForm();
	    form.setName(user.getName());
	    form.setEmail1(user.getEmail().split("@")[0]);
	    form.setEmail2(user.getEmail().split("@")[1]);
	    form.setAddress1(user.getAddress().split(" ")[0]);
	    form.setAddress2(user.getAddress().split(" ", 2)[1]);
//	    form.setAddress2(user.getAddress().split(" ")[0]);
	    
	    form.setAddressDetail(user.getAddressDetail());
	    form.setGender(user.getGender());
	    form.setTell(user.getTell());
		
	    model.addAttribute("userModifyForm", form);
	    model.addAttribute("user", user);
		
		return "Mypage/usermodify";
	}

//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/usermodify") 
	public String myInfoModifyPage(
			@Valid UserModifyForm userModifyForm, BindingResult bindingResult,
			Model model, Principal principal) throws CanNotFoundException  {
		
//		User user = this.us.getUser(principal.getName());
//		로그인 생기면 위에걸로 수정
		User user = this.us.getUser(1);
		
	    model.addAttribute("user", user);
	    
		if(bindingResult.hasErrors()) {
			return "Mypage/usermodify";
		}

		
		this.us.usermodify(user,
				userModifyForm.getEmail1(),
				userModifyForm.getEmail2(),
				userModifyForm.getName(),
				userModifyForm.getAddress1(),
				userModifyForm.getAddress2(),
				userModifyForm.getAddressDetail(),
				userModifyForm.getGender(),
				userModifyForm.getTell());
		
		return "redirect:usermodify";
		
	}
	
	@GetMapping("/mycoupon")
	public String myCouponPage(Model model, Principal principal) throws CanNotFoundException {
		
		User user = this.us.getUser(2);
		List<Coupon> coupon = this.cs.getCoupon(user.getUserId());
		
		model.addAttribute("user", user);
		model.addAttribute("couponList", coupon);
		return "Mypage/mycoupon";
	}
	
}
