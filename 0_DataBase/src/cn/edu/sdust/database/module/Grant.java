package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;

public class Grant {
	private String userName;
	private String tableName;						//需要操作的表
	private String[] conditions = new String[] {};	//需要增加的权限
	private DBFManager dbfmanager = new DBFManager();
	
	public Grant(String sql) {
		String[] temp = sql.split(" ");
		conditions = temp[1].split(",");
		tableName = temp[3];
		userName = temp[5];
	}
	
	/**
	 * 遍历全部数据定位修改行，获取原先权限后，增加需要增加的权限
	 * */
	public boolean grant(String filespath) {
		ArrayList<String[]> all = dbfmanager.getDBF(filespath, "Permissions.dbf");
		for(int i = 3; i < all.size(); ++i) {
			String[] col = all.get(i);
			//定位修改行
			if(col[0].equals(userName) && col[1].equals(tableName)) {
				String perms = col[2];
				String[] perm = perms.split(" ");
				
				if(conditions[0].equals("all")) {//增加到所有权限
					perms = "select create insert update delete";
				}
				else {//增加部分权限
					boolean owned = false;
					for(int j = 0; j < conditions.length; ++j) {
						for(int k = 0; k < perm.length; ++k) {
							if(perm[k].equals(conditions[j])) {
								owned = true;
								break;
							}
						}
						if(!owned) {
							perms += " " + conditions[j];
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
