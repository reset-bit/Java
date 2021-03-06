package cn.edu.sdust.database;

import java.util.ArrayList;
import java.util.Scanner;

import cn.edu.sdust.database.module.Create;
import cn.edu.sdust.database.module.Delete;
import cn.edu.sdust.database.module.Grant;
import cn.edu.sdust.database.module.Help;
import cn.edu.sdust.database.module.Insert;
import cn.edu.sdust.database.module.Revoke;
import cn.edu.sdust.database.module.Select;
import cn.edu.sdust.database.module.Update;

public class Main {
	private User user = new User();
	private DBFManager dbfmanager = new DBFManager();
	private String filespath = "C:/Users/Administrator/Desktop";
	
	public Main() {
		Scanner scan = new Scanner(System.in);
		if(init()) {
			new Function(scan);
		}
//		scan.close();
	}
	
	public static void main(String[] orgs) {
		new Main();
	}
	
	public boolean init() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to this DataBase!");
		System.out.println("Here's a simple setup.");
		System.out.print("Please specify where you want to put the files generated by this program. ");
		System.out.println("Or you can hit enter and use the default path (your desktop)");
		System.out.println("Please enter a similar format:\t \"C:/Users/Administrator/Desktop\".");
		String temp = scan.nextLine();
		if(!temp.equals("")) {
			filespath = temp;
		}
		System.out.println("Now you can start your operation. Please select the operation number you want to proceed:");
		System.out.println("1.login\t2.register");
		int op = scan.nextInt();
		switch(op) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		}
