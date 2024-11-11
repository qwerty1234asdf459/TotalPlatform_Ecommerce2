package com.example.demo.ecommerce.Payment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.ecommerce.Entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	List<Payment> findByUser_UserId(Integer userId);
	
	/*  관리자 사용 > 유저 ID(기본키)로 결제 테이블 조회  */
	@Query(value = "SELECT * FROM payment WHERE user_id = :id" , nativeQuery = true)
	List<Payment> findByUserId(@Param("id") Integer userId);
	
//	@Query(value ="select u.user_id, p.payment_id, pd.pdetail_id, pd.product_id, pr.name "
//			+ "from user u "
//			+ "inner join payment p "
//			+ "on u.user_id = p.user_id "
//			+ "inner join payment_detail pd "
//			+ "on p.payment_id = pd.payment_id "
//			+ "inner join product pr "
//			+ "on pd.product_id = pr.product_id "
//			+ "where pd.pdetail_id = ( "
//			+ "    select min(pdetail_id) "
//			+ "    from payment_detail "
//			+ "    where payment_id = p.payment_id and "
//			+ "    u.user_id = 2)", nativeQuery = true)
//	List<Payment> findByUserId(@Param("userId") Integer userId);
	
	@Query(value = "select * "
			+ "from payment "
			+ "where create_date >= now() - interval :period month "
			+ "and user_id = :userId", nativeQuery = true)
	List<Payment> findByUserCreate(@Param("userId") Integer userId, @Param("period") Integer period);
	
	Payment findByOrderNo(String orderNo);

	

	/********************************** 검색어 : 전체(주문번호 or 주문자ID or 수취인) 조회 **********************************/
	@Query(value= "select * from payment p where p.order_no like %:kw% or p.user_id like %:kw% or p.name like %:kw%", nativeQuery = true)
	List<Payment> findAllByKeyword(@Param("kw") String kw);
	

	/********************************** 검색어 : 주문번호로조회 **********************************/
	@Query(value = "SELECT * FROM payment p where p.order_no like %:kw%", nativeQuery = true)
	List<Payment> findAllOrderNum(@Param("kw") String kw);
	
	/********************************** 검색어 : 주문자ID로 회원 리스트 조회 **********************************/
	@Query(value = "SELECT * FROM payment p where p.user_id like %:kw%", nativeQuery = true)
	List<Payment> findAllByOrderUser(@Param("kw") String kw);

	/********************************** 검색어 : 수취인으로 조회 **********************************/
	@Query(value = "SELECT * FROM payment p where p.name like %:kw%", nativeQuery = true)
	List<Payment> findAllByReceiver(@Param("kw") String kw);
}
