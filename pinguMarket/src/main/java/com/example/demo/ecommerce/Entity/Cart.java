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
@Table(name = "cart")
public class Cart {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Integer cartId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 회원
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; // 제품
	
	@Column(name = "product_count", nullable = false)
	private Integer productCount; // 수량
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;

}
