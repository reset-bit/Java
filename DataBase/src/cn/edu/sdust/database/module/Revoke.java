package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;

public class Revoke {
	private String userName;
	private String tableName;						//需要操作的表
	private String[] conditions = new String[] {};	//需要移除的权限
	private DBFManager dbfmanager = new DBFManager();
	
	public Revoke(String sql) {
		String[] temp = sql.split(" ");
		conditions = temp[1].split(",");
		tableName = temp[3];
		userName = temp[5];
	}
	
	public boolean revoke(String filespath) {
		ArrayList<String[]> all = dbfmanager.getDBF(filespath, "Permissions.dbf");
		for(int i = 3; i < all.size(); ++i) {
			String[] col = all.get(i);
			//定位修改行，获取原先权限后删除需要删除的权限
			if(col[0].equals(userName) && col[1].equals(tableName)) {
				String perms = col[2];
				String[] perm = perms.split(" ");
				
				if(conditions[0].equals("all")) {//删除所有权限
					perms = "null";
				}
				else {//删除部分权限
					for(int j = 0; j < conditions.length; ++j) {
						for(int k = 0; k < perm.length; ++k) {
							if(perm[k].equals(conditions[j])) {
								perm[k] = "null";
								break;
							}
						}
					}
					//按原格式合并已有的所有权限
					for(int j = 0; j < perm.length; ++j) {
						if(j == 0) {
							perms = perm[j];
						}
						else {
							if(!perm[j].equals("null")) {
								perms += " " + perm[j];
							}
						}
					}
				}
				col[2] = perms;
				all.set(i, col);
				dbfmanager.updateRecords(filespath, "Permissions.dbf", all);
				return true;
			}
		}
		return false;
	}

}
