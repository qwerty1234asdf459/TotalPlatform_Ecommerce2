package com.example.demo.ecommerce.Admin.Product;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ecommerce.Entity.AdminProduct;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Image.ImageService;
import com.example.demo.ecommerce.productimg.ProductImgService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminProductService {
	private final AdminProductRepository apr;

//상품조회는 ProductService에 있음

//상품등록
	public Product returnCreate(String name, String category, Integer price, Integer amount) {
		// 
		Product a = new Product();
		a.setName(name);
		a.setCategory(category);
		a.setPrice(price);
		a.setAmount(amount);
		a.setCancelYn("N");
		a.setUpdateDate(LocalDateTime.now());
		
		this.apr.save(a);
		return a;
	}


//상품 삭제 
	public void delete(Product p) {
		this.apr.delete(p);
		
	}
	
//	public AdminProduct Create(String name, String category, Integer price, Integer amount) {
//		// 
//		AdminProduct a = new AdminProduct();
//		a.setName(name);
//		a.setCategory(category);
//		a.setPrice(price);
//		a.setAmount(amount);
//		a.setUpdateDate(LocalDateTime.now());
//
//		
//		this.apr.save(a);
//	}
	
//	public AdminProduct returnCreate(String name, String category, Integer price, Integer amount) {
//		// 
//		AdminProduct a = new AdminProduct();
//		a.setName(name);
//		a.setCategory(category);
//		a.setPrice(price);
//		a.setAmount(amount);
//		a.setUpdateDate(LocalDateTime.now());
//
//		
//		this.apr.save(a);
//		return a;
//	}
	
}
