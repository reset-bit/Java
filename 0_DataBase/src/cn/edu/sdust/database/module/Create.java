package cn.edu.sdust.database.module;

import java.util.ArrayList;

import cn.edu.sdust.database.DBFManager;
import cn.edu.sdust.database.Hash;

public class Create {
	private String type;		//标记create语句创建类型：table/index/view
	private String objName;		//操作对象名称
	private DBFManager dbfmanager = new DBFManager();
	private ArrayList<String[]> head = new ArrayList<String[]>();
	private String subsql;		//建立视图条件
	private String aimfield;	//建立索引条件
	
	//获取objName/type, 添加用户对新建表权限
	public Create(String sql) {
		sql = sql.trim();
		int spaceNum = 0;
		String temp = "";
		//顺字符遍历，依空格划分，同时获得type与objName
		for (int i = 0; i < sql.length(); i++) {
			if(sql.charAt(i) == ' ') {//create第一个空格，其后为type
				spaceNum++;
			}
			if(spaceNum == 1 | spaceNum == 2) {
				temp += sql.charAt(i);
			}
			else if(spaceNum == 3 | sql.charAt(i) == '(') {
				String[] obj = temp.trim().split(" ");
//				System.out.println(obj);//test
				type = obj[0].toLowerCase();
				objName = obj[1];
				break;
			}
		}
		//获取create view中sql子句
		if(sql.contains("as")) {
			subsql = sql.substring(sql.indexOf("as") + 3);
			subsql = subsql.trim();
		}
		else {
			subsql = "";
		}
		//获取create index中目标列
		if(sql.contains("on")) {
			aimfield = sql.substring(sql.indexOf("on"));
			aimfield = aimfield.replace('(', ' ').replace(')', ' ').trim();
			aimfield = aimfield.split(" ")[1];
		}
		else {
			aimfield = "";
		}
		getHead(sql);
	}
	
	public String getTableType() {return type;}
	
	public String getObjName() {return objName;}
	
	public ArrayList<String[]> getHead(String sql) {
		ArrayList<String[]> head = new ArrayList<String[]>();
		String all = "";
		int start = 0, startFlag = 0, endFlag = 0;//标记括号起始点
		//取属性字段及其类型
		for (int i = 0; i < sql.length(); i++) {
			if (sql.charAt(i) == '(') {
				startFlag++;
				if (startFlag == endFlag + 1) {//第一个前括号的数据位置
					start = i;
				}
			}
			else if (sql.charAt(i) == ')') {
				endFlag++;
				if (endFlag == startFlag) {//最后一个后括号的数据位置
					all = sql.substring(start + 1, i);
				}
			}
		}
		//获得每一个属性及其类型
		String[] eachField = all.split(",");
		for(int j = 0; j < eachField.length; ++j) {
			String[] d = eachField[j].split(" ");
			head.add(d);
		}
		this.head = head;
		return head;
	}
	
	//为每个存在的用户，依据其注册权限，添加对新建表的操作权限
	public void updatePermissions(String filespath) {
		if(objName.equals("Users") | objName.equals("Permissions") | objName.equals("Tables")) {
			return;
		}
		String[] allUsers = dbfmanager.getFieldByAttribute(filespath, "Users.dbf", "username");
		String[] allLimits = dbfmanager.getFieldByAttribute(filespath, "Users.dbf", "limits");
		for(int i = 2; i < allUsers.length; ++i) {
			String username = allUsers[i];
			String limit = allLimits[i];
			String perms = "";
			if(limit.equals("r")) {
				perms = "select";
			}
			else if(limit.equals("w")) {
				perms = "create insert update delete";				
			}
			else {
				perms = "select create insert update delete";				
			}
			String sql = "";
			if(!dbfmanager.isFileExist(filespath, "Permissions.dbf")) {//文件已存在
				sql = "create table Permissions (username varchar(20),objName varchar(20),limits varchar(100))";
				Create create = new Create(sql);
				create.create(filespath);
			}
			sql = "insert into Permissions values ('"+username+"\', '"+objName+"', '"+perms+"')";
			Insert insert = new Insert(filespath, sql);
			insert.insert(filespath);
			dbfmanager.show(dbfmanager.getDBF(filespath, insert.getTableName() + ".dbf"));
		}
		System.out.println("updatePermissions successfully!");		
	}
	
	public void updateTables(String filespath) {
		if(objName.equals("Users") | objName.equals("Permissions") | objName.equals("Tables") ) {
			return;
		}
		
		String sql = "";
		if(!dbfmanager.isFileExist(filespath, "Tables.dbf")) {//文件已存在
			sql = "create table Tables (tablename varchar(20))";
			Create create = new Create(sql);
			create.create(filespath);
		}
		sql = "insert into Tables values ('"+objName+"')";
		Insert insert = new Insert(filespath, sql);
		insert.insert(filespath);
		dbfmanager.show(dbfmanager.getDBF(filespath, "Tables.dbf"));
		System.out.println("updateTables successfully!");			
	}
	
	public boolean create(String filespath) {
		switch(type) {
		case "table":
			//初始化主键索引
			ArrayList<String[]> data = new ArrayList<String[]>();
			String[] haveIndex = new String[head.size()];
			for(int i = 0; i < head.size(); ++i) {
				if(i == 0) {//默认首元素为主键
					haveIndex[i] = "true";
				}
				else {
					haveIndex[i] = "false";
				}
			}
			data.add(haveIndex);
			dbfmanager.addRecords(filespath, objName + ".dbf", head, data);
//			dbfmanager.show(dbfmanager.getDBF(filespath, "Users.dbf"));//test
			break;
		case "view":
			String viewSql = "";
			if(!dbfmanager.isFileExist(filespath, "Views.dbf")) {//文件已存在
				viewSql = "create table Views (viewName varchar(20),sql varchar(100))";
				Create create = new Create(viewSql);
				create.create(filespath);
			}
			viewSql = "insert into Views values ('"+objName+"','"+subsql+"')";
//			System.out.println("subsql="+subsql);//test
			Insert insert = new Insert(filespath, viewSql);
			insert.insert(filespath);
			dbfmanager.show(dbfmanager.getDBF(filespath, "Views.dbf"));//test
			
			if(!subsql.equals("")) {
				Select select = new Select(subsql);
				dbfmanager.show(select.select(filespath, dbfmanager.getDBF(filespath, select.getTableName() + ".dbf")));
			}
			break;
		case "index":
			Hash hash = new Hash(50);
			String[] allAttributes = dbfmanager.getFieldByAttribute(filespath, objName, aimfield);
			for(int i = 0; i < allAttributes.length; ++i) {
				hash.put(allAttributes[i], i + "");
			}
			ArrayList<String[]> all = dbfmanager.getDBF(filespath, objName + ".dbf");
			for(int i = 0; i < all.get(1).length; ++i) {
				if(all.get(1)[i].equals(aimfield)) {
					all.get(1)[i] = "true";
				}
			}
			dbfmanager.updateRecords(filespath, objName + ".dbf", all);
			break;
		}
		updatePermissions(filespath);
		updateTables(filespath);
		return true;
	}
}
