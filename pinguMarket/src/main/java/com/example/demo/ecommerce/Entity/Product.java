package com.example.demo.ecommerce.Entity;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	private String name;
	
	private String category;
	
	private Integer price;
	
	private Integer amount; //수량
	
	@Column(name = "cancel_yn")
	private String cancelYn;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductImg> productImgList;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Review> reviewList;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Cart> cartList;
}
