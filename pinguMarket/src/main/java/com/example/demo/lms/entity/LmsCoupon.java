package com.example.demo.lms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coupon")
@Getter
@Setter
public class LmsCoupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private Integer couponId;
	
	private String code; //쿠폰 코드
	@Column(name = "use_yn")
	private String useYn; //사용 여부
	private Integer discount; //할인금액
	@Column(name = "create_date")
	private LocalDateTime createDate; //쿠폰코드 발급 날짜
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private LmsUser user;
	
}