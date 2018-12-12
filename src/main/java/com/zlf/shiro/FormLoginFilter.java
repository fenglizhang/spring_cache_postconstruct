package com.zlf.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class FormLoginFilter extends PathMatchingFilter {
	private String loginUrl = "../login";
	private String successUrl = "/test";

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		boolean authenticated = subject.isAuthenticated();// 判断是否已经登陆了
		if (authenticated) {
			return true;// 登陆过了。
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (isLoginRequest(req)) {
			if ("post".equalsIgnoreCase(req.getMethod())) {// form表单提交
				boolean loginSuccess = login(req); // 登录
				if (loginSuccess) {
					return redirectToSuccessUrl(req, resp);
				}
			}
			return true;// 继续过滤器链
		} else {// 保存当前地址并重定向到登录界面
			saveRequestAndRedirectToLogin(req, resp);
			return false;
		}
	}

	private boolean redirectToSuccessUrl(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		WebUtils.redirectToSavedRequest(req, resp, successUrl);
		return false;
	}

	private void saveRequestAndRedirectToLogin(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		WebUtils.saveRequest(req);
		WebUtils.issueRedirect(req, resp, loginUrl);
	}

	private boolean login(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			if (username != null) {
				username = new String(Base64.decodeBase64(username));
			}
			if (password != null) {
				password = new String(Base64.decodeBase64(password));
			}
			String salt = "java1234";// 盐值
			password = new Md5Hash(password, salt).toString();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			SecurityUtils.getSubject().login(token);
		} catch (Exception e) {
			req.setAttribute("shiroLoginFailure", e.getClass());
			return false;
		}
		return true;
	}

	private boolean isLoginRequest(HttpServletRequest req) {
		return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
	}

}
