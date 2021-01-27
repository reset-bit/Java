package cn.sdust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Hello")
public class HelloController {
	@RequestMapping(method=RequestMethod.POST)
	public String Print(ModelMap model, @RequestParam("username")String username){
		model.addAttribute("message",username);
		return "Hello";//返回逻辑视图名
	}
}
