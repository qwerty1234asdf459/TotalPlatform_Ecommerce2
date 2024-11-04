package com.example.demo.ecommerce.Admin.Notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Admin.AdminRepository;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.Notice;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminNoticeService {
	private final AdminNoticeRepository anr;
	private final AdminRepository ar;

	public Notice returnCreate(Admin admin, String title, String contents) {
		Notice n = new Notice();
		n.setAdmin(admin);
		n.setTitle(title);
		n.setContents(contents);
		n.setUpdateDate(LocalDateTime.now());

		
		this.anr.save(n);
		return n;
	}
	
	
	
	//---------------	noticeId값으로 공지사항 데이터 조회---------------------
	public Notice getNotice(Integer noticeId) { 
        Optional<Notice> n = this.anr.findById(noticeId);
        return n.get();
	}

	public void updateNotice(Notice notice) {
		anr.save(notice);
		
	}


	//공지사항 수정 데이터 저장
	public void update(Notice n, String title, String contents) {
		n.setTitle(title);
		n.setContents(contents);
		n.setUpdateDate(LocalDateTime.now());
		this.anr.save(n);
	}


// **************************고객센터 > 공지사항 list 불러오기 **************************
	public List<Notice> findAll() {	
		return this.anr.findAll();
	}


	
	
	//**************************고객센터 > 공지사항 총 count 조회***************************************************
	public int getNoticeCountAll() {
		return this.anr.countNoticeAll(); //유저정보 강제 입력함. 
	}
	
	//**************************고객센터 > 공지사항 count 조회***************************************************

	public List<Notice> getNoticePaging(int startNo, int pageSize) {
		return this.anr.findNoticeByAdminId(startNo, pageSize);
	}






//---------------	공지사항 삭제  ---------------------
	public void delete(Notice n) {
		this.anr.delete(n);
		
	}

}
