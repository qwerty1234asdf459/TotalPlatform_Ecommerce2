package com.example.demo.ecommerce.Admin.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Product;


public interface AdminProductRepository  extends JpaRepository<Product, Integer> {

	/********************************** 검색어 : 이름으로 회원 리스트 조회 **********************************/
	@Query(value = "SELECT * FROM product p where p.name like %:kw%", nativeQuery = true)
	List<Product> findAllByProductName(@Param("kw") String keyword);
	
}
