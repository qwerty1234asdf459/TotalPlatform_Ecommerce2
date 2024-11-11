package com.example.demo.ecommerce.Admin;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Admin.Inquiry.AdminCsQuestionRepository;
import com.example.demo.ecommerce.Admin.Review.AdminReviewRepository;
import com.example.demo.ecommerce.Admin.UserManagement.AdminUserRepository;
import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.Review;
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
	private final AdminUserRepository adminUserRepository;
	private final AdminCsQuestionRepository adminCsQuestionRepository;
	private final AdminReviewRepository avr;
	
	
	
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
	return this.cqr.countQuestionByAll(userId); //유저정보 강제 입력함. 추후 1 대신 user.getUserId()로 변경 필요
}


// ******************1:1문의하기 페이징 처리******************************************************
public List<CsQuestion> getUserByKeyword(Integer userId, int startNo, int pageSize) {
	User user = this.ur.searchUser(userId);
	return this.cqr.findQestionByUserId(userId, startNo, pageSize); // 유저정보 강제 입력함. 추후 1 대신 user.getUserId()로 변경 필요
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

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 조회 > 페이징 처리 및 검색 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<User> getUserByKeyword(String kwType, String kw, int startNo, int pageSize){
		
		switch(kwType) {
			case "total": return this.adminUserRepository.findAllByKeyword(kw, startNo, pageSize);
			case "id": return this.adminUserRepository.findAllByUserId(kw, startNo, pageSize);
			case "name": return this.adminUserRepository.findAllByUserName(kw, startNo, pageSize);
		}
		return this.adminUserRepository.findAllByKeyword(kw, startNo, pageSize);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 조회 > 검색어의 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getUserCountByKeyword(String kwType, String kw) {
		
		switch(kwType) {
			case "total": return this.adminUserRepository.countUserByKeyword(kw);
			case "id": return this.adminUserRepository.countUserById(kw);
			case "name": return this.adminUserRepository.countUserByName(kw);
		}
		
		return this.adminUserRepository.countUserByKeyword(kw);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 모든 회원 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<User> getUserList() {
		return this.adminUserRepository.findAll();
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 1명 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public User getUser(Integer userId) {
		
		Optional<User> user = this.adminUserRepository.findById(userId);
		
		return user.get();
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 리뷰 페이징 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Review> getReviewByKeyword(String kwType,String kw, int startNo, int pageSize) {
		switch(kwType) {
		case "total": return this.avr.findAllByKeyword(kw, startNo, pageSize);
		case "name": return this.avr.findAllByReviewName(kw, startNo, pageSize);
		case "userId": return this.avr.findAllByReviewProduct(kw, startNo, pageSize);
	}
		
		
		return this.avr.findAllByKeyword(kw, startNo, pageSize);
	}	

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 리뷰 전체조회  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
public int getReviewCountByKeyword(String kwType, String kw) {
		
		switch(kwType) {
			case "total": return this.avr.countReviewByKeyword(kw);
			case "name": return this.avr.countReviewByName(kw);
			case "userId": return this.avr.countReviewByProduct(kw);
		}
		
		
		return this.avr.countReviewByKeyword(kw);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 관리자 1대1 문의 전체 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<CsQuestion> getCsQuetionByKeyword(String kwType,String kw, int startNo, int pageSize) {
		switch(kwType) {
		case "total": return this.adminCsQuestionRepository.findAllByKeyword(kw, startNo, pageSize);
		case "title": return this.adminCsQuestionRepository.findAllByQuestionTitle(kw, startNo, pageSize);
		case "name": return this.adminCsQuestionRepository.findAllByUserName(kw, startNo, pageSize);
	}
		
		
		return this.adminCsQuestionRepository.findAllByKeyword(kw, startNo, pageSize);
	}	

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 관리자 1대1 페이징  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
public int getCsQuestionCountByKeyword(String kwType, String kw) {
		
		switch(kwType) {
			case "total": return this.adminCsQuestionRepository.countQuestionByKeyword(kw);
			case "title": return this.adminCsQuestionRepository.countQuestionByTitle(kw);
			case "name": return this.adminCsQuestionRepository.countQuestionByName(kw);
		}
		return this.adminCsQuestionRepository.countQuestionByKeyword(kw);
	}

	
	public CsQuestion getCsQuestion(Integer csQuestionId) {
		Optional<CsQuestion> question = this.adminCsQuestionRepository.findById(csQuestionId);
		return question.get();				

    }
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 주문/배송조회 검색기능  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Payment> getOrderByKeyword(String kwType,String kw) {
		switch(kwType) {
		case "total" : return this.pr.findAllByKeyword(kw);
		case "orderNum": return this.pr.findAllOrderNum(kw);
		case "orderUser": return this.pr.findAllByOrderUser(kw);
		case "receiver": return this.pr.findAllByReceiver(kw);
	}
		return this.pr.findAllByKeyword(kw);
	}	
	

}

