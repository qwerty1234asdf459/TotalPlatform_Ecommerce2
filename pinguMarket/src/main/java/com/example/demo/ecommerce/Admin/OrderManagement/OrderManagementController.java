package com.example.demo.ecommerce.Admin.OrderManagement;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Payment.PaymentRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class OrderManagementController {
	
	//private final PaymentService ps;
	private final PaymentRepository pr;
	private final AdminService as;

	//---------------관리자페이지 > 주문/배송 관리-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/OrderManagement") 
	public String AdminOrderManagement(Model model,
			@RequestParam(value = "kw", defaultValue = "") String kw,
			@RequestParam(value = "kwType", defaultValue = "") String kwType,
			HttpSession session) {
		
       // List<Payment> PayList = this.pr.findAll();
		List<Payment> PayList = this.as.getOrderByKeyword(kwType, kw);
		
        model.addAttribute("PayList", PayList);
        return "/Admin/AdminOrderManagement";  
	}
}
