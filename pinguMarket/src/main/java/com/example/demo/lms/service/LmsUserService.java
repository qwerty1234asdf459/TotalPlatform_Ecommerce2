package com.example.demo.lms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.LmsUser;
import com.example.demo.lms.repository.LmsUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LmsUserService {
	private final LmsUserRepository userRepository;
	
	public LmsUser getUser(String id) throws Exception {
		Optional<LmsUser> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new Exception("해당 유저가 존재하지 않습니다");
		}
	}
}
