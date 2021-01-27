package cn.edu.sdust.database.module;

import java.util.ArrayList;
import java.util.Scanner;

import cn.edu.sdust.database.DBFManager;

public class Help {
	private DBFManager dbfmanager = new DBFManager();
	
	public Help(String filespath, Scanner scan) {
		System.out.println("------------------------------------------------------------");
		System.out.println("This is the help information document begin.");
		System.out.println("You can enter these following options:(Or enter \"end\" to exit.)");
		System.out.println("help database | help table tableName | help view | help index | help insert | help delete | help update");
		String op = scan.nextLine();
		while(!op.equals("end")) {
			switch(op.split(" ")[1]) {
			case "database":
				ArrayList<String[]> tables = dbfmanager.getDBF(filespath, "tables.dbf");
				dbfmanager.show(tables);
				break;
			case "table":
				ArrayList<String[]> all = dbfmanager.getDBF(filespath, op.split(" ")[2] + ".dbf");
				ArrayList<String[]> aim = new ArrayList<String[]>();
				aim.add(all.get(0));
				aim.add(all.get(1));
				for(int i = 0; i < aim.size(); ++i) {
					for (int j = 0; j < aim.get(0).length; j++) {
						System.out.format("%-20s", aim.get(i)[j]);
					}
					System.out.println();
					if(i == 0) {
						for (int j = 0; j < aim.get(0).length; j++) {
							System.out.print("--------------------");
						}
						System.out.println();
					}
				}
				break;
			case "view":
				System.out.println("SQL as:");
				System.out.println("create view viewname as select LastName on Persons");
				break;
			case "index":
				System.out.println("SQL as:");
				System.out.println("create index indexname on Persons(LastName)");
				break;
			case "insert":
				System.out.println("SQL as:");
				System.out.println("insert into Persons values ('Gates', 'Bill', 'Xuanwumen 10', 'Beijing')");
				break;
			case "delete":
				System.out.println("SQL as:");
				System.out.println("delete from Persons where LastName='Wilson'");
				break;
			case "update":
				System.out.println("SQL as:");
				System.out.println("update Persons set FirstName='Fred' where LastName='Wilson'");
				break;
			}
			op = scan.nextLine();
		}
		System.out.println("This is the help information document end.");
		System.out.println("------------------------------------------------------------");
	}
}
