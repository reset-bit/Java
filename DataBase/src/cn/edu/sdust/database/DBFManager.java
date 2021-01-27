package cn.edu.sdust.database;

import java.io.*;
import java.util.ArrayList;

import com.linuxense.javadbf.*;

public class DBFManager {
	private DBFUtil dbf = new DBFUtil(); 
	
	public void show(ArrayList<String[]> all) {
		int dataLen = all.get(0).length;		
		for(int i = 0; i < all.size(); i++) {
			if(i == 1 | i == 2) {//辅助信息不读
				continue;
			}
			for (int j = 0; j < dataLen; j++) {
				System.out.format("%-20s", all.get(i)[j]);
			}
			System.out.println();
			
			if(i == 0) {
				for (int j = 0; j < dataLen; j++) {
					System.out.print("--------------------");
				}
				System.out.println();
			}
		}
	}
	
	public ArrayList<String[]> getDBF(String filepath, String fileName) {
		String path = filepath + "/" + fileName;
		ArrayList<String[]> all = dbf.readDBF(path);
		return all;
	}
	
	public boolean isFileExist(String filepath, String fileName) {
		String path = filepath + "/" + fileName;
		File file = new File(path);
		return file.exists();
	}
	
	//对未存在的文件添加记录
	public void addRecords(String filepath, String fileName, ArrayList<String[]> head, ArrayList<String[]> datas) {
		String path = filepath + "/" + fileName;
		dbf.createDBF(path);
		dbf.writeDBF(path, head, datas);
	}
	
	//对已存在的文件添加记录
	public void addRecords(String filepath, String fileName, ArrayList<String[]> datas) {
		String path = filepath + "/" + fileName;
		dbf.writeDBF(path, datas);
	}
	
	//对已存在的文件更新记录
	public void updateRecords(String filepath, String fileName, ArrayList<String[]> all) {
		String path = filepath + "/" + fileName;
		dbf.updateDBF(path, all);
	}

	//按字段名查找整个DBF列
	public String[] getFieldByAttribute(String filepath, String fileName, String attribute) {
		String path = filepath + "/" + fileName;
		String[] allAttributes = dbf.readFieldByFieldname(path, attribute);
//		//show for test
//		for(int i = 0; i < allAttributes.length; ++i) {
//			System.out.print("\t"+allAttributes[i]);
//		}
//		System.out.println();
		return allAttributes;
	}

}
