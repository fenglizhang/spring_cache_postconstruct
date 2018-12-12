package com.zlf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zlf.bo.User;
import com.zlf.dao.UserDao;
import com.zlf.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserDao ud;
	
	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return ud.findUserByUsername(username);
	}

}
