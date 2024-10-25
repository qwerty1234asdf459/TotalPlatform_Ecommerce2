package com.example.demo.ecommerce.CsQuestion;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.User;


public interface CsQuestionRepository extends JpaRepository<CsQuestion, Integer>{
	
	
	Page<CsQuestion> findAll(Pageable pageable);
	Page<CsQuestion> findByTitleContaining(String keyword, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "inner join cs.user u " 
            + "where cs.title like %:kw% " 
            + "or u.id like %:kw%")
	Page<CsQuestion> findAllByTitleOrId(@Param("kw") String kw, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "where cs.title like %:kw%")
	Page<CsQuestion> findAllByTitle(@Param("kw") String kw, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "inner join cs.user u " 
            + "where u.id like %:kw%")
	Page<CsQuestion> findAllById(@Param("kw") String kw, Pageable pageable);
	
	
	Page<CsQuestion> findByUser(User user, Pageable pageable);
	

}
