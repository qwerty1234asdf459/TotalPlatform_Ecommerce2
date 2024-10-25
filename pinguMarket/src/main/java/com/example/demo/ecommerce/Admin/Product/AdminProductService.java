package com.example.demo.ecommerce.Admin.Product;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminProductService {
	private final AdminProductRepository apr;



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
