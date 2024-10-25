package com.example.demo.ecommerce.Admin.Notice;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNoticeForm {
	
	@NotEmpty
	private String title; //공지사항 제목
	
	@NotEmpty
	private String contents; //공지사항 내용
	

}
