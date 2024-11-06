package com.example.demo.ecommerce.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPwForm {
	
	@NotEmpty(message = "현재 비밀번호를 입력해주세요")
	private String prePassword;
	
	@NotEmpty(message = "바꿀 비밀번호를 입력해주세요")
	private String password;
	
	@NotEmpty(message = "비밀번호 확인을 해주세요")
	private String password2;
//	기능에 맞춰서 이름 변경

}