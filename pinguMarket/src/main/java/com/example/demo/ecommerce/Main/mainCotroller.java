package com.example.demo.ecommerce.Main;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.LoginCheck.LoginCheck;
import com.example.demo.ecommerce.Authuser.Authuser;
import com.example.demo.ecommerce.Authuser.AuthuserService;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.User.UserRepository;
import com.example.demo.totalplatform.TotalUser;
import com.example.demo.totalplatform.TotalUserRepository;
import com.example.demo.totalplatform.TotalUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class mainCotroller {

	
	private final UserRepository userRepository;
	private final AuthuserService authuserService;
	private final TotalUserRepository totalUserRepository;
	private final TotalUserService totalUserService;
	
	//---------------------MAIN PAGE--------------------------
		@LoginCheck
		@GetMapping("/main")
		public String mainpage(@Authuser User user) {
			if(user.getId().equals("null")) {
				return "Main/mainPage";
			}
			
			if(userRepository.findById(user.getId()).isEmpty()) {
				return "User/confirm";
			}
			
			return "Main/mainPage";
			}

		
		@GetMapping("/")
		public String in1dex(@Authuser User user)  {
			
			
			
			
			return "redirect:/main";
		}
		
		@PostMapping("/confirm")
		public ResponseEntity<String> agreePage(HttpServletRequest request) {
			String jwtToken = request.getHeader("Authorization");
			System.out.println(jwtToken);
			ResponseEntity<String> user = authuserService.getNameWithHeader(jwtToken).block();
	    	List<String> listHeader = user.getHeaders().get("Principal");
	    
	    	
	    	String userId =  listHeader.get(0);
	    	TotalUser TotalUser =  totalUserRepository.findById(userId);
			User userbyTotalUser = totalUserService.returncreate(TotalUser);
			userRepository.save(userbyTotalUser);
			
			return ResponseEntity.ok("성공");
		}
	
}
