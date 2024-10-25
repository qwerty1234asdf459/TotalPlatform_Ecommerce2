package com.example.demo.ecommerce.Entity;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	private String deliveryno;

	private String address;
	
	private String name;
	
	private String tell;
	
	@Column(name = "payment_state")
	private String paymentState;
	@Column(name = "delivery_state")	
	private String deliveryState;
	
	@OneToMany(mappedBy = "payment", cascade = CascadeType.PERSIST)
	private List<PaymentDetail> paymentDetailList;
	
}
