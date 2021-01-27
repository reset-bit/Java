package cn.edu.sdust.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdust.crm.domain.Employee;
import cn.edu.sdust.crm.domain.User;
import cn.edu.sdust.crm.domain.Sheet;
import cn.edu.sdust.crm.mapper.EmpMapper;
import cn.edu.sdust.crm.service.EmpService;

@Service("EmpService")
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpMapper empMapper;

	@Override
	public String getEmployeeNameById(int empid) {
		return empMapper.selectEmployeeById(empid).getEmpname();
	}

	@Override
	public String getEmployeePasswordByName(String empname) {
		// TODO Auto-generated method stub
		return empMapper.selectEmployeeByName(empname).getEmppassword();
	}

	@Override
	public boolean hasName(String empname) {
		// TODO Auto-generated method stub
		List<Employee> all = empMapper.allEmployee();
		for(int i = 0; i < all.size(); ++i) {
			if(empname.equals(all.get(i).getEmpname())) {
				return true;
			}
		}
		return false;
	}
}
