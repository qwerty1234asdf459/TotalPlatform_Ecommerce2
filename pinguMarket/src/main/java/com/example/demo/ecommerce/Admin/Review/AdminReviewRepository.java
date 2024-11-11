package com.example.demo.ecommerce.Admin.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Review;
import com.example.demo.ecommerce.Entity.User;

public interface AdminReviewRepository extends JpaRepository<Review, Integer>{

	/********************************** 상품 또는 작성자로 공지글 조회 **********************************/
	@Query(value = "SELECT r.* FROM review r join product p on r.product_id = p.product_id join user u on r.user_id = u.user_id where p.name like %:kw% || u.id like %:kw% limit :start, :idx", nativeQuery = true) 	
	List<Review> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(r.product_id) FROM review r join product p on r.product_id = p.product_id join user u on r.user_id = u.user_id where p.name like %:kw% || u.name like %:kw%", nativeQuery = true)
	int countReviewByKeyword(@Param("kw") String keyword);
	
	
	/********************************** 상품으로 공지글 조회 **********************************/
	@Query(value = "select r.* from review r inner join product p on r.product_id = p.product_id where p.name like %:kw% limit :start, :idx", nativeQuery = true)
	List<Review> findAllByReviewName(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "select count(r.review_id) from review r inner join product p on r.product_id = p.product_id where p.name like %:kw%" , nativeQuery = true)
	int countReviewByName(@Param("kw") String keyword);
	
	Review findByUser(User user);
	
	/********************************** 작성자로 공지글 조회 **********************************/
	@Query(value = "SELECT r.* FROM review r inner join user on r.user_id = user.user_id where user.id like %:kw% limit :start, :idx", nativeQuery = true)
	List<Review> findAllByReviewProduct(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM review r inner join user on r.user_id = user.user_id where user.id like %:kw%" , nativeQuery = true)
	int countReviewByProduct(@Param("kw") String keyword);
	
	


}
