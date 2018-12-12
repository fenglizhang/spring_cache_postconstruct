package com.zlf.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlf.bo.UserBo;
import com.zlf.service.IUserLoginService;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	@Resource
	private IUserLoginService us;
	
	/**
	 * 登陆验证用户名密码
	 */
	@RequestMapping("/checkLogin")
	public String checkLogin(HttpServletRequest request){
		String username=request.getParameter("username");
		String pwd=request.getParameter("password");
		UserBo ub=new UserBo();
		ub.setUsername(username);
		ub.setPwd(pwd);
		UserBo userBo = us.queryByPwdAndUsername(ub);
		if(userBo !=null && userBo.getUserid()!=null){
			return  "test";
		}
		return "../login";
	}
	
	
	
	
	
}
