
package homework.test;

/**
 * 求小于当前数的水仙花数
 * 
 * 设一数组存放1-9n次幂，取得当前数的每位数，访问数组求和与自身比较===>减少取n次幂的时间
 * 一般思路：取得当前数的每位数，取n次幂求和与自身相比较===>易超时
 * */
import java.util.Scanner;
public class SearchDaffodilNumber {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		int nummin = (int)Math.pow(10, number - 1);//Math.pow返回double类型
		int nummax = (int)Math.pow(10, number);
		int[] each = new int[10];
		
		each[1] = 1;
		for(int i = 2; i < each.length; ++i) {
			each[i] = (int)Math.pow(i, number);
		}
		for(int i = nummin; i < nummax; ++i) {
			int currenti = i, sum = 0;
			while(currenti > 0) {//获得每位数及其n次幂
				sum += each[currenti % 10];
				currenti /= 10; 
			}
			if(sum == i) {
				System.out.println(i);
			}
		}
		scan.close();
	}

}
