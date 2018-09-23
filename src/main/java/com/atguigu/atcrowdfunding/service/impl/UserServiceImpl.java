package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}

	@Override
	public User query4Login(User user) {
		return userDao.query4Login(user);
	}

	@Override
	public List<User> pageQueryData(Map<String, Object> map) {
		return userDao.pageQueryData(map);
	}

	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return userDao.pageQueryCount(map);
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User queryById(Integer id) {
		return userDao.queryById(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}
