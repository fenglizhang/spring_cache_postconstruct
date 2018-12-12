package com.zlf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
/**
 * 如果只想监听某一个事件，可以继承SessionListenerAdapter实现：
 * @author Administrator
 *
 */
public class MySessionListener2 extends SessionListenerAdapter {

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		 System.out.println("会话创建：" + session.getId());  
	}

}
