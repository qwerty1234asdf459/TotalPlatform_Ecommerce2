package com.example.demo.ecommerce.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cs_answer")
public class CsAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cs_answer_id")
	private Integer csAnswerId;
	
	@OneToOne
	@JoinColumn(name = "cs_question_id")
	private CsQuestion csQuestion;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	private String title;
	
	private String contents;
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	


}
