package cn.edu.sdust.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.edu.sdust.service.IUserService;

@Controller
@RequestMapping("/user")
public class HelloController {
	@Autowired
	IUserService userservice;
	
	@RequestMapping(method=RequestMethod.POST)
	public String checkUserByPassword(HttpServletRequest request, @RequestParam("username")String username, @RequestParam("password")String password) {
		HttpSession session = request.getSession();
		try {
			int num = userservice.checkUserByPassword(username, password);
			if(num > 0) {
				session.setAttribute("username", username);
				return "/WEB-INF/views/welcome.jsp";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("error", "用户名或密码错误");
		return "/WEB-INF/views/fail.jsp";
	}
}
