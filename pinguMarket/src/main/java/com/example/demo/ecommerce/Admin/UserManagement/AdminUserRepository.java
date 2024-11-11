package com.example.demo.ecommerce.Admin.UserManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.User;

public interface AdminUserRepository extends JpaRepository<User, Integer>{

	/********************************** 검색어 : ID 또는 이름으로 회원 리스트 조회 **********************************/
	@Query(value = "SELECT * FROM user u where u.id like %:kw% or u.name like %:kw% limit :start, :idx", nativeQuery = true)
	List<User> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM user u where u.id like %:kw% or u.name like %:kw%" , nativeQuery = true)  // 테이블의 모든 row 갯수 조회
	int countUserByKeyword(@Param("kw") String keyword);
	
	
	/********************************** 검색어 : ID로 회원 리스트 조회 **********************************/
	@Query(value = "SELECT * FROM user u where u.id like %:kw% limit :start, :idx", nativeQuery = true)
	List<User> findAllByUserId(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM user u where u.id like %:kw%" , nativeQuery = true)
	int countUserById(@Param("kw") String keyword);

	
	/********************************** 검색어 : 이름으로 회원 리스트 조회 **********************************/
	@Query(value = "SELECT * FROM user u where u.name like %:kw% limit :start, :idx", nativeQuery = true)
	List<User> findAllByUserName(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM user u where u.name like %:kw%" , nativeQuery = true)
	int countUserByName(@Param("kw") String keyword);

	
	User findById(String userId);	
}
