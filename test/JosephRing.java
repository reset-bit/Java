package homework.test;

/**
 * 约瑟夫环：
 * 已知n个人（以编号a，b，c...分别表示）围坐在一张圆桌周围。从编号为1的人开始报数，数到m的那个人出列
 * 他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列
 * 
 * 外循环保证桌上总有人，内循环保证当前圈未结束并进行移除操作
 * */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JosephRing {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int aimNum = scan.nextInt();
		scan.nextLine();
		String data = scan.nextLine();
		String[] people = data.split(",");
		
		List<String> arr = new ArrayList<String>(); 
		for(int i = 0; i < people.length; ++i) {
			arr.add(people[i]);
		}
		
		int i = 1;//若放while内，则每次循环从头开始
		while(arr.size() > 0) {
			Iterator<String> iter = arr.iterator();
			
			while(iter.hasNext()) {
				String tmp = (String)iter.next();
				if(i == aimNum) {
					if(arr.size() > 1) {
						System.out.print(tmp+",");
					}
					else {
						System.out.print(tmp);
					}
					iter.remove();
					i = 1;
				}
				else {
					++i;
				}
			}
		}
		
		scan.close();
	}	
}
