package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//stereotype : 클래스 종류. Controller : 클라이언트 요청을 받아서 처리
@Controller	//fullstack 컨트롤러
public class HomeController {
	//url과 맞게 맵핑하기
	//handlerMapper
	@GetMapping("/")	//get 요청만 받음
	public String home() {	//요청을 처리하는 메소드는 뷰 페이지 경로를 반환
		return "index";		
	}
	//회원가입 폼
	@GetMapping("/member/join")	
	public String joinform() {
		return "joinForm";
	}
	
	//회원가입 완료
	@PostMapping("/member/join")	//post 요청만 받음
	public String join() {	
		return "join";		//회원가입완료 메세지만 출력
	}
	
	@GetMapping("/member/login")	//get 요청만 받음
	public String login() {	
		return "login";		
	}
	
	@PostMapping("/member/login")	//post 요청만 받음
	public String loginForm() {	
		return "loginres";		
	}
}
