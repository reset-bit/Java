package cn.edu.sdust.database.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.edu.sdust.database.DBFManager;

public class Select {
	private String tableName;
	private String[] conditions = new String[10];		//查询条件
	private String[] fields = new String[] {};			//查询结果包含的列
	private DBFManager dbfmanager = new DBFManager();
	
	public Select(String sql) {
		String[] temp = sql.split(" ");
		if(temp[1].length() == 1) {//*
			fields = new String[] {temp[1]};
		}
		else {//列名
			fields = temp[1].split(",");
		}
		tableName = temp[3];
		int j = 0;										//添加查询条件conditions[]下标
		for(int i = 4; i < temp.length; ++i) {
			if(temp[i].equals("where") | temp[i].equals("and")) {
				conditions[j++] = temp[i + 1];
			}
		}
	}

	public String getTableName() {
		return tableName;
	}
	
	/**
	 * 获得全部满足条件的行，通过fields将获得行中不需要的列去除
	 * */
	public ArrayList<String[]> select(String filespath, ArrayList<String[]> all){
		ArrayList<String[]> filter = all;
		
		if(!fields[0].equals("*")) {
			filter = new ArrayList<String[]>();
			//获得所有满足条件的行（包含全部属性列）
			filter.add(all.get(0));//head
			filter.add(all.get(1));//head attribute
			filter.add(all.get(2));//hava index
			//遍历查询条件
			for(int i = 0; i < conditions.length; ++i) {
				if(conditions[i] == null) {
					if(i == 0) {
						filter = all;
					}
					break;
				}
				String[] required = new String[] {};
				if(conditions[i].indexOf('=') > 0) {
					required = conditions[i].split("=");
					String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
					for(int j = 0; j < aimField.length; ++j) {
						if(aimField[j].equals(required[1].replace("'", " ").replace("'", " ").trim())) {
							if(filter.size() > 3) {
								boolean isRepeat = false;
								for(int k = 3; k < filter.size(); ++k) {
									if(Arrays.equals(filter.get(k), all.get(j + 1))) {
										isRepeat = true;
										break;
									}
								}
								if(!isRepeat) {
									filter.add(all.get(j + 1));
								}
							}
							else {
								filter.add(all.get(j + 1));
							}
						}
					}
				}
				else if(conditions[i].indexOf('>') > 0) {
					required = conditions[i].split(">");
					String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
					for(int j = 0; j < aimField.length; ++j) {
						if(Integer.parseInt(aimField[j]) > Integer.parseInt(required[1].replace("'", " ").replace("'", " ").trim())) {
							if(filter.size() > 3) {
								boolean isRepeat = false;
								for(int k = 3; k < filter.size(); ++k) {
									if(Arrays.equals(filter.get(k), all.get(j + 1))) {
										isRepeat = true;
										break;
									}
								}
								if(!isRepeat) {
									filter.add(all.get(j + 1));
								}
							}
							else {
								filter.add(all.get(j + 1));
							}
						}
					}
				}
				else if(conditions[i].indexOf('<') > 0) {
					required = conditions[i].split("<");
					String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
					for(int j = 0; j < aimField.length; ++j) {
						if(Integer.parseInt(aimField[j]) < Integer.parseInt(required[1].replace("'", " ").replace("'", " ").trim())) {
							if(filter.size() > 3) {
								boolean isRepeat = false;
								for(int k = 3; k < filter.size(); ++k) {
									if(Arrays.equals(filter.get(k), all.get(j + 1))) {
										isRepeat = true;
										break;
									}
								}
								if(!isRepeat) {
									filter.add(all.get(j + 1));
								}
							}
							else {
								filter.add(all.get(j + 1));
							}
						}
					}
				}
			}
			
			//从获得行中获取目标属性列，遍历头部记录不需要的属性列，依据记录值去掉不需要数据后重新写入
			ArrayList<String[]> temp = filter;
			ArrayList<String> col = new ArrayList<String>();		//当前操作行
			ArrayList<Integer> noNeed = new ArrayList<Integer>();	//需要删除的列序号
			
			//head
			List<String> temphead = Arrays.asList(temp.get(0));//将String[]转ArrayList便于动态移除
			col = new ArrayList<>(temphead);
			while(noNeed.size() != (all.get(0).length - fields.length)) {//仍有多余列需要删除
				for(int k = col.size() - 1; k >= 0; --k) {
					boolean need = false;
					for(int i = 0; i < fields.length; ++i) {
						if(col.get(k).equals(fields[i])) {
							need = true;
						}
					}
					if(!need) {//若属性列不需要则去除
						col.remove(k);
						noNeed.add(k);//从右到左记录不需要的属性列
					}
				}
			}
			filter.set(0, (String[])col.toArray(new String[col.size()]));
			//body
			for(int j = 1; j < filter.size(); ++j) {
				List<String> tempbody = Arrays.asList(temp.get(j));
				col = new ArrayList<>(tempbody);
				for(int k = 0; k < noNeed.size(); ++k) {
					col.remove((int)noNeed.get(k));
				}
				filter.set(j, (String[])col.toArray(new String[col.size()]));
			}
		}
		return filter;
	}
}
