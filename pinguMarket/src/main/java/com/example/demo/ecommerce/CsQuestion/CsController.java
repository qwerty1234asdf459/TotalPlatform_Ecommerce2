package com.example.demo.ecommerce.CsQuestion;

import java.security.Principal;
import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeService;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Paging.EzenPaging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CsController {
	
private final CsQuestionService qr;
private final AdminService ar;
private final AdminNoticeService an;
	
//  고객센터 페이지 연결
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc")
	public String csCenter(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
				EzenPaging ezenPaging = new EzenPaging(page, 10, ar.getQuestionCountByAll(1), 5); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
				List<CsQuestion> questionList = this.ar.getUserByKeyword(1, ezenPaging.getStartNo(), ezenPaging.getPageSize()); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
				
				
				
				model.addAttribute("questionList", questionList);

				model.addAttribute("page", ezenPaging);
				
				return "Cs/csPage";
	}

	
//  1:1 문의에서 질문 클릭 시 해당 내용 보여주는 상세페이지 연결
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/csc/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CsQuestionForm csquestionForm) throws UserException {
		CsQuestion q = this.qr.getQuestion(id);
		model.addAttribute("question",  q);
		
		return "Cs/cscDetail";
	}
	
	
//  1:1 문의 작성 폼 페이지 연결
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc/form")
	public String csquestionForm(CsQuestionForm csquestionForm) {
		
		return "Cs/cscForm";
	}
	
	//  1:1 문의 문의한 내용을 받아와 처리하는 곳
	//	@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/form")
		public String questionCreate(@Valid CsQuestionForm csquestionForm, 
					BindingResult bindingResult, Principal principal) throws UserException {
						
			if(bindingResult.hasErrors()) {				
				return "Cs/cscForm";
			}
			this.qr.create(csquestionForm.getTitle(), csquestionForm.getContents(), 1); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기			
			return "redirect:/csc";
		}
		
	//  관리자 답변 미등록 상태에서 1:1문의 내용 수정 할 수 있는 페이지 연결
	//	@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/modify/{id}")
		public String questionModify(CsQuestionForm csquestionForm, @PathVariable("id") Integer id) throws UserException {
			
			CsQuestion q = this.qr.getQuestion(id);
			csquestionForm.setTitle(q.getTitle());
			csquestionForm.setContents(q.getContents());
			
			return "Cs/cscRetouchForm"; 
			
		}
	
	//  1:1 문의 수정이 처리되는 곳
	//	@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/modify/{id}")
		public String questionModify(@Valid CsQuestionForm csquestionForm, 
				@PathVariable("id") Integer id, BindingResult bindingResult) throws UserException {
			
			if(bindingResult.hasErrors()) {
				return "Cs/cscForm";
			}
			
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.modify(q, csquestionForm.getTitle(), csquestionForm.getContents());
			
			return "redirect:/csc/detail/{id}";
		}

	//   1:1 문의 삭제페이지 연결
	//	@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/delete/{id}")
		public String questionDelete(CsQuestionForm csquestionForm, @PathVariable("id") Integer id) throws UserException {
			
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.delete(q);
			
			return "redirect:/csc";
		}
		

		//  1:1 문의 답변페이지 연결
//		@PreAuthorize("isAuthenticated()")
//		@GetMapping("/csc/answer/{id}")
//		public String answerDetail(Model model, @PathVariable("id") Integer id) {
//			
//			model.addAttribute("answer", this.ar.getAnswer(id));
//			
//			return "";
//		}
		

	
	

}