//		scan.close();
		return true;
	}
	
	public void login() {
		System.out.println("Username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		
		//dbf检查用户信息是否存在
		String checkpermission = isUserExist(username, password);//new String(password)
		while(checkpermission.equals("null")) {
			System.out.println("Login failed! Please check your information and retry.Or you can enter 0 to register.");
			String temp = scan.nextLine();
			if(temp.equals("")) {
				System.out.println("Username:");
				username = scan.nextLine();
				System.out.println("Password:");
				password = scan.nextLine();
				checkpermission = isUserExist(username, password);
			}
			else if(Integer.parseInt(temp) == 0) {
				register();
			}
			else {
				continue;
			}
		}
			
		System.out.println("Login successful!");
		user.setUsername(username);
		user.setPermissions(checkpermission);
//		scan.close();
	}
	
	public void register() {
		System.out.println("Please enter the username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		System.out.println("Please enter the password:");
		String password = scan.nextLine();
		System.out.println("Please enter the password again:");
		String repassword = scan.nextLine();
		while(!password.equals(repassword)) {
			System.out.println("The two password inputs are inconsistent!");
			System.out.println("Please enter the password:");
			password = scan.nextLine();
			System.out.println("Please enter the password again:");
			repassword = scan.nextLine();
		}
		System.out.println("Please enter the level of identity you want to have: r/w/rw");
		System.out.println("ps: r(Read permission) and w(Write permission) and rw(Read and Write permission)");
		String limits = scan.nextLine();
		while(!(limits.equals("r") | limits.equals("w") | limits.equals("rw"))) {
			System.out.println("Please enter the correctly form: r/w/rw");
			System.out.println("ps: r(Read permission) and w(Write permission) and rw(Read and Write permission)");
			limits = scan.nextLine();
		}
		
		String sql = "";
		if(!dbfmanager.isFileExist(filespath, "Users.dbf")) {//文件已存在
			sql = "create table Users (username varchar(20),password varchar(20),limits varchar(20))";
			Create create = new Create(sql);
			create.create(filespath);
		}
		sql = "insert into Users values ('"+username+"\', '"+password+"', '"+limits+"')";
		Insert insert = new Insert(filespath, sql);
		insert.insert(filespath);
//		dbfmanager.show(dbfmanager.getDBF(filespath, insert.getTableName() + ".dbf"));
		
		System.out.println("Registered successfully! Please login in.");
		login();
//		scan.close();
	}
	
	public String isUserExist(String username, String password) {
		int namePos = -1;
		ArrayList<String[]> all = dbfmanager.getDBF(filespath, "Users.dbf");
//		dbfmanager.show(all);//test
//		System.out.println("all.size="+all.size());//test
		String[] allUsers = dbfmanager.getFieldByAttribute(filespath, "Users.dbf", "username");
		for(int i = 0; i < allUsers.length; ++i) {
			if(allUsers[i].equals(username)) {
				namePos = i;
				break;
			}
		}
//		System.out.println("namepos="+namePos);//test
		if(namePos != -1 && all.get(namePos + 1)[1].equals(password)) {//+1==>加头部
			return all.get(namePos + 1)[2];
		}
		return "null";
	}
	
	class Function{
		public Function(Scanner scan) {
			System.out.println("Hi, " + user.getUsername() + "!");
			if(user.getPermissions().equals("r")) {
				System.out.println("You have permission to read files. You can do the following:");
				System.out.println("Select | Help");
			}
			else if(user.getPermissions().equals("w")) {
				System.out.println("You have permission to write files. You can do the following:");
				System.out.println("Create | Delete | Insert | Update | Grant | Revoke | Help");
			}
			else if(user.getPermissions().equals("rw")) {
				System.out.println("You have permissions to read and write files. You can do the following:");
				System.out.println(" Select |Create | Delete | Insert | Update | Grant | Revoke | Help");
			}
			System.out.println("\nNow you can enter the SQL statement to do your operation.");
			System.out.println("Attention:");
			System.out.println("\t1. If you want to get help information, just enter \"help\".");
			System.out.println("\t2. If you want to exit, just enter \"exit\".");
			exe(scan);
		}
		
		public void exe(Scanner scan) {
			System.out.println("------------------------------------------------------------");
			System.out.println("SQL:");
			String sql = scan.nextLine();
			boolean exit = false;
			while(true) {
				while(sql.equals("")) {
					System.out.println("------------------------------------------------------------");
					System.out.println("SQL:");
					sql = scan.nextLine();
				}
				switch(sql.split(" ")[0].toLowerCase()) {
				case "help":
					new Help(filespath, scan);
					break;
				case "create":
					Create create = new Create(sql);
					if(create.create(filespath)) {
						System.out.println("create successfully");
					}
					break;
				case "select":
					Select select = new Select(sql);
					if(dbfmanager.isFileExist(filespath, select.getTableName() + ".dbf")) {//table查询
						dbfmanager.show(select.select(filespath, dbfmanager.getDBF(filespath, select.getTableName() + ".dbf")));
					}
					else {//view查询
						ArrayList<String[]> views = dbfmanager.getDBF(filespath, "Views.dbf");
						for(int i = 0; i < views.size(); ++i) {
							if(views.get(i)[0].equals(select.getTableName())) {
								Select subSelect = new Select(views.get(i)[1]);
								dbfmanager.show(subSelect.select(filespath, dbfmanager.getDBF(filespath, subSelect.getTableName() + ".dbf")));
								break;
							}
						}
					}
					break;
				case "delete":
					Delete delete = new Delete(sql);
					dbfmanager.show(delete.delete(filespath, dbfmanager.getDBF(filespath, delete.getTableName() + ".dbf")));
					System.out.println("delete successfully");
					break;
				case "insert":
					Insert insert = new Insert(filespath, sql);
					insert.insert(filespath);
					dbfmanager.show(dbfmanager.getDBF(filespath, insert.getTableName() + ".dbf"));
					System.out.println("insert successfully");
					break;
				case "update":
					Update update = new Update(sql);
					update.update(filespath, dbfmanager.getDBF(filespath, update.getTableName() + ".dbf"));
					dbfmanager.show(dbfmanager.getDBF(filespath, update.getTableName() + ".dbf"));
					System.out.println("update successfully");
					break;
				case "revoke":
					dbfmanager.show(dbfmanager.getDBF(filespath, "Permissions.dbf"));
					System.out.println();
					Revoke revoke = new Revoke(sql);
					if(revoke.revoke(filespath)) {
						System.out.println("revoke successfully");
					}
					dbfmanager.show(dbfmanager.getDBF(filespath, "Permissions.dbf"));
					break;
				case "grant":
					dbfmanager.show(dbfmanager.getDBF(filespath, "Permissions.dbf"));
					System.out.println();
					Grant grant = new Grant(sql);
					if(grant.grant(filespath)) {
						System.out.println("grant successfully");
					}
					dbfmanager.show(dbfmanager.getDBF(filespath, "Permissions.dbf"));
					break;
				case "exit":
					exit = true;
					break;
				}
				if(exit) {break;}
				System.out.println("------------------------------------------------------------");
				System.out.println("SQL:");
				sql = scan.nextLine();
			}
		}
	}
}
