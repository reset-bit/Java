package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;

public class Update {
	private String tableName;
	private String[] conditions = new String[2];//更新条件
	private String[] newConditions = new String[] {};//更新值
	private DBFManager dbfmanager = new DBFManager();
	
	public Update(String sql) {
		String[] temp = sql.split(" ");
		tableName = temp[1];
		newConditions = temp[3].split("=");
		conditions = temp[5].split("=");
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * 依据更新条件定位更新行，依据更新值定位列，在数组中修改。最后更新数据。
	 * */
	public boolean update(String filespath, ArrayList<String[]> all) {
		int updateField = -1;
		ArrayList<Integer> updateCols = new ArrayList<Integer>();
		updateCols.add(-1);
		for(int j = 0; j < all.get(0).length; ++j) {
			if(all.get(0)[j].equals(conditions[0])) {//定位修改行
				for(int i = 3; i < all.size(); ++i) {
					if(all.get(i)[j].equals(conditions[1].replace("'", " ").replace("'", " ").trim())) {
						updateCols.add(i);
					}
				}
			}
			if(all.get(0)[j].equals(newConditions[0])) {//定位修改列
				updateField = j;
			}
		}
		if(updateCols.size() != 1 && updateField != -1) {
			for(int i = 1; i < updateCols.size(); ++i) {
				String[] aimField = all.get(updateCols.get(i));
				aimField[updateField] = newConditions[1].replace("'", " ").replace("'", " ").trim();
				all.set(updateCols.get(i), aimField);
			}
//			dbfmanager.show(all);//test
			dbfmanager.updateRecords(filespath, tableName + ".dbf", all);
//			dbfmanager.show(dbfmanager.getDBF(filespath, getTableName() + ".dbf"));//test
			return true;
		}
//		dbfmanager.show(dbfmanager.getDBF(filespath, getTableName() + ".dbf"));//test
		return false;
	}

}
