package com.example.demo.ecommerce.Admin.Notice;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminNoticeController {
	
	private final AdminNoticeService ans;
	private final AdminNoticeRepository anr;
	
	//---------------관리자페이지 > 공지사항 > 신규 등록 페이지-----------------------------
	//@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
		@GetMapping("/createnotice")
		public String createNotice() {
			return "/Admin/AdminNotice_create";
		}
		
		//@PreAuthorize(value = "isAuthenticated()")  //로그인 한 경우에만 요청 처리
		@PostMapping("/createnotice")
		public String createNotice(AdminNoticeForm adminNoticeForm, BindingResult bindingResult,
				HttpServletRequest request) throws Exception{
			 if (bindingResult.hasErrors()) {
			        return "/Admin/AdminNotice"; // 에러가 있는 경우 반환할 뷰
			    }
		//	 Member m = this.ms.getUser(principal.getName()); 추후 작성한 관리자 번호 불러오기
		//	 Notice a = this.ans.returnCreate(adminNoticeForm.getTitle(),adminNoticeForm.getContents());
		      
		       
		    // ic.thumbfileInsert(request, a);  // 추후 이미지 업로드 메소드
		    // ic.bannerfileInsert(request, a); // 
		     return "redirect:/createnotice"; //URL
		}
		
		//---------------관리자페이지 > 공지사항 > 상세 페이지-----------------------------
//		@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
//		@GetMapping("/admin/Notice/{noticeId}") 
//	    public String AdminNotice(Model model, @PathVariable("productId") Integer noticeId) {
//			//noticeId로 조회해서 가져오기
//			Notice n = this.ans.getNotice(noticeId);
//	        model.addAttribute("n", n);
//	        return "/Admin/AdminNotice_detail";
//	    }
		
		//---------------관리자페이지 > 공지사항 페이지(리스트)-----------------------------
//		@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
//		@GetMapping("/admin/Notice") 
//		public String notice(Model model) {
//	        List<Notice> NList = this.anr.findAll();
//	        model.addAttribute("NList", NList);
//	                          //" "안에 있는 값이 html에서 인식할 텍스트
//	        return "/Admin/AdminNotice";  
//		}
}
