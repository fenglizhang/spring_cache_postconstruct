package com.zlf.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlf.service.IUserService;

@Controller
@RequestMapping("/login")
public class UserController {
	
	@Resource
	private IUserService t_userService;

	@RequestMapping("/loginAdmin")
	public String login(ServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = WebUtils.getCleanParam(request, "username");
		String password = request.getParameter("password");
		String host = request.getRemoteHost();
		// String validata = WebUtils.getCleanParam(request, "validatecode");
		boolean rememberMe = false;
		if (username != null) {
			username = new String(Base64.decodeBase64(username));
		}
		if (password != null) {
			password = new String(Base64.decodeBase64(password));
		}
		String salt = "java1234";// 盐值
		password = new Md5Hash(password, salt).toString();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password.toCharArray(), rememberMe, host);
		try {
			subject.login(token);// 身份验证
			//登陆成功后获得session会话。
			Session session = subject.getSession();
//			session.setAttribute(key, value);
//			session.removeAttribute(key);
//			session.getId();获取会话的唯一标志id
//			session.getHost();获取主机的ip
//			session.getTimeout();获取会话过期时间
//			session.setTimeout(maxIdleTimeInMillis);//设置当前会话的过期时间。
			PrincipalCollection princialCollection = subject.getPrincipals();
			Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();
			Set<String> realmNames = princialCollection.getRealmNames(); // 获取所有身份验证成功的Realm名字。

			System.out.println("身份验证结束，开始权限验证！！");
			// subject.isPermitted();//权限验证
			return "test";// 都通过，进入主页面
		} catch (Exception e) {
			// 这里将异常打印关闭是因为如果登录失败的话会自动抛异常
			// e.printStackTrace();
			model.addAttribute("error", "用户名或密码错误");
			return "../login";
		}
	}

}
