package com.example.demo.ecommerce.Admin;


import java.time.LocalDateTime;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.User.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final CsQuestionRepository cqr;
	private final CsAnswerRepository car;
	private final UserRepository ur;
	
	
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


//**************************1:1 문의글 count 조회***************************************************

public int getQuestionCountByAll(Integer userId) {
	User user = this.ur.searchUser(userId);
	return this.cqr.countQuestionByAll(1); //유저정보 강제 입력함. 추후 1 대신 user.getUserId()로 변경 필요
}


// ******************1:1문의하기 페이징 처리******************************************************
public List<CsQuestion> getUserByKeyword(Integer userId, int startNo, int pageSize) {
	User user = this.ur.searchUser(userId);
	return this.cqr.findQestionByUserId(1, startNo, pageSize); // 유저정보 강제 입력함. 추후 1 대신 user.getUserId()로 변경 필요
}

	
	

}

