package com.example.demo.ecommerce.CsQuestion;

import java.security.Principal;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeForm;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeService;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Paging.EzenPaging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CsController {
	
private final CsQuestionService qr;
private final AdminService ar;
private final AdminNoticeService ans;
	
//  ------------------------------------------------------------------고객센터 페이지 연결---------------------------------------------------------------------------------
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc")
	public String csCenter(Model model, 
	                       @RequestParam(value="page1", defaultValue="0") int page1,
	                       @RequestParam(value="page2", defaultValue="0") int page2, 
	                       @RequestParam(value="pageType", defaultValue="inquiry") String pageType,
	                       Principal principal, 
	                       @RequestParam(value="chk", defaultValue="on") String chk) throws UserException {
	    
	    // 1:1 문의 페이징
	    EzenPaging ezenPaging = new EzenPaging(page1, 10, ar.getQuestionCountByAll(1), 5); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
	    List<CsQuestion> questionList = ar.getUserByKeyword(1, ezenPaging.getStartNo(), ezenPaging.getPageSize()); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
	
	    // 공지사항 페이징
	    EzenPaging ezenPaging2 = new EzenPaging(page2, 10, ans.getNoticeCountAll(), 5);
	    List<Notice> noticeList = ans.getNoticePaging(ezenPaging2.getStartNo(), ezenPaging2.getPageSize());
	
	    model.addAttribute("questionList", questionList);
	    model.addAttribute("page", ezenPaging); 
	    model.addAttribute("page2", ezenPaging2);
	    model.addAttribute("noticeList", noticeList);
	
	    // 현재 활성화된 페이지 타입 정보 추가
	    model.addAttribute("activePageType", pageType);
	
	    return "Cs/csPage";
	}

	
//  ----------------------------------------------------고객센터 > 1:1 문의 > 상세페이지 연결-------------------------------------------------------
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/csc/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CsQuestionForm csquestionForm) throws UserException {
		CsQuestion q = this.qr.getQuestion(id);
		model.addAttribute("question",  q);
		
		return "Cs/cscDetail";
	}
	
	
//  -----------------------------------------------고객센터 > 1:1 문의 작성 form-----------------------------------------------
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc/form")
	public String csquestionForm(CsQuestionForm csquestionForm, Model model) {
		
		String userId = "test"; //principal.getName();으로 바꿔주기
		List<Payment> payList = this.ar.getPaymentList(userId);
		model.addAttribute("payList", payList);
		
		return "Cs/cscForm";
	}
	
	//   -----------------------------------------------고객센터 > 1:1 문의한 내용을 받아와 처리 -----------------------------------------------
	//	@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/form")
		public String questionCreate(@Valid CsQuestionForm csquestionForm, 
					BindingResult bindingResult, Principal principal) throws UserException {
						
			if(bindingResult.hasErrors()) {				
				return "Cs/cscForm";
			}
			this.qr.create(csquestionForm.getOrderNo(), csquestionForm.getTitle(), csquestionForm.getContents(), 1); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기			
			return "redirect:/csc";
		}
		
	//   -----------------------------------------------고객센터 > 1:1문의 수정-----------------------------------------------
	//	@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/modify/{id}")
		public String questionModify(Model model, CsQuestionForm csquestionForm, @PathVariable("id") Integer id) throws UserException {
			
			CsQuestion q = this.qr.getQuestion(id);
			model.addAttribute("question", q);
			
			String userId = "test"; //test말고 principal.getName();으로 수정 필요
			List<Payment> payList = this.ar.getPaymentList(userId);
			model.addAttribute("payList", payList);
			
			return "Cs/cscRetouchForm"; 
			
		}
	
	//  -----------------------------------------------고객센터 > 1:1 문의 수정한 내용 받아와 처리-----------------------------------------------
	//	@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/modify/{id}")
		public String questionModify(@Valid CsQuestionForm csquestionForm, 
				@PathVariable("id") Integer id, BindingResult bindingResult) throws UserException {
			
			if(bindingResult.hasErrors()) {
				return "Cs/cscForm";
			}
			
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.modify(q, csquestionForm.getOrderNo(), csquestionForm.getTitle(), csquestionForm.getContents());
			
			return "redirect:/csc/detail/{id}";
		}

	//   -----------------------------------------------고객센터 > 1:1 문의 삭제-----------------------------------------------
	//	@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/delete/{id}")
		public String questionDelete(CsQuestionForm csQuestionForm, @PathVariable("id") Integer id) throws UserException {
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.delete(q);
			
			return "redirect:/csc";
		}
		
		
		// -----------------------------------------------고객센터 > 공지사항 > 상세페이지 조회-----------------------------------------------
		@GetMapping(value ="/csc/notice/{id}")
		public String noticeDetail(Model model, @PathVariable("id") Integer noticeId, AdminNoticeForm adminNoticeForm) throws UserException {
			
			Notice notice = this.ans.getNotice(noticeId);
			model.addAttribute("notice", notice);
			
			return "Cs/csNotiDetail"; 
			
		}

}
