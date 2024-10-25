package com.example.demo.ecommerce.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "객체가 존재하지 않습니다")
public class nosignException extends Exception {
	public nosignException(String msg) {
		super(msg);
	}

}
