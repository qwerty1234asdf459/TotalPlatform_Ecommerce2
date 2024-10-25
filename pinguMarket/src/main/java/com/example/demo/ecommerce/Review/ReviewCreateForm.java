package com.example.demo.ecommerce.Review;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateForm {
	
	@NotEmpty(message = "별점을 선택해주세요.")
	private String scope;
	
	@NotEmpty(message = "제목을 작성해주세요.")
	private String title;
	
	@NotEmpty(message = "내용을 작성해주세요.")
	private String contents;
}
