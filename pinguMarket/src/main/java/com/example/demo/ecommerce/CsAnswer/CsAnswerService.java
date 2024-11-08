package com.example.demo.ecommerce.CsAnswer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Admin.AdminRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.CsQuestion.UserException;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsAnswerService {

	private final CsAnswerRepository car;
//	private final CsQuestionUserRepository ur;
	private final AdminRepository ar;
	private final CsQuestionRepository qr;
	
	
//	public CsAnswer getCsAdminAnswer(Integer csQuestionId) throws UserException { //throws UserException맞나?
//		//csAnswerId로 답변 조회
//		Optional<CsAnswer> a1 = this.car.findByCsAnswerId(csQuestionId);
//		if(a1.isPresent()) {
//			return a1.get();
//		}else {
//			throw new UserException("데이터를 찾을수 없습니다");
//		}
//	}
	
	public CsAnswer getCsAnswer(Integer questionId) {
	
		CsQuestion question = this.qr.findById(questionId).get();
		
		return this.car.findByCsQuestion(question).get();
	}
	
	public void returnCreate(String adCode, CsQuestion q, String title, String contents) throws UserException {
		// 답변(신규) 생성
		CsAnswer csAnswer = new CsAnswer();
		csAnswer.setAdmin(getAdmin(adCode));
		csAnswer.setCsQuestion(q);
		csAnswer.setTitle(title);
		csAnswer.setContents(contents);
		csAnswer.setUpdateDate(LocalDateTime.now());
		this.car.save(csAnswer);
		
	}
	

	private Admin getAdmin(String adCode) throws UserException {
		// 관리자(작성자) 정보 가져오기
		Optional<Admin> admin = this.ar.findByAdCode(adCode);
		
		if(admin.isPresent()) { 
			return admin.get();
		} else {
			throw new UserException("존재하지 않는 관리자입니다.");
		}
	}

	
	public void update(CsAnswer n, String title, String contents) {
		// 답변 수정
	//	n.setAdmin(getAdmin(adminId));
	//	n.setCsQuestion(q);
		n.setTitle(title);
		n.setContents(contents);
		n.setUpdateDate(LocalDateTime.now());
		this.car.save(n);
		
	}
	
	public void delete(CsAnswer a) {
		//답변만 삭제
		this.car.delete(a);
	}
}
