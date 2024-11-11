package com.example.demo.ecommerce.Authuser;

import java.util.List;
import java.util.Optional;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demo.LoginCheck.LoginCheckService;
import com.example.demo.LoginCheck.undefinedUserException;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.User.UserRepository;
import com.example.demo.totalplatform.TotalUser;
import com.example.demo.totalplatform.TotalUserRepository;
import com.example.demo.totalplatform.TotalUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthuserResolver implements HandlerMethodArgumentResolver {

		private final UserRepository userRepository;
		private final LoginCheckService loginCheckService;
		private final AuthuserService authuserService;
		private final TotalUserRepository totalUserRepository;
		private final TotalUserService totalUserService;
		
	    @Override
	    public boolean supportsParameter(MethodParameter parameter) {
	        boolean hasAnnotation = parameter.hasParameterAnnotation(Authuser.class);
	        boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());
	        
	        
	        return hasAnnotation && isUserType;
	    }
	    
	    
	
	    
	    
	    @Override
	    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
	        
	    	
	    	 HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
	         
	    	String jwtToken = loginCheckService.checkToken(servletRequest);
	    	User noUser = new User();
	    	if(jwtToken == null) {
	    		noUser.setId("null");
	    		return noUser;
	    	}
	    	
	    	
	    	ResponseEntity<String> user = authuserService.getNameWithHeader(jwtToken).block();
	    	List<String> listHeader = user.getHeaders().get("Principal");
	    
	    	
	    	String userId =  listHeader.get(0);
	    	
	  
	    	
	    	
	    	if(userRepository.findById(userId).isEmpty()) {
	    		TotalUser TotalUser =  totalUserRepository.findById(userId);
	    		User userbyTotalUser = totalUserService.returncreate(TotalUser);
	    		return userbyTotalUser;
	    	}
	    	
	    	
	        return userRepository.findById(userId).orElseThrow(
	                () -> new undefinedUserException("User is undefined")
	        );
	    }
	    
	    
	    
	}

