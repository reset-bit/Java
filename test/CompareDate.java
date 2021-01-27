package homework.test;

/**
 * 构造日期类MyDate类，包含年月日，提供相应的get和set函数，提供void print()函数打印日期
 * 提供int compare(MyDate d)测试当前对象和参数对象d的早晚，如果早则返回-1，晚则返回1，相等则返回0
 * 在main函数中，读入两个日期对象，输出第一个日期对象的信息，输出两个对象的比较结果
 * 
 * @author Reset
 * @date 2020/5/8 晚
 * */
import java.util.Scanner;
public class CompareDate{
	public static void main(String[] args) {
		final int N = 2;//利于更改
		Scanner scan = new Scanner(System.in);
		MyDate[] dates = new MyDate[N];
		
		for(int i = 0; i < N; ++i) {
			dates[i] = new MyDate(scan.nextInt(), scan.nextInt(),  scan.nextInt());
		}
		dates[0].print();
		System.out.print(" ");
		System.out.print(dates[0].compare(dates[1]));
		scan.close();
	}
}
class MyDate{
	int year;
	int month;
	int day;
	
	MyDate(int y, int m, int d){
		year = y;
		month = m;
		day = d;
	}
	
	public int getYear() {return this.year;}
	
	public int getMonth() {return this.month;}
	
	public int getDay() {return this.day;}
	
	public void setYear(int y) {year = y;}
	
	public void setMonth(int m) {month = m;}
	
	public void setDay(int d) {day = d;}
	
	public void print() {
		System.out.print(month+"/"+day+"/"+year);
	}
	
	/**
	 * 比较年份大小：比较得出不同，剩余的一定相同
	 * */
	public int compare(MyDate d){
		if(this.year < d.getYear()) {return -1;}
		else if(this.year > d.getYear()) {return 1;}
		if(this.month < d.getMonth()) {return -1;}
		else if(this.month > d.getMonth()) {return 1;}
		if(this.day < d.getDay()) {return -1;}
		else if(this.day < d.getDay()) {return 1;}
		return 0;
	}
}