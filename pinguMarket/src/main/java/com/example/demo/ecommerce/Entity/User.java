package com.example.demo.ecommerce.Entity;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;	
	
	private String id;
	
	private String pw;
	
	private String email;
	
	private String name;
	
	private String tell;
	
	private String address;
	
	@Column(name = "address_detail")
	private String addressDetail;
	
	private String gender;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "signout_yn")
	private String signoutYn;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Payment> paymentList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Coupon> couponList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Review> reviewList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Cart> cartList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<CsQuestion> csQuestionList;
	
	
}
