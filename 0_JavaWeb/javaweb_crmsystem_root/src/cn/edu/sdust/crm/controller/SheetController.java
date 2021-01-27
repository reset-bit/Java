package cn.edu.sdust.crm.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sdust.crm.domain.Employee;
import cn.edu.sdust.crm.domain.Enums;
import cn.edu.sdust.crm.domain.Sheet;
import cn.edu.sdust.crm.service.EmpService;
import cn.edu.sdust.crm.service.SheetService;

@Controller
@RequestMapping("/sheet")
public class SheetController {

	@Autowired
	private SheetService sheetService;
	@Autowired
    private EmpService empService;
	
	@RequestMapping("/sheetList")
	public ModelAndView sheetList(ModelAndView mv) {
		
		List<Sheet> sheets = sheetService.ListAllSheets();
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets");
		
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView sheetSearch(
			@RequestParam("search-name") String t,
			@RequestParam("search-startCreateDate") String startCreateDate,
			@RequestParam("search-endCreateDate") String endCreateDate, ModelAndView mv) {
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		Sheet s = new Sheet();
		s.setTitle(t);

		Timestamp st,ed;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String str = df.format(new Date());
		
        if(startCreateDate != null && !startCreateDate.equals(""))
	    {
        	st = Timestamp.valueOf(startCreateDate);
	    }else {
	    	st = Timestamp.valueOf("2000-01-01 00:00:00");
	    }
        if(endCreateDate != null && !endCreateDate.equals(""))
		{
        	ed = Timestamp.valueOf(endCreateDate);
		}else {
			ed = Timestamp.valueOf(str);
		}
	
		List<Sheet> sheets = sheetService.ListSheetsByParam(s);
		
		List<Sheet> sheets1 = sheetService.qureyByParam(t, st, ed);
		
		mv.addObject("sheets", sheets);
		mv.addObject("sheets", sheets1);
		mv.setViewName("sheets");
		
		return mv;
	}
	
	@RequestMapping("/todelete")
	public ModelAndView sheetdelete(
			@RequestParam("id") int id, 
			ModelAndView mv){
		//调用service 删除一个部门
		sheetService.RemoveSheet(id);
		
		
		mv.setViewName("redirect:/sheet/sheetList");
		return mv;
	}
	@RequestMapping("/save")
	public ModelAndView sheetsave(@RequestParam("id") String id,
			@RequestParam("title")String title, ModelAndView mv){
		//判断deptid是不是为空 deptname 是否为空
		//System.out.println("---"+deptid+"---");
		//业务逻辑校验
		//如果部门名称已经存在 
		//DeptService.checkDeptName(String deptname)-->true / false
		
		
		if (id == ""){
			//新增
			Sheet s = new Sheet();
			s.setTitle(title);			
			sheetService.AddSheet(s); 
			
		}
		else{
			//修改
			Sheet s = new Sheet();
			s.setTitle(title);	
			s.setId(Integer.parseInt(id));
			
			sheetService.EditSheet(s); 
	 
		}
//		mv.setViewName("depts");
        mv.setViewName("redirect:/sheet/sheetList");
		return mv;
	} 
	
	@RequestMapping("/ListOp1")
	public ModelAndView ListOP1(ModelAndView mv) {
		
		Sheet s = new Sheet();
		List<Sheet> sheets = sheetService.ListSheetsByOp1(s);
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets1");
		
		return mv;
	}
	
	@RequestMapping("/ListOp2")
	public ModelAndView ListOP2(ModelAndView mv) {
		
		Sheet s = new Sheet();
		List<Sheet> sheets = sheetService.ListSheetsByOp2(s);
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets2");
		
		return mv;
	}
	
	@RequestMapping("/ListOp3")
	public ModelAndView ListOP3(ModelAndView mv) {
		
		Sheet s = new Sheet();
		List<Sheet> sheets = sheetService.ListSheetsByOp3(s);
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets3");
		
		return mv;
	}
	
	@RequestMapping("/ListOp4")
	public ModelAndView ListOP4(ModelAndView mv) {
		
		Sheet s = new Sheet();
		List<Sheet> sheets = sheetService.ListSheetsByOp4(s);
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets4");
		
		return mv;
	}
	
	@RequestMapping("/ListOp5")
	public ModelAndView ListOP5(ModelAndView mv) {
		
		Sheet s = new Sheet();
		List<Sheet> sheets = sheetService.ListSheetsByOp5(s);
		
		mv.addObject("sheets", sheets);
		mv.setViewName("sheets5");
		
		return mv;
	}
	
	//---------------------------------------------------------------
	@RequestMapping("/checklogin")
	public ModelAndView checkLogin(@RequestParam("empname")String empname,
			@RequestParam("emppassword")String emppassword,
			ModelAndView mv,
			HttpServletRequest request) {
		Employee emp = new Employee();
		emp.setEmpname(empname);
		emp.setEmppassword(emppassword);
		
		if(empService.hasName(empname)) {
			String curpwd = empService.getEmployeePasswordByName(empname);
			if(curpwd.equals(emppassword) && curpwd!=null) {
				request.getSession().setAttribute("empname", empname);
				mv.setViewName("redirect:/sheet/sheetList");
			}
			else {
				mv.setViewName("login");
			}
		}
		else {
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@RequestMapping("/addworksheet")
	public ModelAndView addWorkSheet(@RequestParam("title")String title,
			@RequestParam("associate")String associates,
			@RequestParam("importance")String importance,
			@RequestParam("template")String template,
			ModelAndView mv,
			HttpSession session) {
		String ori = (String)session.getAttribute("empname");
		String status = "已提交";
		String op = "提醒分配";

		java.util.Date d = new java.util.Date();
		Timestamp date = new Timestamp(d.getTime());
//		System.out.println(ori+"	"+title+"	"+associate+"	"+date);
		Sheet worksheet = new Sheet(title, status, 
				Integer.parseInt(template), 
				Integer.parseInt(importance), 
				ori, associates, date, op);
		List<Sheet> sheets = sheetService.ListAllSheets();
		worksheet.setId(sheets.get(sheets.size() - 1).getId() + 1);
		sheetService.AddSheet(worksheet);
		mv.setViewName("redirect:/sheet/sheetList");
		return mv;
	}
	@RequestMapping("/editworksheet")
	public ModelAndView editworksheet(@RequestParam("id")int id,
			@RequestParam("opration")String opration,
			ModelAndView mv) {
		List<Sheet> sheets = sheetService.ListAllSheets();
		Sheet sheet = new Sheet();
		for(int i = 0; i < sheets.size(); ++i) {
			if(sheets.get(i).getId() == id) {
				sheet = sheets.get(i);
				break;
			}
		}
		if("    ".equals(opration)) {
			mv.setViewName("redirect:/sheet/sheetList");
		}
		Enums en = new Enums();
		for(int i = 0; i < en.getOp().size(); ++i) {
			if(en.getOp().get(i).equals(opration)) {
				sheet.setOpration(en.getOp().get(i + 1));
				sheet.setStatus(en.getStatus().get(i + 1));
				if("申请完结".equals(opration)) {
					java.util.Date d = new java.util.Date();
					Timestamp date = new Timestamp(d.getTime());
					sheet.setAccepttime(date);
				}
				else {
					sheet.setAccepttime(null);
				}
				sheetService.EditSheet(sheet);
				break;
			}
		}
		mv.setViewName("redirect:/sheet/sheetList");
		return mv;
	}
	
}
