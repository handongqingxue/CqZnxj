package com.cqZnxj.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.User;
import com.cqZnxj.service.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}
}
