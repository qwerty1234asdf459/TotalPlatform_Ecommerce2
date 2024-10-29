package com.example.demo.ecommerce.Admin.UserManagement;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ecommerce.Admin.Login.AdminLoginService;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeService;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.User.UserRepository;
import com.example.demo.ecommerce.User.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class AdminUserManagementController {
	
	//private final UserService us;
	private final UserRepository ur;

	//---------------회원 관리 페이지(리스트)-----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/UserManagement") 
	public String AdminUserManagement(Model model) {
        List<User> UList = this.ur.findAll();
        model.addAttribute("UList", UList);
                          //" "안에 있는 값이 html에서 인식할 텍스트
        return "/Admin/AdminUserManagement";  
	}
}
