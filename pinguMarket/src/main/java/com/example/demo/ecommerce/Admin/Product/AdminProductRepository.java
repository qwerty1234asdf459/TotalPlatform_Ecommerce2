package com.example.demo.ecommerce.Admin.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.Product;




public interface AdminProductRepository  extends JpaRepository<Product, Integer> {
													//프로덕트 엔티티
		
}
