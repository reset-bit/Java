package cn.edu.sdust.crm.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.crm.domain.Employee;

public interface EmpMapper { 
	public List<Employee> allEmployee();
	public Employee selectEmployeeById(int empid);
	public Employee selectEmployeeByName(String empname);
}
