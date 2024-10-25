package com.example.demo.ecommerce.Admin;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.lms.LmsAdminUserRepository;
import com.example.demo.lms.LmsUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final CsQuestionRepository cqr;
	private final CsAnswerRepository car;
	
	public Page<CsQuestion> getQuestionList(int page, String kw, String kwType){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("updateDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		switch(kwType) {
			case "total": return this.cqr.findAllByTitleOrId(kw, pageable);
			case "id" : return this.cqr.findAllById(kw, pageable);
			case "title" : return this.cqr.findAllByTitle(kw, pageable);
		
		}
		
		return this.cqr.findAllByTitleOrId(kw, pageable);
		
	}
	
	public CsQuestion getQuestion(Integer id) {
		return this.cqr.findById(id).get();
	}
	
	public void answerCreate(String title, String contents, CsQuestion question) {
		CsAnswer ca = new CsAnswer();
		ca.setTitle(title);
		ca.setContents(contents);
		ca.setUpdateDate(LocalDateTime.now());
		ca.setCsQuestion(question);
		
		this.car.save(ca);
	}
	
	public void answerDelete(Integer id) {
		this.car.deleteById(id);
	}
	
	public CsAnswer getAnswer(Integer id) {
		return this.car.findById(id).get();
	}
	
public void answerUpdate(String title, String contents, Integer id) {
		
		CsAnswer ca  = this.car.findById(id).get();
		
		ca.setTitle(title);
		ca.setContents(contents);
		ca.setUpdateDate(LocalDateTime.now());
		
		this.car.save(ca);
	}
	
	

}

