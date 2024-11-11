package com.example.demo.totalplatform;

import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class TotalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	private String id;
	private String pw;
	private String email;
	private String name;
	private String tel;

	private String gender;
	@Column(name = "create_date")
	private LocalDateTime createDate; //회원가입일
	
	private String address;
	
	@Column(name = "address_detail")
	private String addressDetail;
	
	@Column(name = "signout_yn")
	private String signoutYn; //회원탈퇴여부
	
	@Column(name = "refresh_token")
	private String refreshToken; //로그인 토큰
	
	private String role; //로그인 권한
	
	
	
	
	
}
