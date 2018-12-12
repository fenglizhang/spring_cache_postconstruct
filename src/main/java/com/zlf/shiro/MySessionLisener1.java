package com.zlf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
/**
 * 会话监听器用于监听会话创建、过期及停止事件： 
 * @author Administrator
 *
 */
public class MySessionLisener1 implements SessionListener {

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		System.out.println("会话创建时触发：" + session.getId());  
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		  System.out.println("会话停止是触发：" + session.getId());  
	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		 System.out.println("会话过期时触发：" + session.getId());  
	}

}
