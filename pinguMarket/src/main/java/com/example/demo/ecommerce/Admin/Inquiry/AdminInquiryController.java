package com.example.demo.ecommerce.Admin.Inquiry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.ecommerce.CsQuestion.CsQuestionForm;
import com.example.demo.ecommerce.CsQuestion.CsQuestionRepository;
import com.example.demo.ecommerce.CsQuestion.CsQuestionService;
import com.example.demo.ecommerce.CsQuestion.UserException;
import com.example.demo.ecommerce.Entity.CsQuestion;


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
	
	//---------------관리자페이지 > 문의 관리(다중 선택 삭제)-----------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/cs/delete")
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
	            this.cqs.delete(cq);									//받아온 문의글(질문) 데이터 삭제 -> 답변도 같이 삭제 되도록
	        }
	        response.put("success", true);								//성공적으로 삭제
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);								//삭제 실패 시 false 값을 넘김(실패했습니다 알림창)
	    }
	    return response;
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
	//관리자 답변 등록 및 조회 기능

	
}
