package com.example.demo.ecommerce.Admin.Inquiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.CsQuestion;

public interface AdminCsQuestionRepository extends JpaRepository<CsQuestion, Integer>{
	
	/* 커뮤니티 관리 검색 기능 */
	/********************************** 글제목 또는 작성자로 1대1 문의글 조회 **********************************/
	@Query(value = "SELECT c.* FROM cs_question c inner join user u on c.user_id = u.user_id where c.title like %:kw% or u.id like %:kw% limit :start, :idx", nativeQuery = true) 	
	List<CsQuestion> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(c.cs_question_id) FROM cs_question c inner join user u on c.user_id = u.user_id where c.title like %:kw% or u.name like %:kw%", nativeQuery = true)
	int countQuestionByKeyword(@Param("kw") String kw);
	
	
	/********************************** 글제목으로 1대1 문의글 조회 **********************************/
	@Query(value = "SELECT c.* FROM cs_question c where c.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<CsQuestion> findAllByQuestionTitle(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM cs_question c where c.title like %:kw%" , nativeQuery = true)
	int countQuestionByTitle(@Param("kw") String kw);
	
	/********************************** 이름으로 1대1 문의글 조회 **********************************/
	@Query(value = "SELECT c.* FROM cs_question c inner join user u on c.user_id = u.user_id where u.id like %:kw% limit :start, :idx", nativeQuery = true)
	List<CsQuestion> findAllByUserName(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	
	@Query(value = "SELECT count(cs_question_id) FROM cs_question c inner join user u on c.user_id = u.user_id where u.id like %:kw%", nativeQuery = true)
    int countQuestionByName(@Param("kw") String kw);	
}
