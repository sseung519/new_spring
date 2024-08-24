package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")		//이 컨트롤러의 공통 url. 등록하는 모든 url 앞에 자동으로 /mem이 붙음
public class MemController {
	@Autowired
	private MemService service;
	
	@GetMapping("/join")
	public String join() {
		service.addMember();	//db 추가작업
		return "member/join";	// views/member/join으로
	}
	
	@GetMapping("/info")
	public String info() {
		service.getMember();
		return "member/info";
	}
	
	@GetMapping("/getAll")
	public String getAll() {
		service.getAll();
		return "member/getAll";
	}
	
	@GetMapping("/edit")
	public String edit() {
		service.editMember();
		return "member/edit";
	}
	
	@GetMapping("/delete")
	public String delete() {
		service.editMember();
		return "member/delete";
	}
	
	
	//검색 			url:get. 		view:info.jsp
	//전체검색		url:getall. 		view:getall.jsp
	//수정			url:edit. 		view:edit.jsp
	//삭제			url:delete. 		view:delete.jsp
	
}
