package com.example.demo.ecommerce.Admin.Login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLogin {
	
	@NotEmpty
	private String adCode; //관리자코드

}
