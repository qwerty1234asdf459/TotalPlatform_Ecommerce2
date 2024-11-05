package com.example.demo.ecommerce.CsQuestion;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.CsQuestion;


public interface CsQuestionRepository extends JpaRepository<CsQuestion, Integer>{
	
	/* 유저 기본키로 1:1 문의글 조회 */
	@Query(value = "SELECT count(*) FROM cs_question WHERE user_id = :id" , nativeQuery = true)
	int countQuestionByAll(@Param("id") Integer userId);
	
	@Query(value = "SELECT * FROM cs_question WHERE user_id = :id limit :start, :idx", nativeQuery = true)
	List<CsQuestion> findQestionByUserId(@Param("id") Integer userId, @Param("start") int startNo, @Param("idx") int pageSize);
	
	

}
