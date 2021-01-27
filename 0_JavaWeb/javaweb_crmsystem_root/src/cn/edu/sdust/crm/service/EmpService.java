package cn.edu.sdust.crm.service;

import cn.edu.sdust.crm.domain.Sheet;

public interface EmpService {
	public String getEmployeeNameById(int id);
	public String getEmployeePasswordByName(String empname);
	public boolean hasName(String empname);
}
