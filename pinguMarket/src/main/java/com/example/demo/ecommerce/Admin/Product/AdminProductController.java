package com.example.demo.ecommerce.Admin.Product;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ecommerce.Entity.Image;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.ProductImg;
import com.example.demo.ecommerce.Image.ImageService;
import com.example.demo.ecommerce.Product.ProductRepository;
import com.example.demo.ecommerce.Product.ProductService;
import com.example.demo.ecommerce.productimg.ProductImgService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class AdminProductController {
	private final AdminProductService aps;
	private final ProductRepository pr;
	private final ProductService ps;
	private final ImageService imageService;
	private final ProductImgService productImgService;
	
	//------------------관리자페이지 > 상품 관리(리스트)--------------------------
	//@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@GetMapping("/admin/Product") 
	public String AdminProduct(Model model) {
        List<Product> PList = this.pr.findAll();
        model.addAttribute("PList", PList);
                          //" "안에 있는 값이 html에서 인식할 텍스트
        return "/Admin/AdminProduct";  
	}
	
	//---------------관리자페이지 > 상품 관리(다중 선택 삭제)-----------------------
//	@PreAuthorize("isAuthenticated()") // 로그인 한 경우에만 요청 처리
	@PostMapping("/admin/Product/delete")
	@ResponseBody
	public Map<String, Object> AdminProductDelete(@RequestBody Map<String, List<String>> payload) {
	    Map<String, Object> response = new HashMap<>();
	    List<String> ids = payload.get("ids"); //ids = id값들을 저장한 리스트
	    System.out.println("Received IDs: " + ids);
	try {
	        for (String productIdStr : ids) {
	            int productId = Integer.parseInt(productIdStr); 	//저장한 json타입의 id값을 Integer타입으로 변환해 productId에 할당
	            System.out.println("id확인: " + productId);
	            Product P = this.ps.getProduct(productId); 		//productId로 리뷰 데이터를 받아옴
	            this.aps.delete(P);								//받아온 리뷰 데이터 삭제
	        }
	        response.put("success", true);						//성공적으로 삭제
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);						//삭제 실패 시 false 값을 넘김(실패했습니다 알림창)
	    }
	    return response;
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
			HttpServletRequest request, @RequestParam("productImg") MultipartFile files) throws Exception{
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
	      
		 Image i = this.imageService.save(request, files);
		 this.productImgService.create(a, i);
	       
	    // ic.thumbfileInsert(request, a);  //썸네일 이미지 저장 메소드
	    // ic.bannerfileInsert(request, a); //배너 이미지 저장 메소드
	     return "redirect:/admin/Product"; //URL
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
