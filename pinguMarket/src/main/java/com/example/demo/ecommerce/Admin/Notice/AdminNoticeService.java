package com.example.demo.ecommerce.Admin.Notice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.Notice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminNoticeService {
	private final AdminNoticeRepository anr;

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
}
