package com.example.demo.ecommerce.Admin;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Payment.PaymentRepository;
import com.example.demo.ecommerce.User.UserRepository;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final CsQuestionRepository cqr;
	private final CsAnswerRepository car;
	private final UserRepository ur;
	private final AdminRepository ar;
	private final PaymentRepository pr;
	
	
	public CsQuestion getQuestion(Integer id) {
		return this.cqr.findById(id).get();
	}
	//CsAnswerService과 중복되는 내용 주석처리
//	public void answerCreate(String title, String contents, CsQuestion question) {
//		CsAnswer ca = new CsAnswer();
//		ca.setTitle(title);
//		ca.setContents(contents);
//		ca.setUpdateDate(LocalDateTime.now());
//		ca.setCsQuestion(question);
//		
//		this.car.save(ca);
//	}
	
//	public void answerDelete(Integer id) {
//		this.car.deleteById(id);
//	}
//	
//	public CsAnswer getAnswer(Integer id) {
//		return this.car.findById(id).get();
//	}
	
//public void answerUpdate(String title, String contents, Integer id) {
//		
//		CsAnswer ca  = this.car.findById(id).get();
//		
//		ca.setTitle(title);
//		ca.setContents(contents);
//		ca.setUpdateDate(LocalDateTime.now());
//		
//		this.car.save(ca);
//	}



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

	//**************************유저가 로그인한 아이디로 Payment 리스트를 조회**************************
	public List<Payment> getPaymentList(String userId) {
		
		User user = this.ur.findById(userId).get();	
		return this.pr.findByUserId(user.getUserId());
	}

	
	

}

