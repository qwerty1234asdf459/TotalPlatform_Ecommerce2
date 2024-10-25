package com.example.demo.ecommerce.Admin.UserManagement;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserManagement {
	//id 이름 전화번호 주소 주문건수 반품건수 등록일 //새로운 엔티티 생성이 아닌 기존 엔티티에서 가져오는 정보들
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String tel;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String address_detail;
	
	
	//private String a; //주문건수 payment_id 카운트
	
	
	//private String b; //반품건수 return_state 카운트
	
	@NotEmpty
	private LocalDateTime create_date; //회원등록일
	
	//회원 탈퇴한 사람도 보여줘야 하나?
	
	

	
}
