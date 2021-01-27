package cn.edu.sdust.database;

import java.io.*;
import java.util.ArrayList;

import com.linuxense.javadbf.*;

public class DBFUtil {	
	public boolean createDBF(String path) {
		boolean isSuccess = false;
		try {
			File file = new File(path);
			if(file.createNewFile()) {
				isSuccess = true;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public ArrayList<String[]> readDBF(String path) {
		ArrayList<String[]> all = new ArrayList<String[]>();
		InputStream fis = null;
		try {
			fis = new FileInputStream(path);
			// 根据输入流初始化一个DBFReader实例，用来读取DBF文件信息
			DBFReader reader = new DBFReader(fis);
			int fieldsCount = reader.getFieldCount();// 调用DBFReader对实例方法得到path文件中字段的个数
			String[] headLine = new String[fieldsCount];
	 
			// 取出字段信息
			for (int i = 0; i < fieldsCount; i++) {
				DBFField field = reader.getField(i);
				headLine[i] = (String)field.getName();
			}
			all.add(headLine);
			
			Object[] rowValues;
			// 一条条取出path文件中记录
			while ((rowValues = reader.nextRecord()) != null) {
				String[] dataLine = new String[fieldsCount];
				for (int i = 0; i < rowValues.length; i++) {
					dataLine[i] = (String)rowValues[i];
				}
				all.add(dataLine);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return all;
	}

	//按字段名查找整个DBF列
	public String[] readFieldByFieldname(String path, String attributeName) {
		DBFReader reader = null;
		String[] allAttributes = {};
		try {
			reader = new DBFReader(new FileInputStream(path));
//			//显示所有列名
//			int numberOfFields = reader.getFieldCount();
//			for (int i = 0; i < numberOfFields; i++) {
//				DBFField field = reader.getField(i);
//				System.out.println(field.getName());
//			}
			DBFRow row;
			String temp = "";
			String all = "";
			while ((row = reader.nextRow()) != null) {
				temp = row.getString(attributeName).replace('\n', ' ').trim();
				if(all.equals("")) {
					all = temp;
				}
				else{
					all += "," + temp;
				}
			}
			if(all.contains(",")) {
				allAttributes = all.split(",");
			}
			else {
				allAttributes = new String[] {all};
			}
//			for(int i = 0; i < allAttributes.length; ++i) {
//				System.out.println("see==>"+allAttributes[i]);
//			}
		} catch (DBFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			DBFUtils.close(reader);
		}
		return allAttributes;
	}
	
	//对未存在的文件写记录
	public void writeDBF(String path, ArrayList<String[]> head, ArrayList<String[]> datas) {
		int hlength = head.size();
		int dslength = datas.size();
		try {
			/*  定义DBF文件字段 
			 *  case DBFField.FIELD_TYPE_C: // 字符串
				case DBFField.FIELD_TYPE_M: // 可以用字符串传值
				case DBFField.FIELD_TYPE_D: // 时期
				case DBFField.FIELD_TYPE_F: // 数值 用Double
				case DBFField.FIELD_TYPE_N: // 数值 用Int
				case DBFField.FIELD_TYPE_L: // Boolean
			 * */  
			DBFField[] fields = new DBFField[hlength];
			for(int i = 0; i < hlength; ++i) {
				fields[i] = new DBFField();
				fields[i].setName(head.get(i)[0]);
				String len = "";		//自定义类型属性长度
				int pos = -1;			//自定义类型属性长度的起始位置
				if(head.get(i)[1].contains("(")) {
					pos = head.get(i)[1].indexOf("(") + 1;
					if(pos > 0) {
						int tail = head.get(i)[1].indexOf(")");
						len = head.get(i)[1].substring(pos, tail);
					}
				}
				String type = head.get(i)[1];
				if(pos != -1) {
					type = head.get(i)[1].substring(0, pos - 1);
				}
				switch(type) {
				case "char":
				case "varchar":
					fields[i].setDataType(DBFField.FIELD_TYPE_C);
					fields[i].setFieldLength(Integer.parseInt(len)); 
					break;
				case "double":
				case "int":
					fields[i].setDataType(DBFField.FIELD_TYPE_N);
					fields[i].setFieldLength(20); 
					break;
				}
			}	  
 
			//定义DBFWriter实例用来写DBF文件   
			DBFWriter writer = new DBFWriter(new FileOutputStream(path));   
			//把字段信息写入DBFWriter实例，即定义表结构   
			writer.setFields(fields);
			//加入头部信息便于修改
			Object[] rowData = new Object[hlength];
			for(int i = 0; i < hlength; ++i) {
				rowData[i] = head.get(i)[1];
//				System.out.println(head.get(i)[1]);//test
			}
			writer.addRecord(rowData);
			//一条条写入记录，第一条为索引标记
			for(int i = 0; i < dslength; ++i) {
				rowData = new Object[hlength];
				for(int j = 0; j < hlength; ++j) {
					rowData[j] = datas.get(i)[j];
				}
				writer.addRecord(rowData);   
			} 
			writer.close();
		} catch(Exception e) {   
			e.printStackTrace();   
		}
	}
	
	
	//对已存在的文件写记录
	public void writeDBF(String path, ArrayList<String[]> datas) {
		int dslength = datas.size();
		int dlength = datas.get(0).length;
		try {
			DBFWriter writer = new DBFWriter(new File(path));
			for(int i = 0; i < dslength; ++i) {
				Object[] rowData = new Object[dlength];
				for(int j = 0; j < dlength; ++j) {
					rowData[j] = datas.get(i)[j];
				}
				writer.addRecord(rowData);
			}
			writer.close();
		} catch(Exception e) {   
			e.printStackTrace();   
		}
	}
	
	
	//对已存在的文件删除记录：读取head与body，重新写入文件
	public void updateDBF(String path, ArrayList<String[]> all) {
		ArrayList<String[]> head = new ArrayList<String[]>();
		ArrayList<String[]> data = new ArrayList<String[]>();
		for(int i = 0; i < all.size(); ++i) {
			if(i == 0) {//取头部属性名
				for(int k = 0; k < all.get(0).length; ++k) {
					head.add(new String[] {all.get(i)[k], ""});
				}
			}
			else if(i == 1) {//取头部属性类型
				for(int k = 0; k < all.get(0).length; ++k) {
					String[] temp = head.get(k);
					temp[1] = all.get(i)[k];
					head.set(k, temp);
				}					
			}
			else {
				data.add(all.get(i));
			}
		}
//		//show for head test
//		System.out.println("test for head");
//		for(int i = 0; i < head.size(); ++i) {
//			for(int j = 0; j < head.get(i).length; ++j) {
//				System.out.println(head.get(i)[j]);
//			}
//		}
		writeDBF(path, head, data);
	}	
}
