package com.example.demo.ecommerce.Paging;


import java.util.List;

import org.springframework.data.repository.query.Param;

public interface EzenPagingRepository {
	
	//@Query(value = "SELECT * FROM 테이블명 f limit :start, :idx", nativeQuery = true)
	abstract List<?> findLimitStartIdx(@Param("start") int start, @Param("idx") int idx); // start번째 row부터 idx만큼 조회
	
	
	//@Query(value = "SELECT * FROM 테이블명 f where f.title like %:kw% limit :start, :idx", nativeQuery = true)
	abstract List<?> findAllByKeyword(@Param("kw") String keyword, @Param("start") int start, @Param("idx") int idx);
	
	//@Query(value = "SELECT count(*) FROM 테이블명 f", nativeQuery = true)  // 테이블의 모든 row 갯수 조회
			  
	abstract int countFilm();
	
	//@Query(value = "SELECT count(*) FROM 테이블 f where f.title like %:kw%" , nativeQuery = true)  // 테이블의 모든 row 갯수 조회
			 
	abstract int countFilmByKeyword(@Param("kw") String keyword);
}
