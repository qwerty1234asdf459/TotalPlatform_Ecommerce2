package com.example.demo.ecommerce.Admin.Product;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ecommerce.Admin.AdminService;
import com.example.demo.ecommerce.Entity.AdminProduct;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Product.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class AdminProductController {
	private final AdminProductService aps;
	private final ProductRepository pr;
	//private final AdminService as;
	//private final ImageController ic;
	
	//------------------관리자페이지 > 상품관리(리스트)-----------------------------
	//@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/admin/Product") 
	public String AdminProduct(Model model) {
        List<Product> PList = this.pr.findAll();
        model.addAttribute("PList", PList);
                          //" "안에 있는 값이 html에서 인식할 텍스트
        return "/Admin/AdminProduct";  
	}
	
	
	
	//---------------관리자페이지 > 상품관리 > 신규 등록 페이지-----------------
	//@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/registration")
	public String createProduct() {
		return "/Admin/AdminRegistration";
	}
	
	//@PreAuthorize(value = "isAuthenticated()")  //로그인 한 경우에만 요청 처리
	@PostMapping("/registration")
	public String createProduct(AdminProductForm adminProductForm, BindingResult bindingResult,
			HttpServletRequest request) throws Exception{
		 if (bindingResult.hasErrors()) {
//		        for (FieldError error : bindingResult.getFieldErrors()) {
//		            System.out.println("Field: " + error.getField());
//		            System.out.println("Error Code: " + error.getCode());
//		            System.out.println("Default Message: " + error.getDefaultMessage());
//		        }
		        return "/Admin/AdminRegistration"; // 에러가 있는 경우 반환할 뷰
		    }
	//	 Member m = this.ms.getUser(principal.getName());
		 Product a = this.aps.returnCreate(adminProductForm.getName(),adminProductForm.getCategory(),adminProductForm.getPrice()
	    		   ,adminProductForm.getAmount());
	      
	       
	    // ic.thumbfileInsert(request, a);  //썸네일 이미지 저장 메소드
	    // ic.bannerfileInsert(request, a); //배너 이미지 저장 메소드
	     return "redirect:/registration"; //URL
	}
	
	
	
//	@PostMapping("/registration")
//	public String createProduct(AdminProductForm adminProductForm, BindingResult bindingResult,
//			HttpServletRequest request, @RequestParam(value = "mainImg") MultipartFile mainImg,
//			@RequestParam(value = "detailImg") MultipartFile detailImg, Principal principal) throws Exception{
//		 if (bindingResult.hasErrors()) {
////		        for (FieldError error : bindingResult.getFieldErrors()) {
////		            System.out.println("Field: " + error.getField());
////		            System.out.println("Error Code: " + error.getCode());
////		            System.out.println("Default Message: " + error.getDefaultMessage());
////		        }
//		        return "/Admin/AdminProduct"; // 에러가 있는 경우 반환할 뷰
//		    }
//	//	 Member m = this.ms.getUser(principal.getName());
//		 AdminProduct a = this.aps.returnCreate(adminProductForm.getName(),adminProductForm.getCategory(),adminProductForm.getPrice()
//	    		   ,adminProductForm.getAmount());
//	      
//	       
//	     ic.thumbfileInsert(request, mainImg, a);  //썸네일 이미지 저장 메소드
//	     ic.bannerfileInsert(request, detailImg, a); //배너 이미지 저장 메소드
//	     return "redirect:/Admin/AdminProduct"; //
//	}
	
	
}
