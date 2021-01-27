package homework.test;

import java.util.Scanner;

public class JudgeLeapYear {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int year = scan.nextInt();
		
		if(year % 4 == 0 && year % 100 != 0) {
			System.out.println("yes");
		}
		else if(year % 400 == 0) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
		
		scan.close();

	}

}
