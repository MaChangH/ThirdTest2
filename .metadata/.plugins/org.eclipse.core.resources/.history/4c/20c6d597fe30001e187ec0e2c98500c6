package com.teamproject.teamproject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// 처음 접속 시 요청 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		
		req.setAttribute("cp", "home.jsp");
		return "index";
	}
	
	// HOME버튼 클릭시 요청 페이지
	@RequestMapping(value = "/home.go", method = RequestMethod.GET)
	public String goHome(HttpServletRequest req) {
		
		req.setAttribute("cp", "home.jsp");
		return "index";
	}
}
