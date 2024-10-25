package com.example.demo.ecommerce.Admin.Product;

import org.springframework.web.multipart.MultipartFile;



import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminProductForm {
	//폼 : 유효성 검사를 위람

	//Product 엔티티---------------------------------
	@NotEmpty
	private String name; //상품명
	
	@NotEmpty
	private String category; //상품카테고리
	
	@NotEmpty
	private Integer price; //상품가격
	
	@NotEmpty
	private Integer amount; //상품수량

	//@NotEmpty
	//private MultipartFile mainImg; //대표이미지
	
	//@NotEmpty
	//private MultipartFile detailImg; //상세이미지
	
}
