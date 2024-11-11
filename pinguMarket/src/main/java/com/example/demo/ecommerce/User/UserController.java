package com.example.demo.ecommerce.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	
	
	@GetMapping("user/login")
	public String SignIn() {
		return "Login/loginPage";
	}
	
	
	// 회원가입창
	@GetMapping("/user/sign")
	public String userCreate() {
			
		return "/user/signPage";
	}
	
	
	@PostMapping("/JwtCookie/request")
    public ResponseEntity<String> jwtreq( HttpServletResponse response) {
    	String jwtToken = response.getHeader("jwtToken");
    	Cookie cookie = new Cookie("jwtToken",jwtToken);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		// 30초간 저장
		cookie.setMaxAge(30*60);
		cookie.setSecure(true);
		response.addCookie(cookie);
			
    	return ResponseEntity.ok("쿠키 전송이 완료되었습니다.");
    }
}
