package com.example.demo.ajaxtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cookie")
public class CookieTestController {
	
	@GetMapping("/add")
	public String add(int num, String name, HttpServletResponse res) {
		Cookie c1 = new Cookie("num", num+"");
		Cookie c2 = new Cookie("name", name);
		
		res.addCookie(c1);
		res.addCookie(c2);
		
		return "cookie/index";
	}
	
	@GetMapping("/list")
	public String list(HttpServletRequest req) {
		Cookie[] list = req.getCookies();
		for(Cookie c:list) {
			System.out.println("name:"+c.getName()+" / value:"+c.getValue());
		}
		return "cookie/index";
	}
}








