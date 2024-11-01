package com.example.demo.ecommerce.CsQuestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsQuestionService {
	
	private final CsQuestionRepository qr;
	private final CsQuestionUserRepository userRepository;
	
		
		public CsQuestion getQuestion(Integer id) throws UserException {
			Optional<CsQuestion> q1 = this.qr.findById(id);
			if(q1.isPresent()) {
				return q1.get();
			}else {
				throw new UserException("데이터를 찾을수 없습니다");
			}
		}

		public void create(String orderNo, String title, String contents, Integer id) throws UserException {
			CsQuestion q = new CsQuestion();
			q.setOrderNo(orderNo);
			q.setTitle(title);
			q.setContents(contents);
			q.setUpdateDate(LocalDateTime.now());
			q.setUser(getUser(id));
			this.qr.save(q);
		}

		public void modify(CsQuestion q, String orderNo, String title, String contents) {
			q.setTitle(title);
			q.setOrderNo(orderNo);
			q.setContents(contents);
			this.qr.save(q);
		}

		public void delete(CsQuestion q) {
			this.qr.delete(q);
		}
		
		//유저 정보 가져오기
		public User getUser(Integer id) throws UserException {
			Optional<User> user = this.userRepository.findById(id);
			
			if(user.isPresent()) { 
				return user.get();
			} else {
				throw new UserException("유저 정보가 없습니다.");
			}
		}

	
	
	
	

}
