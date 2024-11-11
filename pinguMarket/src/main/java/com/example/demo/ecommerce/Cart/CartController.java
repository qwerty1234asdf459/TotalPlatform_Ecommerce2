package com.example.demo.ecommerce.Cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.LoginCheck.LoginCheck;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final CartService cas;

	
//	---------제품 삭제-------------------
	@LoginCheck
	@GetMapping("/cart/delete")
	@ResponseBody
	public Map<String, Object> deleteSelectedItems(@RequestParam("id") List<Integer> ids) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        for (Integer cartId : ids) {
	            Cart c = this.cas.getCart(cartId); // cartId를 ids에 저장
	            this.cas.delete(c);
	        }
	        response.put("success", true); // 성공 시
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false); // 실패 시
	    }
	    return response;
	}
	
	
	
}
