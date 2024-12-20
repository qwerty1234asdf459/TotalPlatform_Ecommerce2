package com.example.demo.ecommerce.Admin.Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Paging.EzenPaging;
import com.example.demo.ecommerce.Review.CanNotFoundException;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminNoticeController {
	
	private final AdminNoticeService ans;
	private final AdminNoticeRepository anr;
	private final AdminService as;
	
	//---------------관리자페이지 > 공지사항 > 신규 등록 페이지-----------------------------
	//@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
		@GetMapping("/createnotice")
		public String createNotice() {
			return "/Admin/AdminNotice_create";
		}
		
		//@PreAuthorize(value = "isAuthenticated()")  //로그인 한 경우에만 요청 처리
		@PostMapping("/createnotice")
		public String createNotice(AdminNoticeForm adminNoticeForm, BindingResult bindingResult,
				HttpServletRequest request) throws CanNotFoundException{
			 if (bindingResult.hasErrors()) {
			        return "/Admin/AdminNotice"; // 에러가 있는 경우 반환할 뷰
			    }
//			 Member m = this.ms.getUser(principal.getName()); 추후 작성한 관리자 번호 불러오기
//			 Notice a = this.ans.returnCreate(adminNoticeForm.getTitle(),adminNoticeForm.getContents());
			 
			 Admin admin = this.as.getAdmin(1);
			 
			 this.ans.returnCreate(admin, adminNoticeForm.getTitle(), adminNoticeForm.getContents());
		      
//		     ic.thumbfileInsert(request, a);  // 추후 이미지 업로드 메소드
//		     ic.bannerfileInsert(request, a); // 
		     return "redirect:/admin/Notice"; //URL
		}
		
		//---------------관리자페이지 > 공지사항 > 상세 페이지(조회,수정,삭제)-------------------
		@GetMapping("/admin/Notice/detail/{noticeId}") //조회
	    public String updateNotice(Model model, @PathVariable("noticeId") Integer noticeId) {
			//noticeId로 조회해서 가져오기
			Notice n = this.ans.getNotice(noticeId);
	        model.addAttribute("n", n);
	        return "/Admin/AdminNotice_detail";
	    }
		
		@PostMapping("/admin/Notice/detail") //수정
	    public String updateNotice(@Valid AdminNoticeForm adminNoticeForm, 
	    		@RequestParam(value="noticeId") Integer noticeId) throws CanNotFoundException {
			Admin admin = this.as.getAdmin(1);
		//	로그인 기능 구현 전이라 임의로 1을 넘김
			Notice n = this.ans.getNotice(noticeId);
			//수정한 데이터 저장하는 메소드 호출
			this.ans.update(n, adminNoticeForm.getTitle(), adminNoticeForm.getContents());
			
			ans.updateNotice(n);
	        return "redirect:/admin/Notice";
	    }

//		@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
		@GetMapping("/admin/Notice/detail/delete/{noticeId}") //개별 삭제
		public String NoticeDelete(@PathVariable("noticeId") Integer noticeId) throws CanNotFoundException {
			Notice n = ans.getNotice(noticeId);
			this.ans.delete(n);
			return "redirect:/admin/Notice";
		
	  }
		
		//---------------관리자페이지 > 공지사항 페이지(리스트)-----------------------------
//		@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
		@GetMapping("/admin/Notice") 
		public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page,
				@RequestParam(value = "kw", defaultValue = "") String kw,
				@RequestParam(value = "kwType", defaultValue = "") String kwType,
				HttpSession session) {
			
			//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
			EzenPaging ezenPaging = new EzenPaging(page, 10, ans.getNoticeCountByKeyword(kwType, kw), 5);
	        List<Notice> NList = this.ans.getNoticeByKeyword(kwType,kw,ezenPaging.getStartNo(), ezenPaging.getPageSize());
	        
	        model.addAttribute("NList", NList);
	        model.addAttribute("page",  ezenPaging);
	        model.addAttribute("kw", kw);
	        model.addAttribute("kwType", kwType);
	                          //" "안에 있는 값이 html에서 인식할 텍스트
	        return "/Admin/AdminNotice";  
		}
		//---------------관리자페이지 > 공지사항 페이지(다중 선택 삭제)-----------------------------
//		@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
		@PostMapping("/admin/Notice/delete")
		@ResponseBody
		public Map<String, Object> AdminNoticesDelete(@RequestBody Map<String, List<String>> payload) {
		    Map<String, Object> response = new HashMap<>();
		    List<String> ids = payload.get("ids"); 					//ids = id값들을 저장한 리스트
		    System.out.println("Received IDs: " + ids);
		try {
		        for (String noticeIdStr : ids) {
		            int noticeId = Integer.parseInt(noticeIdStr); 	//저장한 json타입의 id값을 Integer타입으로 변환해 noticeId에 할당
		            System.out.println("id확인: " + noticeId);
		            Notice n = this.ans.getNotice(noticeId); 		//noticeId로 리뷰 데이터를 받아옴
		            this.ans.delete(n);								//받아온 리뷰 데이터 삭제
		        }
		        response.put("success", true);						//성공적으로 삭제
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("success", false);						//삭제 실패 시 false 값을 넘김(실패했습니다 알림창)
		    }
		    return response;
	    }	
		
}
