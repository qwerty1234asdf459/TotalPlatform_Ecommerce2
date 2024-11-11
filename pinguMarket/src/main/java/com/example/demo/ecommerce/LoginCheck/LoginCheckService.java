package com.example.demo.ecommerce.LoginCheck;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LoginCheckService {

	public String checkToken(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
        	for(Cookie c : cookies) {
        		String name = c.getName();
        		String value = c.getValue();
        		if(name.equals("jwtToken")) {
        			return value;
        		}
        	}
        }
        return null;
	}
	
}
