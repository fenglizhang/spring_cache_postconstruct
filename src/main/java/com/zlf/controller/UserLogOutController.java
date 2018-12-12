package com.zlf.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class UserLogOutController {
	
	@RequestMapping("/now")
	public String logout(HttpServletRequest request){
		 SecurityUtils.getSubject().logout();  
		System.out.println("退出登陆！");
		return "../login";
	}
}
