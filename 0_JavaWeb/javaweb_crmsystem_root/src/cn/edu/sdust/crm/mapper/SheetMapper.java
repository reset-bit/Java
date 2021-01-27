package cn.edu.sdust.crm.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.crm.domain.Sheet;


public interface SheetMapper {
	
	//根据服务层给出的业务规范，建立数据访问层对应的映射方法
	//cn.edu.sdust.crm.DeptService.ListAllDepts()
	public List<Sheet> SelectAllSheets();
	
	public List<Sheet> SelectSheetsByParam(Sheet s);
	
	public int UpdateSheet(Sheet s);
	
	public int DeleteSheet(int id);
	
	public Sheet SelectSheetById(int id);

    public List<Sheet> SelectSheetsByOp1(Sheet s);
	
	public List<Sheet> SelectSheetsByOp2(Sheet s);
	
	public List<Sheet> SelectSheetsByOp3(Sheet s);
	
	public List<Sheet> SelectSheetsByOp4(Sheet s);
	
	public List<Sheet> SelectSheetsByOp5(Sheet s);
	
	public List<Sheet> qureyByParam(@Param("t") String t,@Param("st") Timestamp st,@Param("ed") Timestamp ed);
	
	//-------------------------------------------
	public int InsertSheet(
			@Param("id")int id,
			@Param("title")String title,
			@Param("status")String status,
			@Param("importance")int importance,
			@Param("template")int template,
			@Param("originator")String originator,
			@Param("associates")String associates,
			@Param("opration")String opration,
			@Param("create")Timestamp create);
	
}

    
