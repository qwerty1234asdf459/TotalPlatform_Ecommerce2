package com.example.demo.ecommerce.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	
	private final UserRepository ur;
	
	public User getUser(String userId) throws CanNotFoundException {
		Optional<User> user = this.ur.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new CanNotFoundException("존재하지 않는 유저입니다");
		}
	}

	public User getUser(Integer id) throws CanNotFoundException {
		Optional<User> user = this.ur.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new CanNotFoundException("존재하지 않는 유저입니다");
		}
	}
//	로그인 기능이 없어서 String이 아니라 Integer를 사용하여 땜빵용
	
}
