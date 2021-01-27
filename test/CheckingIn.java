package homework.test;

/**
 * 找到出勤最多的人：
 * map查找键值-contaionKey
 * 由值打印键-entrySet
 * 
 * @author Reset
 * @date 2020/5/23 晚
 * */
import java.util.*;

public class CheckingIn{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<String, Integer> m = new HashMap<>();
		
		String data = scan.nextLine();
		String[] names = data.split(" ");
		
		for(int i = 0; i < names.length; ++i) {
			if(m.containsKey(names[i])) {//已存在
				Integer tmp = new Integer((int)m.get(names[i]) + 1);
				m.put(names[i], tmp);
			}
			else {
				m.put(names[i], 1);
			}
		}
		
		/**
		 * 由值返回对应的键：
		 * 
		 * 该方法的思路是，迭代Entry集合，当值和传入的value匹配时，返回对应的key
		 * 
		 * Java 集合框架的Map类提供了 entrySet()方法
		 * 该方法就是把map中的每个键值对变成对应成Set集合中的一个对象
		 * 
		 * 这里的Map.Entry就是一种类型，专指map中的一个键值对组成的对象
		 * */
		int max = 0;
		for(int i = 0; i < names.length; ++i) {
			if(m.containsKey(names[i])) {//已存在
				int tmp = ((Integer)(m.get(names[i]))).intValue();
				m.put(names[i], ++tmp);
				if(tmp > max) {
					max = tmp;
				}
			}
			else {
				m.put(names[i], 1);
			}
		}
		
		 String []cnt_name_people = new String[m.size()];
	     int cnt = 0;
	     for( Map.Entry<String, Integer> entry: m.entrySet()){
	           String key = entry.getKey();
	           int value = entry.getValue();
	           
	           if( max == 0){// 全部出勤1次，特殊情况
	               cnt_name_people[cnt ++ ] = key;
	           }
	           else if( value == max ){
	                cnt_name_people[cnt ++ ] = key;
	           }
	       }
	       for( int i = 0; i < cnt - 1; i ++ ){
	           System.out.print(cnt_name_people[i] + " ");
	       }
	       System.out.println(cnt_name_people[cnt -1 ]);
	        
		scan.close();
	}
}
