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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Integer noticeId;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	private String title;
	
	private String contents;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<NoticeImg> noticeImgList;
	

}
