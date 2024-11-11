package com.example.demo.ecommerce.LoginCheck;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



/**
 * HandlerMethod  : 실행될 핸들러(컨트롤러의 메소드) loginCheck가 null이라면 로그인 없이 접근가능한 핸들러이므로 true 리턴
 */

@Component
@RequiredArgsConstructor

public class LoginCheckInterceptor implements HandlerInterceptor {
	
	private final LoginCheckService loginCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler)
        throws Exception {

    	if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

    	boolean hasAnnotation = checkAnnotation(handler, LoginCheck.class);
  
        
        if (hasAnnotation) {
            
            if (loginCheckService.checkToken(request) != null){
            	return true;
            }
            
            if (loginCheckService.checkToken(request) == null) {
            	response.sendRedirect("/user/login");
            }
        }
        

        if(!hasAnnotation) {
        	 return true;
        }
        return true;
        

    }
    
    
    private boolean checkAnnotation(Object handler, Class<LoginCheck> loginCheck) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;


        if (null != handlerMethod.getMethodAnnotation(loginCheck) || null != handlerMethod.getBeanType().getAnnotation(loginCheck)) {
            return true;
        }

        //annotation이 없는 경우
        return false;
    }

}
    