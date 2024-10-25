package com.example.demo.ecommerce.Admin.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ecommerce.Admin.Notice.AdminNoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	private final AdminLoginService als;
	private final AdminNoticeService ans;
	
	@GetMapping("/login") //로그인 
    public String AdminLogin() {
        return "/Admin/AdminLogin";
    }
	
	@GetMapping("/main") //관리자 페이지의 메인 페이지인 대시보드 
    public String AdminMain() {
        return "/Admin/AdminDashboard";
    }

	@GetMapping("/UserManagement") //회원 관리
    public String AdminUserManagement() {
        return "/Admin/AdminUserManagement";
    }
	
	@GetMapping("/OrderManagement") //주문 배송관리 
    public String AdminOrderManagement() {
        return "/Admin/AdminOrderManagement";
    }
	
	@GetMapping("/Product") //상품 관리 
    public String AdminProduct() {
        return "/Admin/AdminProduct";
    }
	
	@GetMapping("/Inquiry") //문의 관리
    public String AdminInquiry() {
        return "/Admin/AdminInquiry";
    }
	
	@GetMapping("/Reviews") //리뷰 관리
    public String AdminReviews() {
        return "/Admin/AdminReviews";
    }
	

	
}
