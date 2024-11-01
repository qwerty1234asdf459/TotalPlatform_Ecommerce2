package com.example.demo.ecommerce.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyForm {
	
	@NotEmpty(message = "이메일은 필수항목입니다.")
	private String email1;
	
	@NotEmpty(message = "도메인은 필수항목입니다.")
	private String email2;
	
	@NotEmpty(message = "전화번호는 필수항목입니다")
	private String tell;
	
	@NotEmpty(message = "주소는 필수항목입니다")
	private String address1;
	
	@NotEmpty(message = "주소는 필수항목입니다")
	private String address2;
	
	@NotEmpty(message = "세부 주소는 필수항목입니다")
	private String addressDetail;
	
	@NotEmpty(message = "성별은 필수항목입니다")
	private String gender;
	
}
