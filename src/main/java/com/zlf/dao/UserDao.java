package com.zlf.dao;

import com.zlf.bo.User;

public interface UserDao {
	 User findUserByUsername(String username);   
}
