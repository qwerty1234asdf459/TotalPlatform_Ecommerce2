package com.example.demo.ecommerce.Admin.Notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Notice;

public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {

	/* 관리자 기본키로 공지사항 조회 */
	@Query(value = "SELECT count(*) FROM notice" , nativeQuery = true)
	int countNoticeAll();

	@Query(value = "SELECT * FROM notice limit :start, :idx", nativeQuery = true)
	List<Notice> findNoticeByAdminId(@Param("start") int startNo, @Param("idx") int pageSize);
	
	

	/********************************** 글제목 또는 작성자로 공지글 조회 **********************************/
	@Query(value = "SELECT * FROM notice n  where n.title like %:kw% or n.admin_id like %:kw% limit :start, :idx", nativeQuery = true) 	
	List<Notice> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM notice n  where n.title like %:kw% or n.admin_id like %:kw%", nativeQuery = true)
	int countNoticeByKeyword(@Param("kw") String keyword);
	
	
	/********************************** 글제목으로 공지글 조회 **********************************/
	@Query(value = "SELECT * FROM notice n where n.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Notice> findAllByNoticeTitle(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM notice n where n.title like %:kw%" , nativeQuery = true)
	int countNoticeByTitle(@Param("kw") String keyword);

	
	
	/********************************** 관리자ID로 공지글 조회 **********************************/
	@Query(value = "SELECT * FROM notice n where n.admin_id like %:kw% limit :start, :idx", nativeQuery = true)
	List<Notice> findAllByNoticeAdmin(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM notice n where n.admin_id like %:kw%" , nativeQuery = true)
	int countNoticeByAdmin(@Param("kw") String keyword);

	Notice findByAdmin(Admin admin);
}
