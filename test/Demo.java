package homework.test;

import java.util.Scanner;//导入Scanner类

public class Demo {

	public static void main(String[] args) {//无限string数组类型参数
		
		//生成scanner对象，该scanner对象可以处理标准输入
		Scanner scan = new Scanner(System.in);
		
		//调用scan对象的nextInt方法
		int a = scan.nextInt(), b = scan.nextInt();
		
		System.out.println(a+b);//print输出不换行，println输出换行
		
		scan.close();//关闭扫描器

	}

}
