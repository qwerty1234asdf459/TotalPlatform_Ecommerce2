package com.example.demo.ecommerce.Entity;

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
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Integer adminId;
	
	@Column(name = "ad_code")
	private String adCode;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST)
	private List<CsAnswer> csAnswerList;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST)
	private List<AdminProduct> adminProductList;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST)
	private List<Notice> noticeList;
	
}
