package cn.edu.sdust.crm.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.crm.domain.Sheet;

public interface SheetService {

	//来自于需求。给出的业务需求，列出所有的部门
	public List<Sheet> ListAllSheets();
	
	public List<Sheet> ListSheetsByParam(Sheet s);
	//新增一个部门
	public int AddSheet(Sheet s);
	
	//修改一个部门
	public int EditSheet(Sheet s);
	
	//删除一个部门
	public int RemoveSheet(int id);
	
	public Sheet ListSheetById(int id);
	
    public List<Sheet> ListSheetsByOp1(Sheet s);
	
	public List<Sheet> ListSheetsByOp2(Sheet s);
	
	public List<Sheet> ListSheetsByOp3(Sheet s);
	
	public List<Sheet> ListSheetsByOp4(Sheet s);
	
	public List<Sheet> ListSheetsByOp5(Sheet s);
	
	public List<Sheet> qureyByParam(@Param("t") String t, @Param("st") Timestamp st,@Param("ed") Timestamp ed);
}
