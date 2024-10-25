package com.example.demo.ecommerce.Product;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByProductIdIn(List<String> productIds); // 장바구니 구현용


}
