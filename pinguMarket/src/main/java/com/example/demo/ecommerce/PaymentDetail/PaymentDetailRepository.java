package com.example.demo.ecommerce.PaymentDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer>{

	@Query(value = "select pd.* "
			+ "from payment_detail pd "
			+ "inner join payment p "
			+ "on pd.payment_id = p.payment_id "
			+ "where user_id = :userId "
			+ "and product_id = :productId "
			+ "limit 1", nativeQuery = true)
	Optional<PaymentDetail> findByUserProduct(@Param("userId") Integer userId, @Param("productId") Integer productId);
	
}
