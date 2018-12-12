package com.zlf.shiro;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.zlf.bo.Permission;
import com.zlf.bo.Role;
import com.zlf.bo.User;
import com.zlf.service.IUserService;

/**
 * shiro
 * 中的AuthorizingRealm有2个方法doGetAuthorizationInfo()和doGetAuthenticationInfo(
 * ),一般实际开发中
 * ，我们都继承AuthorizingRealm类然后重写doGetAuthorizationInfo和doGetAuthenticationInfo。
 * doGetAuthenticationInfo这个方法是在用户登录的时候调用的也就是执行SecurityUtils
 * .getSubject().login（）
 * 的时候调用；而doGetAuthorizationInfo方法是在我们调用SecurityUtils.getSubject
 * ().isPermitted（）这个方法时会调用doGetAuthorizationInfo
 * （），一而我们的@RequiresPermissions这个注解起始就是在执行SecurityUtils.
 * getSubject().isPermitted（
 * ）。我们在某个方法上加上@RequiresPermissions这个，那么我们访问这个方法的时候，就会自动调用SecurityUtils
 * .getSubject().isPermitted（）
 * 
 * @author Administrator
 * 
 */

@Service
public class UserRealm extends AuthorizingRealm {
	@Resource
	private IUserService userService;

	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// String username = (String) getAvailablePrincipal(principals);
		String username = (String) principals.getPrimaryPrincipal();

		Set<Role> roleSet = userService.findUserByUsername(username)
				.getRoleSet();
		// 角色名的集合
		Set<String> roles = new HashSet<String>();
		// 权限名的集合
		Set<String> permissions = new HashSet<String>();

		Iterator<Role> it = roleSet.iterator();
		while (it.hasNext()) {
			roles.add(it.next().getName());
			for (Permission per : it.next().getPermissionSet()) {
				permissions.add(per.getName());
			}
		}

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);

		return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码

		User user = userService.findUserByUsername(username);

		if (user == null) {
			// 木有找到用户
			throw new UnknownAccountException("没有找到该账号");
		}
		if (!(password != null && password.equals(user.getPassword()))) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		/**
		 * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user.getUsername(), user.getPassword(), getName());
		return info;
	}

}
