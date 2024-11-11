package com.example.demo.ecommerce.Admin.Inquiry;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeForm;
import com.example.demo.ecommerce.CsAnswer.CsAnswerForm;
import com.example.demo.ecommerce.CsAnswer.CsAnswerRepository;
import com.example.demo.ecommerce.CsAnswer.CsAnswerService;
import com.example.demo.ecommerce.CsQuestion.CsQuestionForm;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionService;
import com.example.demo.ecommerce.CsQuestion.UserException;
import com.example.demo.ecommerce.Entity.Admin;
import com.example.demo.ecommerce.Entity.CsAnswer;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Paging.EzenPaging;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
public class AdminInquiryController {
	
	private final CsQuestionService cqs;
	private final CsAnswerService cas;
	private final AdminService as;
	private final CsQuestionRepository cqr;
	private final CsAnswerRepository car;
	
	//---------------관리자페이지 > 문의 관리(리스트)----------------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/admin/cs") 
	public String Inquiry(Model model, @RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value = "kw", defaultValue = "") String kw,
			@RequestParam(value = "kwType", defaultValue = "") String kwType,
			HttpSession session) {
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
		EzenPaging ezenPaging = new EzenPaging(page, 10, as.getCsQuestionCountByKeyword(kwType, kw), 5);
        List<CsQuestion> Q = this.as.getCsQuetionByKeyword(kwType,kw,ezenPaging.getStartNo(), ezenPaging.getPageSize());
        
        model.addAttribute("Q", Q);
        model.addAttribute("page",  ezenPaging);
        model.addAttribute("kw", kw);
        model.addAttribute("kwType", kwType);
                          //" "안에 있는 값이 html에서 인식할 텍스트
        return "/Admin/AdminInquiry";  
	}
	
	//---------------관리자페이지 > 문의 관리(다중 선택 삭제)----------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/admin/cs/delete")
	@ResponseBody
	public Map<String, Object> AdminInquiryDelete(@RequestBody Map<String, List<String>> payload) {
	    Map<String, Object> response = new HashMap<>();
	    List<String> ids = payload.get("ids"); 							//ids = id값들을 저장한 리스트
	    System.out.println("Received IDs: " + ids);
	try {
	        for (String csQuestionIdStr : ids) {
	            int csQuestionId = Integer.parseInt(csQuestionIdStr); 	//저장한 json타입의 id값을 Integer타입으로 변환해 csQuestionId에 할당
	            System.out.println("id확인: " + csQuestionId);
	            CsQuestion cq = this.cqs.getQuestion(csQuestionId); 	//csQuestionId로 리뷰 데이터를 받아옴
	            this.cqs.delete(cq);									//받아온 문의글(질문) 데이터 삭제 -> 답변도 같이 삭제되는지?
	        }
	        response.put("success", true);								//성공적으로 삭제
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);								//삭제 실패 시 false 값을 넘김(실패했습니다 알림창)
	    }
	    return response;
    }	
	
	//---------------관리자페이지 > 문의 관리 > 상세페이지 > 답변 개별 삭제-----------------
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/cs/answerDelete/{csAnswerId}")
	
	@RequestMapping(value = "/admin/cs/answerDelete", method = RequestMethod.POST)
    public String answerDelete(@RequestParam(value="csAnswerId") Integer csAnswerId) {
		CsAnswer answer = this.cas.getCsAnswer(csAnswerId);
		
        this.cas.delete(answer);
        return String.format("redirect:/admin/cs/%s", answer.getCsQuestion().getCsQuestionId());
    }
	
//	@RequestMapping(value = "/admin/cs/answerDelete", method = RequestMethod.GET)
//    public String csAnswerDelete(@RequestParam(value="csAnswerId") Integer csAnswerId) {
//		CsAnswer answer = this.cas.getCsAnswer(csAnswerId);
//		
//        this.cas.delete(answer);
//        return String.format("redirect:/admin/cs/%s", answer.getCsQuestion().getCsQuestionId());
//    }
	
	
//	@GetMapping("/delete/{id}") 개별 삭제 참고 코드
//    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
//        Answer answer = this.answerService.getAnswer(id);
//        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
//        this.answerService.delete(answer);
//        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
//    }
	
	
	//---------------관리자페이지 > 문의 관리 > 상세페이지-------------------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/admin/cs/{csQuestionId}") 
    public String AdminInquiry(Model model, @PathVariable("csQuestionId") Integer csQuestionId) 
    		throws UserException{
		//csQuestionId로 조회해서 가져오기
		CsQuestion Q = this.cqs.getQuestion(csQuestionId);
        model.addAttribute("Q", Q);
        return "/Admin/AdminInquiry_detail";
    }

	//---------------관리자페이지 > 문의 관리 > 상세페이지 > 답변 신규등록-------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/admin/cs/newAnswer/{csQuestionId}")
    public String createAnswer(Model model, @RequestParam(value="csQuestionId") Integer csQuestionId,
    							@RequestParam(value="title") String title, //html : name=title
								@RequestParam(value="contents") String contents) throws UserException {
	//	Admin admin = this.as.getAdmin(adCode);
		CsQuestion q = this.cqs.getQuestion(csQuestionId);
		this.cas.returnCreate("aaa", q, title, contents); //관리자 로그인 구현x으로 임의로 집어넣음
        return String.format("redirect:/admin/cs/%s", q.getCsQuestionId());
    }

	
	//참고코드
//	@PostMapping("/cs/newAnswer/{csQuestionId}") 
//	public String createAnswer(Model model, @PathVariable("csQuestionId") Integer csQuestionId,
//			@RequestParam(value="content") String content,
//			@Valid CsAnswerForm answerForm, BindingResult bindingResult,
//			Principal principal) {
//		try {
//			CsQuestion q1 = this.cqs.getQuestion(csQuestionId);
//			Admin admin  = this.cas.getAdmin.get(principal.getadCode());
//			if(bindingResult.hasErrors()) {
//				model.addAttribute("question", q1);
//				return "/Admin/AdminInquiry";
//			}
//			this.cas.returnCreate(adCode, q, answerForm.getTitle(). answerForm.getContents());
//		} catch (CanNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		return String.format("redirect:/admin/cs/{csQuestionId}", csQuestionId);
//	}
	
	
	//---------------관리자페이지 > 문의 관리 > 상세페이지 > 답변 수정하기-------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/admin/cs/updateAnswer/{csAnswerId}")
//	updateAnswer
    public String updateCsAnswer(@Valid CsAnswerForm csAnswerForm, 
    		@RequestParam(value="csAnswerId") Integer csAnswerId) throws CanNotFoundException {
		Admin admin = this.as.getAdmin(1);
//		로그인 기능 구현 전이라 임의로 1을 넘김
		CsAnswer ca = this.cas.getCsAnswer(csAnswerId);
//		수정한 데이터 저장하는 메소드 호출
		this.cas.update(ca, csAnswerForm.getTitle(), csAnswerForm.getContents());
		
		cas.updateCsAnswer(ca);
		return String.format("redirect:/admin/cs/%s", ca.getCsQuestion().getCsQuestionId());
    }
	

	
}
