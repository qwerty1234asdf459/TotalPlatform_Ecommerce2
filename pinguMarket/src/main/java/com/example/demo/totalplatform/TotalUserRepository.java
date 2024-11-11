package com.example.demo.totalplatform;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TotalUserRepository extends JpaRepository<TotalUser, Integer>{
	  
	@Query(value = "SELECT * FROM user where id like %:kw%", nativeQuery = true)
	TotalUser findById(@Param("kw")String username);

}
