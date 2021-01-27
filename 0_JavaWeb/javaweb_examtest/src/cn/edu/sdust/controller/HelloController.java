package cn.edu.sdust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sdust.domain.User;
import cn.edu.sdust.service.IUserService;

@Controller
public class HelloController {
	@Autowired
	IUserService userservice;
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public ModelAndView allusers(ModelAndView mv) {
		List<User> users = userservice.getAllUsers();
		System.out.println(users.size());
		mv.addObject("users", users);
		mv.setViewName("users.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add")
	public ModelAndView useradd(ModelAndView mv) {		
		mv.setViewName("add.jsp");
		return mv;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView useredit(@RequestParam("id")int id, ModelAndView mv) {
		System.out.println("this edit!");
		
		User edit_user = userservice.getUserById(id);
		mv.addObject("user", edit_user);
		mv.setViewName("edit.jsp");
		return mv;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView usersearch(@RequestParam("selection") String selection,
			@RequestParam("data") String data,
			ModelAndView mv) {
		System.out.println("this search!");
		
		List<User> users = null;
		if(data.equals("Enter here")) {//未输入值
			users = userservice.getAllUsers();
		}
		else if(selection.equals("id")){//输入的是id，此时只返回一个用户
			int id = Integer.parseInt(data);
			User user = userservice.getUserById(id);
			mv.addObject("user", user);
			mv.setViewName("search.jsp");
			return mv;
		}
		else if(selection.equals("username")) {
			users = userservice.getUserByUsername(data);
		}
		else if(selection.equals("mobile")) {
			users = userservice.getUserByMobile(data);
		}
		else if(selection.equals("deptid")) {
			int deptid = Integer.parseInt(data);
			users = userservice.getUserByDeptid(deptid);
		}
		mv.addObject("users", users);
		mv.setViewName("search.jsp");
		return mv;
	}
	
	@RequestMapping(value="/delete")
	public ModelAndView userdelete(@RequestParam("id") int id, ModelAndView mv) {
		userservice.removeUserById(id);

		System.out.println("this delete!");
		mv.setViewName("redirect:/userlist");
		return mv;
	}
	
	@RequestMapping(value="/save")
	public ModelAndView usersave(@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("mobile") String mobile,
			@RequestParam("deptid") int deptid,
			ModelAndView mv) {
//		System.out.println(deptid);
		
		User user = new User();
		user.setUsername(username);
		user.setMobile(mobile);
		user.setDeptid(deptid);
		if(id < 0) {//add
			System.out.println("this add!");
			List<User> users = userservice.getAllUsers();
			user.setId(users.get(users.size() - 1).getId() + 1);
			//System.out.println(user.getId());
			userservice.addUser(user);
		}
		else {//edit
			System.out.println("this edit!");
			user.setId(id);
			userservice.editUser(user);
		}
		
		mv.setViewName("redirect:/userlist");
		return mv;
	}
	
	
}
