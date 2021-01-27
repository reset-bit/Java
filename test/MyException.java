package homework.test;

/**
 * 自己编码以产生常见异常。

	main方法：事先定义好一个大小为5的数组。根据屏幕输入产生相应异常
	提示：可以使用System.out.println(e)打印异常对象的信息，其中e为捕获到的异常对象。
	
	输入说明:
	arr 代表产生访问数组是产生的异常。然后输入下标，如果抛出ArrayIndexOutOfBoundsException异常则显示，如果不抛出异常则不显示。
	null，产生NullPointerException
	cast，尝试将String对象强制转化为Integer对象，产生ClassCastException。
	num，然后输入字符，转化为Integer，如果抛出NumberFormatException异常则显示。
	其他，结束程序。
 * 
 * 输入样例：
	arr 4
	null
	cast
	num 8
	arr 7
	num a
	other
	输出样例：
	java.lang.NullPointerException
	java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
	java.lang.ArrayIndexOutOfBoundsException: 7
	java.lang.NumberFormatException: For input string: "a"
 * */
import java.util.Scanner;

public class MyException {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int arr[] = new int[5];
		String str = scan.nextLine();
		
		while(!"other".equals(str)) {
			String ops[] = str.split(" ");
			if("arr".equals(ops[0])) {//数组越界
				try {
					int tmp = arr[Integer.parseInt(ops[1])];
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			else if("null".equals(ops[0])) {
				try {
					String s = null;
					int tmp = s.length();
				}catch(Exception e){
					System.out.println(e);
				}
				
			}
			else if("cast".equals(ops[0])) {
				try {
					Object o = new String("string");
					Integer tmp = (Integer)o;
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			else if("num".equals(ops[0])) {//字符强制转换数字
				try {
					Integer tmp = Integer.parseInt(ops[1]);
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			str = scan.nextLine();
		}
		
		scan.close();
	}
}