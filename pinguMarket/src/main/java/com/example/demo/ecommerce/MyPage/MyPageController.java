package com.example.demo.ecommerce.MyPage;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.aspectj.weaver.patterns.ThrowsPattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Coupon.CouponService;
import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Payment.PaymentService;
import com.example.demo.ecommerce.Review.CanNotFoundException;
import com.example.demo.ecommerce.User.UserModifyForm;
import com.example.demo.ecommerce.User.UserService;
import com.example.demo.lms.service.LmsCouponService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;


@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	private final UserService us;
	private final PaymentService ps;
	private final CouponService cs;
	private final LmsCouponService lcs;
	
	@GetMapping("/myorder")
	public String myOrderPage(Model model, Principal principal) throws CanNotFoundException {
//		User u = this.us.getUser(principal.getName());
		User u = this.us.getUser(1);
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
		User u = this.us.getUser(1);
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
//	테스트용 페이지이므로 나중에 삭제
	

	@PostMapping("/pwcheck")
	@ResponseBody
	public Map<String, Boolean> pwCheck(@RequestParam("password") String password,
	                                    Principal principal, HttpSession session) throws CanNotFoundException {
	    User u = this.us.getUser(1);
	    
	    boolean pwChecked = this.us.pwCheck(password, u.getPw());
//	    pwCheck 메서드를 활용해 pwChecked에 true false 값 저장
	    
	    Map<String, Boolean> data = new HashMap<>();
//	    Map 형식 인스턴스 data 생성
	    
	    data.put("pwChecked", pwChecked);
//	    data에 key pwChecked, 밸류 true, false를 저장
	    
	    return data;
//	    data 반환
	}
	
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/usermodify") 
	public String myInfoModifyPage(HttpSession session,
			Model model, Principal principal) throws CanNotFoundException  {
//		User user = this.us.getUser(principal.getName());
//		로그인 생기면 위에걸로 수정
		
		User user = this.us.getUser(1);
		
		UserModifyForm form = new UserModifyForm();
	    form.setName(user.getName());
	    form.setEmail1(user.getEmail().split("@")[0]);
	    form.setEmail2(user.getEmail().split("@")[1]);
	    form.setAddress1(user.getAddress().split(" ")[0]);
	    form.setAddress2(user.getAddress().split(" ", 2)[1]);
	    form.setAddressDetail(user.getAddressDetail());
	    form.setGender(user.getGender());
	    form.setTell(user.getTell());
//	    form에 현재 정보를 바로 가져오기 위한 코드
		
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

		
		this.us.userModify(user,
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
		
		User user = this.us.getUser(1);
		List<Coupon> coupon = this.cs.getCoupon(user.getUserId());
//		userId로 쿠폰을 조회하되 useYn 값이 n인 경우만 가져옴
		
		model.addAttribute("user", user);
		model.addAttribute("couponList", coupon);
		return "Mypage/mycoupon";
	}
	

	@PostMapping("/mycoupon/inputcoupon")
	public ResponseEntity<String> useCoupon(@RequestParam("code")String code)
			throws CanNotFoundException,CouponOverlappingException {
		
		if(lcs.existCoupon(code) && !lcs.useCheck(code)) {
			cs.createCoupon(code);
			lcs.useCoupon(code);
			System.out.println(lcs.existCoupon(code)+","+lcs.useCheck(code));
			return ResponseEntity.ok("쿠폰 정상 입력");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("쿠폰에러");
		}
	}

	@GetMapping("/signout")
	public String singoutPage(Model model, Principal principal) throws CanNotFoundException {
		User user = this.us.getUser(1);
		
		model.addAttribute("user", user);
		
		return "Mypage/signout";
	}

}
