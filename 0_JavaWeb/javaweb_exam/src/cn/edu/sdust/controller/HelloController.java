package cn.edu.sdust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sdust.domain.Tutor;
import cn.edu.sdust.service.ITutorService;

@Controller
public class HelloController {
	@Autowired
	ITutorService tutorservice;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView allusers(ModelAndView mv) {
		List<Tutor> tutor = tutorservice.getAllTutors();
		System.out.println(tutor.size());
		mv.addObject("tutors", tutor);
		mv.setViewName("tutors.jsp");
		return mv;
	}
	
	@RequestMapping(value="/add")
	public ModelAndView useradd(ModelAndView mv) {		
		mv.setViewName("add.jsp");
		return mv;
	}
	
	@RequestMapping(value="/save")
	public ModelAndView usersave(@RequestParam("tutorid") int tutorid,
			@RequestParam("tutorname") String tutorname,
			@RequestParam("years") int years,
			@RequestParam("tutorinformation") String tutorinformation,
			ModelAndView mv) {
//		System.out.println(tutorid);
		
		Tutor tutor = new Tutor();
		tutor.setTutorname(tutorname);
		tutor.setYears(years);
		tutor.setTutorinformation(tutorinformation);
		if(tutorid < 0) {//add
			System.out.println("this add!");
			//获得已添加的最新编号
			List<Tutor> users = tutorservice.getAllTutors();
			tutor.setTutorid(users.get(users.size() - 1).getTutorid() + 1);
			//System.out.println(user.getId());
			tutorservice.addTutor(tutor);
		}
		else {//edit
			System.out.println("this edit!");
		}
		
		mv.setViewName("redirect:/list");
		return mv;
	}
	
	
}
