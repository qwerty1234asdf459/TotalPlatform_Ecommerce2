package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	//실험
	   @Bean
	   SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	      // 인증되지 않은 모든 페이지의 요청을 허락
	      http
	      .authorizeHttpRequests(
	            (authorizeHttpRequests) -> authorizeHttpRequests
	           .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
	      	   //.requestMatchers("/main").authenticated()
	      	   )
	        .csrf((csrf) -> csrf
	                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
	        .headers((headers) -> headers
	                .addHeaderWriter(new XFrameOptionsHeaderWriter(
	                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
	        .formLogin((formLogin) -> formLogin
	              .loginPage("/user/login")
	              .defaultSuccessUrl("/main")) //추후 보던 페이지로 돌아가게끔 수정
	        .logout((logout) -> logout
	              .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	              .logoutSuccessUrl("/main")
	              .invalidateHttpSession(true)) //로그아웃 시 생성된 사용자 세션도 삭제
	      ;
	      return http.build();
	   }
	// 
	
	@Bean
	PasswordEncoder passwordEncoder() { //비밀번호 암,복호화
		return new BCryptPasswordEncoder();
	}
	@Bean
	AuthenticationManager authenticationManager( //인증과 권한 부여 프로세스를 처리
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean//영상 인코딩을 위한 빈 객체
	public WebMvcConfigurer webMvcConfigurer() { //해당 인터페이스를 상속받는 클래스를 생성
	    return new WebMvcConfigurer() {
	        @Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) { //정적 파일들의 경로를 잡아주는(관리) 메소드
	            registry.addResourceHandler("/videos/**") //어느 경로로 들어왔을때 매핑이 되어줄 것인지를 정의
	                    .addResourceLocations("classpath:/static/videos/"); //실제 파일이 있는 경로 지정
	        }
	    };
	}
}
