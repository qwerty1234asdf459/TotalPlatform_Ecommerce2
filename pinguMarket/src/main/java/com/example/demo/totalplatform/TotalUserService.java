package com.example.demo.totalplatform;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TotalUserService {



	
	public User returncreate(TotalUser totalUser) {
		
		User user = new User();
		
		
		user.setId(totalUser.getId());
		user.setEmail(totalUser.getEmail());
		user.setPw(totalUser.getPw());
		user.setName(totalUser.getName());
		user.setTell(totalUser.getTel());
		user.setGender(totalUser.getGender());
		user.setAddress(totalUser.getAddress());
		user.setAddressDetail(totalUser.getAddressDetail());
		user.setSignoutYn(totalUser.getSignoutYn());
		user.setCreateDate(LocalDateTime.now());
		
		
		return user;
	}
}
