package com.example.demo.ecommerce.Admin.Inquiry;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeRepository;
import com.example.demo.ecommerce.Admin.Notice.AdminNoticeService;
import com.example.demo.ecommerce.CsQuestion.CsQuestionForm;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionService;
import com.example.demo.ecommerce.CsQuestion.UserException;
import com.example.demo.ecommerce.Entity.CsQuestion;
import com.example.demo.ecommerce.Entity.Notice;
import com.example.demo.ecommerce.Payment.PaymentRepository;
import com.example.demo.ecommerce.User.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/admin")
public class AdminInquiryController {
	
	private final CsQuestionService cqs;
	//private final UserService us;
	private final CsQuestionRepository cqr;
	
	//---------------관리자페이지 > 문의 관리(리스트)-----------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/cs") 
	public String Inquiry(Model model) {
        List<CsQuestion> Q = this.cqr.findAll();
        model.addAttribute("Q", Q);
                          //" "안에 있는 값이 html에서 인식할 텍스트
        return "/Admin/AdminInquiry";  
	}
	
	//---------------관리자페이지 > 문의 관리 > 상세페이지-------------------
	//김민지님이 만드신 cs페이지와 겹쳐서 작업진행도 여쭤보기
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/cs/{csQuestionId}") 
    public String AdminInquiry(Model model, @PathVariable("csQuestionId") Integer csQuestionId, CsQuestionForm csQuestionForm) 
    		throws UserException{
		//csQuestionId로 조회해서 가져오기
		CsQuestion Q = this.cqs.getQuestion(csQuestionId);
        model.addAttribute("Q", Q);
        return "/Admin/AdminInquiry_detail";
    }
	
}
