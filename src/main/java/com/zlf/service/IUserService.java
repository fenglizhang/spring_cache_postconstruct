package com.zlf.service;

import com.zlf.bo.User;

public interface IUserService {
	User findUserByUsername(String username);
}
