package com.example.demo.ecommerce.Admin.Notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Notice;

public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {

	/* 관리자 기본키로 공지사항 조회 */
	@Query(value = "SELECT count(*) FROM notice" , nativeQuery = true)
	int countNoticeAll();

	@Query(value = "SELECT * FROM notice limit :start, :idx", nativeQuery = true)
	List<Notice> findNoticeByAdminId(@Param("start") int startNo, @Param("idx") int pageSize);




}
