package com.example.demo.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.LmsUser;


public interface LmsUserRepository extends JpaRepository<LmsUser, Integer>{
	@Query(value = "SELECT * FROM user where id like %:kw%", nativeQuery = true)
	Optional<LmsUser> findById(@Param("kw")String username);
}
