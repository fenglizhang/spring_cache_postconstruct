package com.zlf.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

/**
 * 会话存储/持久化 Shiro提供SessionDAO用于会话的CRUD，即DAO（Data Access Object）模式实现
 * 
 * @author Administrator
 * 
 */
public class MySessionDao extends CachingSessionDAO {

	@Override
	protected void doUpdate(Session session) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(Session session) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
