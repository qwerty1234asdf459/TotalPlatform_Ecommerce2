package com.example.demo.lms;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class LmsUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	private String id;
	private String pw;
	private String email;
	private String name;
	private String tel;
	private String birth;
	private String gender;
	
	@Column(name = "create_date")
	private LocalDateTime createDate; //회원가입일
	
	@Column(name = "banned_yn")
	private String bannedYn; //정지여부
	
	@Column(name = "signout_yn")
	private String signoutYn; //회원탈퇴여부
	
}






