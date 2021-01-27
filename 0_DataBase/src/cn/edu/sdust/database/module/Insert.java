package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;

public class Insert {
	private String tableName;
	private String[] insertCol = new String[] {};//插入行
	private DBFManager dbfmanager = new DBFManager();
	
	public Insert(String filespath, String sql) {
		String[] temp = sql.split(" ");
		tableName = temp[2];
		int len = dbfmanager.getDBF(filespath, tableName + ".dbf").get(0).length;
		insertCol = new String[len];
		if(sql.contains("(")) {
			int pos = sql.indexOf("(");
			String[] oricol = sql.substring(pos).split(",");
			if(sql.substring(pos).contains("select") && sql.substring(pos).contains("from")) {//若为SQL语句则不分割
				String viewName = sql.substring(pos + 1, sql.indexOf(","));
				String subsql = sql.substring(sql.indexOf(",") + 1).trim();
				oricol = new String[] {viewName, subsql};
			}
			for(int i = 0; i < oricol.length; ++i) {
				insertCol[i] = oricol[i].replace("(", " ").replace("'", " ").replace(")", " ").trim();
			}
		}
//		//show for col test
//		System.out.println("test for col");
//		for(int j = 0; j < insertCol.length; ++j) {
//			System.out.println(insertCol[j]);
//		}
	}

	public String getTableName() {
		return tableName;
	}
	
	public void insert(String filespath) {
		ArrayList<String[]> newcol =  new ArrayList<String[]>();
		newcol.add(insertCol);
		dbfmanager.addRecords(filespath, tableName + ".dbf", newcol);
	}

}
