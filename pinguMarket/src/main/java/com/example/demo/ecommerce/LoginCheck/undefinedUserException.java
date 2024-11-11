package com.example.demo.ecommerce.LoginCheck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason = "User is undefined")
public class undefinedUserException extends Exception {

	public undefinedUserException(String msg) {
		super(msg);
	}
	
	
}
