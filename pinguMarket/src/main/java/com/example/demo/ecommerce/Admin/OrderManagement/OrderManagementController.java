package com.example.demo.ecommerce.Admin.OrderManagement;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Payment.PaymentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class OrderManagementController {
	
	//private final PaymentService ps;
	private final PaymentRepository pr;

	//---------------관리자페이지 > 주문/배송 관리-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/OrderManagement") 
	public String AdminOrderManagement(Model model) {
        List<Payment> PayList = this.pr.findAll();
        model.addAttribute("PayList", PayList);
        return "/Admin/AdminOrderManagement";  
	}
}
