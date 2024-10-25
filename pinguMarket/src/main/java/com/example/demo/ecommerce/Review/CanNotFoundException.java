package com.example.demo.ecommerce.Review;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "객체 없음")
public class CanNotFoundException extends Exception {
	
	public CanNotFoundException(String msg) {
		super(msg);
	}
}
