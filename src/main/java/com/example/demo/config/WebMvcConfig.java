package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// beforeActionInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	// needLoginInterceptor 인터셉터 불러오기
		@Autowired
		@Qualifier("needAdminInterceptor")
		HandlerInterceptor needAdminInterceptor;
	
	// needLoginInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needLoginInterceptor")
	HandlerInterceptor needLoginInterceptor;

	// needToLogoutInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToLogoutInterceptor")
	HandlerInterceptor needToLogoutInterceptor;

	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		// 로그인 없이도 접속할 수 있는 URI 기술
			registry.addInterceptor(needAdminInterceptor)
				.addPathPatterns("/adm/**")
				.excludePathPatterns("/adm/member/login")
				.excludePathPatterns("/adm/member/doLogin")
				;
		
		// 로그인 없이도 접속할 수 있는 URI 기술
		registry.addInterceptor(needLoginInterceptor)
			.addPathPatterns("/usr/**")
			.excludePathPatterns("/")
			.excludePathPatterns("/usr/main/*")
			.excludePathPatterns("/usr/article/list")
			.excludePathPatterns("/usr/article/detail")
			.excludePathPatterns("/usr/member/signup")
			.excludePathPatterns("/usr/member/login")
			.excludePathPatterns("/usr/reply/list")
			.excludePathPatterns("/usr/reply/delete")
			.excludePathPatterns("/error")
			;

		// 로그인 상태에서 접속할 수 없는 URI 전부 기술
		registry.addInterceptor(needToLogoutInterceptor)
			.addPathPatterns("/adm/member/login")
			.addPathPatterns("/usr/member/login")
			.addPathPatterns("/usr/member/signup")
			.addPathPatterns("/usr/member/join")
			.addPathPatterns("/usr/member/doJoin")
			;
	}
}