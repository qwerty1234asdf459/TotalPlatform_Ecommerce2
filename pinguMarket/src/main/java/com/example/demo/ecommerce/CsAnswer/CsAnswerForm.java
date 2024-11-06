package com.example.demo.ecommerce.CsAnswer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsAnswerForm {
	
	@NotEmpty(message="제목이 없습니다")
	@Size(max=50)
	private String title;
	
	@NotEmpty(message="내용이 없습니다")
	private String contents;
	

}
