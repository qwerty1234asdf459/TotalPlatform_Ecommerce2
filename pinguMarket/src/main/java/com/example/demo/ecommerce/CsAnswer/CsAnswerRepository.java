package com.example.demo.ecommerce.CsAnswer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.CsAnswer;



public interface CsAnswerRepository extends JpaRepository<CsAnswer, Integer>{

	Page<CsAnswer> findAllByCsAnswerId(Integer csAnswerId, Pageable pageable);
	
	Page<CsAnswer> findAllByTitle(String title, Pageable pageable);
	
	Page<CsAnswer> findAllByTitleOrCsAnswerId(String title, Integer csAnswerId, Pageable pageable);

}
