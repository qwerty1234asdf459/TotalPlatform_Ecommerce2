package com.example.demo.ecommerce.Cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Cart;



public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query(value = "SELECT *" +
			"FROM cart " +
			"WHERE " +
			" user_id = :ui" +
			" and product_id = :pi", nativeQuery = true)
	Optional<Cart> findByUserProduct(@Param("ui")Integer ui, @Param("pi")Integer pi);

}
