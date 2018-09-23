package com.atguigu.atcrowdfunding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.atcrowdfunding.bean.AJAXResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText, Integer pageno, Integer pagesize){
		AJAXResult result = new AJAXResult();
		try{
			Map<String, Object> map = new HashMap<>();
			map.put("start", (pageno - 1) * pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			List<User> users = userService.pageQueryData(map);
			//总数据条数
			int totalsize = userService.pageQueryCount(map);
			//最大页码
			int totalno = 0;
			if(totalsize % pagesize == 0){
				totalno = totalsize / pagesize;
			}else{
				totalno = totalsize / pagesize + 1;
			}
			//分页对象
			Page<User> userPage = new Page<>();
			userPage.setDatas(users);
			userPage.setPageno(pageno);
			userPage.setTotalno(totalno);
			userPage.setTotalsize(totalsize);
			result.setData(userPage);
			result.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "user/index";
	}
	
	/**
	 * 用户首页
	 * @return
	 */
	@RequestMapping("/index1")
	public String index1(@RequestParam(required=false, defaultValue="1")Integer pageno, 
			@RequestParam(required=false, defaultValue="2")Integer pagesize, 
			Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("start", (pageno - 1) * pagesize);
		map.put("size", pagesize);
		List<User> users = userService.pageQueryData(map);
		model.addAttribute("users", users);
		//当前页码
		model.addAttribute("pageno", pageno);
		//总数据条数
		int totalsize = userService.pageQueryCount(map);
		//最大页码
		int totalno = 0;
		if(totalsize % pagesize == 0){
			totalno = totalsize / pagesize;
		}else{
			totalno = totalsize / pagesize + 1;
		}
		model.addAttribute("totalno", totalno);
		return "user/index";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "user/add";
	}
	
	/**
	 * 用户新增
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(User user){
		AJAXResult result = new AJAXResult();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setCreatetime(sdf.format(new Date()));
			user.setLoginpassword("123456");
			userService.insertUser(user);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, Model model){
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( User user ) {
		AJAXResult result = new AJAXResult();
		try {
			userService.updateUser(user);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
