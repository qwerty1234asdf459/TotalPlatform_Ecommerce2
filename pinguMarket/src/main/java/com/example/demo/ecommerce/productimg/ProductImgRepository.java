package com.example.demo.ecommerce.productimg;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.ProductImg;

public interface ProductImgRepository extends JpaRepository<ProductImg, Integer>{
	
}