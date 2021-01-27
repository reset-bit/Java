package homework.test;

/**
 * 加点提示信息
 * search放入show()函数中更易维护
 * */
import java.util.Scanner;
public class BinarySearch {
	/**
	 * selectionsort
	 * */
	public static void sort(int[] num) {
		for(int i = 0; i < num.length; ++i) {
			int min = i;
			for(int j = i; j < num.length; ++j) {
				if(num[j] < num[min]) {
					min = j;
				}
			}
			int temp = num[i];
			num[i] = num[min];
			num[min] = temp;
		}
	}
	
	public static int reBinarySearch(int[] num, int low, int high, int key) {
		if(low > high || key < num[low] || key > num[high]) {
			return -1;
		}
		int middle = (high + low) / 2;
		if(key < num[middle]) {
			reBinarySearch(num, low, middle, key);
		}
		else if(key > num[middle]) {
			reBinarySearch(num, middle, high, key);
		}
		return middle;
	}
	
	public static int binarySearch(int[] num, int key) {
		int low = 0;
		int high = num.length - 1;
		int middle;
		
		if(key < num[low] || key > num[high]) {
			return -1;
		}
		while(low <= high) {
			middle = (low + high) / 2;
			if(key < num[middle]) {
				high = middle - 1;
			}
			else if(key > num[middle]) {
				low = middle + 1;
			}
			else {
				return middle;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int length = scan.nextInt();
		int[] num = new int[length];
		int key;
		
		for(int i = 0; i < length; ++i) {
			num[i] = scan.nextInt();
		}
		sort(num);
		for(int i = 0; i < num.length; ++i) {
			System.out.print(num[i]);
		}
		System.out.println(" ");
		
		key = scan.nextInt();
		int tmp = reBinarySearch(num, 0, num.length - 1, key);
		if(tmp < 0) {
			System.out.println("未找到此元素");
		}
		else {
			System.out.println("该元素位置为第"+tmp+"位");
		}
		
		key = scan.nextInt();
		tmp = binarySearch(num, key);
		if(tmp < 0) {
			System.out.println("未找到此元素");
		}
		else {
			System.out.println("该元素位置为第"+tmp+"位");
		}
		scan.close();
	}
}
