package cn.edu.sdust.crm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sdust.crm.domain.Employee;
import cn.edu.sdust.crm.domain.Sheet;
import cn.edu.sdust.crm.service.EmpService;

@Controller
@RequestMapping("/crm")
public class HelloController {
	@Autowired
    private EmpService empService;
	
	@RequestMapping("/checklogin")
	public ModelAndView checkLogin(@RequestParam("empname")String empname,
			@RequestParam("emppassword")String emppassword,
			ModelAndView mv,
			HttpServletRequest request) {
		Employee emp = new Employee();
		emp.setEmpname(empname);
		emp.setEmppassword(emppassword);
		
		String curpwd = empService.getEmployeePasswordByName(empname);
		if(curpwd.equals(emppassword)) {
			request.getSession().setAttribute("empname", empname);
			mv.setViewName("/sheet/sheetList");
		}
		else {
			mv.setViewName("login");
		}
		return mv;
	}

//	@RequestMapping("/addworksheet")
//	public ModelAndView addWorkSheet(@RequestParam("title")String title,
//			@RequestParam("associate")String associate,
//			@RequestParam("importance")String importance,
//			@RequestParam("template")String template,
//			ModelAndView mv,
//			HttpSession session) {
//		String ori = (String)session.getAttribute("empname");
//		String status = "已提交";
//		String op = "提醒分配";
//		Date date = new Date();//获取任务发布的时间
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
////		String date1 = sdf.format(date);
////		System.out.println(date1);
////		System.out.println(ori+"	"+title+"	"+associate);//+"	"+date1
//		
//		Sheet worksheet = new Sheet(title, status, 
//				Integer.parseInt(template), 
//				Integer.parseInt(importance), 
//				ori, associate, date, op);
//		empService.AddWorkSheet(worksheet);
//		mv.setViewName("worksheet");
//		return mv;
//	}
}
