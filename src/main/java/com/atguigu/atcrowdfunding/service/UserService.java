package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;
import com.atguigu.atcrowdfunding.bean.User;

public interface UserService {

	public List<User> queryAll();
	
	public User query4Login(User user);
	
	public List<User> pageQueryData(Map<String, Object> map);
	
	public int pageQueryCount(Map<String, Object> map);

	public void insertUser(User user);

	public User queryById(Integer id);

	public void updateUser(User user);
}
