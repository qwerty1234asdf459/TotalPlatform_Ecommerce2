package com.example.demo.ecommerce.Admin;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final CsQuestionRepository cqr;
	private final CsAnswerRepository car;
	private final AdminRepository ar;
	
	
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


///////////////////////////////////////////////////////////////////
	public Admin getAdmin(Integer adminId) throws CanNotFoundException {
		
		Optional<Admin> admin = this.ar.findByAdminId(adminId);
		if(admin.isPresent()) {
			return admin.get();
		}
		else {
			throw new CanNotFoundException("존재하지 않는 관리자입니다");
		}
	}

	//	관리자 로그인 기능이 없어서 String이 아니라 Integer를 사용하여 땜빵용
	public Admin getAdmin(String adCode) throws CanNotFoundException {
		Optional<Admin> admin = this.ar.findByAdCode(adCode);
		if(admin.isPresent()) {
			return admin.get();
		}
		else {
			throw new CanNotFoundException("존재하지 않는 유저입니다");
		}
	}
	
	
	

}

