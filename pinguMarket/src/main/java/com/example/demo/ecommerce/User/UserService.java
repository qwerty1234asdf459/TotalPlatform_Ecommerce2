package com.example.demo.ecommerce.User;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	
	private final UserRepository ur;
	private final PasswordEncoder passwordEncoder;
	
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
	

	public void userModify(User user,
			String email1, String email2,
			String address1,String address2,String addressDetail,
			String gender, String tell) {
			user.setEmail(email1+"@"+email2);
//			bu.setRegisterDate(LocalDateTime.now());
//			이 RegisterDate는 가입일이라서 수정일 변수가 필요
		    user.setAddress(address1+" "+address2);
		    user.setAddressDetail(addressDetail);
			user.setGender(gender);
			user.setTell(tell);
			this.ur.save(user);
		}
//	     회원정보 바꾸는 메서드
	
//	public String pwEncode(String password) {
//		return passwordEncoder.encode(password);
//	}
//	비밀번호 확인용 비밀번호 암호화 메서드인데 딱히 쓸데가 없어서 주석처리
	
	public boolean pwCheck(String password, String pw) {
		return passwordEncoder.matches(password, pw);
		
	}

	public void userSignout(User user, String signoutYn) {
		user.setSignoutYn(signoutYn);
		this.ur.save(user);
		
	}
	
}
