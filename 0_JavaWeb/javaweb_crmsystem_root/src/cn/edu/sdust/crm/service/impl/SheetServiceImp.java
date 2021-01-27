package cn.edu.sdust.crm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdust.crm.domain.Sheet;
import cn.edu.sdust.crm.mapper.SheetMapper;
import cn.edu.sdust.crm.service.SheetService;

@Service("sheetService")
public class SheetServiceImp implements SheetService {

	@Autowired
	private SheetMapper sheetMapper;
	
	@Override
	public List<Sheet> ListAllSheets() {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectAllSheets();
	}


	@Override
	public List<Sheet> ListSheetsByParam(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByParam(s);
	}


	@Override
	public int EditSheet(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.UpdateSheet(s);
	}


	@Override
	public int RemoveSheet(int id) {
		// TODO 自动生成的方法存根
		return sheetMapper.DeleteSheet(id);
	}


	@Override
	public Sheet ListSheetById(int id) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetById(id);
	}
	
	@Override
	public List<Sheet> ListSheetsByOp1(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByOp1(s);
	}

	
	@Override
	public List<Sheet> ListSheetsByOp2(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByOp2(s);
	}
	
	
	@Override
	public List<Sheet> ListSheetsByOp3(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByOp3(s);
	}
	
	
	@Override
	public List<Sheet> ListSheetsByOp4(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByOp4(s);
	}
	
	
	@Override
	public List<Sheet> ListSheetsByOp5(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.SelectSheetsByOp5(s);
	}
	
	@Override
	public List<Sheet> qureyByParam(String t, Timestamp st, Timestamp ed) {
		// TODO 自动生成的方法存根
		return sheetMapper.qureyByParam(t, st, ed);
	}

//-----------------------------------------------------
	@Override
	public int AddSheet(Sheet s) {
		// TODO 自动生成的方法存根
		return sheetMapper.InsertSheet(
				s.getId(),
				s.getTitle(),
				s.getStatus(),
				s.getImportance(),
				s.getTemplate(),
				s.getOriginator(),
				s.getAssociates(),
				s.getOpration(),
				s.getCreatetime()
		);
	}

}
