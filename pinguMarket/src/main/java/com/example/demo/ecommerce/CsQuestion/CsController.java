package com.example.demo.ecommerce.CsQuestion;

import java.security.Principal;


import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Entity.CsQuestion;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CsController {
	
private final CsQuestionService qr;
private final AdminService ar;
	
//  고객센터 페이지 연결
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc")
	public String csCenter(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
		
		Page<CsQuestion> paging = this.qr.getList(page, principal.getName());
		model.addAttribute("paging", paging);
		
		return "csc/csPage";
	}
	
//  1:1 문의에서 질문 클릭 시 해당 내용 보여주는 상세페이지 연결
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/csc/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CsQuestionForm csquestionForm) throws UserException {
		CsQuestion q = this.qr.getQuestion(id);
		model.addAttribute("question",  q);
		
		return "csc/cscDetail";
	}
	
//  1:1 문의 작성 폼 페이지 연결
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/csc/form")
	public String csquestionForm(CsQuestionForm csquestionForm) {

		return "csc/cscForm";
	}
	
	// 서비스센터 문의한 내용을 받아와 처리하는 곳
		@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/form")
		public String questionCreate(@Valid CsQuestionForm csquestionForm, 
					BindingResult bindingResult, Principal principal) throws UserException {
			
			if(bindingResult.hasErrors()) {
				return "csc/cscForm";// cscDetail?
			}
			
			this.qr.create(csquestionForm.getTitle(), csquestionForm.getContents(), principal.getName());
			
			return "redirect:/csc";
		}
		
	//  관리자 답변 미등록 상태에서 1:1문의 내용 수정 할 수 있는 페이지 연결
		@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/modify/{id}")
		public String questionModify(CsQuestionForm csquestionForm, @PathVariable("id") Integer id) throws UserException {
			
			CsQuestion q = this.qr.getQuestion(id);
			csquestionForm.setTitle(q.getTitle());
			csquestionForm.setContents(q.getContents());
			
			return "csc/cscRetouchForm"; 
			
		}
	
	//  서비스센터 문의 수정이 처리되는 곳
		@PreAuthorize("isAuthenticated()")
		@PostMapping("/csc/modify/{id}")
		public String questionModify(@Valid CsQuestionForm csquestionForm, 
				@PathVariable("id") Integer id, BindingResult bindingResult) throws UserException {
			
			if(bindingResult.hasErrors()) {
				return "csc/cscForm";
			}
			
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.modify(q, csquestionForm.getTitle(), csquestionForm.getContents());
			
			return "redirect:/csc/detail/{id}";
		}

	//  서비스센터 문의 삭제페이지 연결
		@PreAuthorize("isAuthenticated()")
		@GetMapping("/csc/delete/{id}")
		public String questionDelete(CsQuestionForm csquestionForm, @PathVariable("id") Integer id) throws UserException {
			
			CsQuestion q = this.qr.getQuestion(id);
			this.qr.delete(q);
			
			return "redirect:/csc";
		}
		

		// 서비스센터 문의 답변페이지 연결
//		@PreAuthorize("isAuthenticated()")
//		@GetMapping("/csc/answer/{id}")
//		public String answerDetail(Model model, @PathVariable("id") Integer id) {
//			
//			model.addAttribute("answer", this.ar.getAnswer(id));
//			
//			return "";
//		}
	
	
	

}
