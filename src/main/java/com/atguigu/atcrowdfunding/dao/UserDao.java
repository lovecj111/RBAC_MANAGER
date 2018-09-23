package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import com.atguigu.atcrowdfunding.bean.User;

public interface UserDao {

	@Select("select * from t_user")
	public List<User> queryAll();
	
	@Select("select * from t_user where loginaccount=#{loginaccount} and loginpassword=#{loginpassword}")
	public User query4Login(User user);

	public List<User> pageQueryData(Map<String, Object> map);
	
	public int pageQueryCount(Map<String, Object> map);

	public void insertUser(User user);

	@Select("select * from t_user where id = #{id}")
	public User queryById(Integer id);

	public void updateUser(User user);
}
