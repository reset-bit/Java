package homework.datastructure;

import java.util.Scanner;
public class Stack {
	private final int N = 50;
	private Object[] members;
	private int top = 0;
	
	Stack(){
		members = new Object[N];
	}
	
	/**
	 * @return false 栈不空
	 * @return true 栈空
	 * */
	public boolean isEmpty() {
		if(top == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean push(Object obj) {
		if(top == N) {
			return false;
		}
		else {
			members[top++] = obj;
			return true;
		}
	}
	
	public Object pop() {
		if(!isEmpty()) {
			return members[--top];
		}
		else {
			return -1;
		}
	}
	
	public Object getTop() {
		return members[top - 1];
	}
	
	public void getBinaryNum(int n) {
		while(n > 1) {
			push(n % 2);
			n /= 2;
		}
		push(1);
		while(!isEmpty()) {
			System.out.print(pop());
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input a number which is useful:");
		int n = scan.nextInt();
		Stack stack = new Stack();
		
		System.out.print("The converted binary number is:");
		stack.getBinaryNum(n);
		scan.close();
	}
}
