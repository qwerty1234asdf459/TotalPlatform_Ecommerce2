package com.example.demo.ecommerce.CsAnswer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;



public interface CsAnswerRepository extends JpaRepository<CsAnswer, Integer>{

	Page<CsAnswer> findAllByCsAnswerId(Integer csAnswerId, Pageable pageable);
	
	Page<CsAnswer> findAllByTitle(String title, Pageable pageable);
	
	Page<CsAnswer> findAllByTitleOrCsAnswerId(String title, Integer csAnswerId, Pageable pageable);
	
	Optional<CsAnswer> findByCsAnswerId(Integer csAnswerId); //해당 csAnswerId이 있는지 체크
	
	//추가
	Optional<CsAnswer> findByCsQuestion(CsQuestion csQuestion);

}
