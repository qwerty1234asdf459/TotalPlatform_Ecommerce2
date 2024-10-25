package com.example.demo.ecommerce.Entity;

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

@Getter
@Setter
@Entity
@Table(name = "coupon")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private Integer couponId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String code;
	
	@Column(name = "use_yn")
	private String useYn;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	private Integer discount;

}
