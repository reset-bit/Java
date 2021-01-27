package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;

public class Delete {
	private String tableName;
	private String[] conditions = new String[10];//删除条件
	private DBFManager dbfmanager = new DBFManager();
	
	public Delete(String sql) {
		String[] temp = sql.split(" ");
		tableName = temp[2];
		int j = 0;
		for(int i = 3; i < temp.length; ++i) {
			if(temp[i].equals("where") | temp[i].equals("and")) {
				conditions[j++] = temp[i + 1];
			}
		}
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * 获取操作列全部属性，依据删除条件定位删除行，使用remove()删除该行。最后更新数据。
	 * */
	public ArrayList<String[]> delete(String filespath, ArrayList<String[]> all){
		ArrayList<String[]> filter = all;
		
		for(int i = 0; i < conditions.length; ++i) {
			if(conditions[i] == null) {
				break;
			}
			String[] required = new String[] {};//单个删除条件
			//依据删除条件分类删除
			if(conditions[i].indexOf('=') > 0) {
				required = conditions[i].split("=");
				String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
				for(int j = aimField.length - 1; j > 1; --j) {//倒序减
					String old = required[1].replace("'", " ").replace("'", " ").trim();
//					System.out.println("old=="+old);//test
					if(aimField[j].equals(old)) {//在此删除
//						System.out.println("cur=="+aimField[j]);//test
						filter.remove(j + 1);//加head
					}
				}
			}
			else if(conditions[i].indexOf('>') > 0) {
				required = conditions[i].split(">");
				String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
				for(int j = aimField.length - 1; j > 1; --j) {
					if(Integer.parseInt(aimField[j]) > Integer.parseInt(required[1])) {//在此删除
						filter.remove(j + 1);
					}
				}
			}
			else if(conditions[i].indexOf('<') > 0) {
				required = conditions[i].split("<");
				String[] aimField = dbfmanager.getFieldByAttribute(filespath, tableName + ".dbf", required[0]);
				for(int j = aimField.length - 1; j > 1; --j) {
					if(Integer.parseInt(aimField[j]) < Integer.parseInt(required[1])) {//在此删除
						filter.remove(j + 1);
					}
				}
			}
		}
//		dbfmanager.show(filter);//test
		dbfmanager.updateRecords(filespath, tableName + ".dbf", filter);
//		dbfmanager.show(dbfmanager.getDBF(filespath, getTableName() + ".dbf"));//test
		return filter;
	}

}
