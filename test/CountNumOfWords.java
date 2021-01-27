package homework.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountNumOfWords {
	public static void main(String[] args) {
		Map<String, Integer> words = new HashMap<>();
		getWords(words);
		System.out.println(words.size());
		
		/**
		 * 排序
		 * 
		 * 次数不同，按值降序；次数相同，按键升序
		 * */
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if(o1.getValue() - o2.getValue() != 0) {//次数不同，不可用==
					return o2.getValue().compareTo(o1.getValue());
				}
				else {//次数相同
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
		
		/**
		 * 输出
		 * */
		int count = 0;
		for(Map.Entry<String, Integer> map:list) {
			System.out.println(map.getKey()+"="+map.getValue());
			++count;
			if(count == 10) {
				break;
			}
		}
	}	
	
	/**
	 * 按要求读入数据
	 * 
	 * 通过SringBuilder处理初始String中标点符号，遇标点即跳过
	 * 将初步处理后的数据全部转换成小写
	 * 加入map，已存在键的值在原基础上+1 
	 * 
	 * s0初始word；s1处理标点；s处理后word
	 * */
	public static void getWords(Map<String, Integer> words) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String s0 = scan.next();
			StringBuilder s1 = new StringBuilder();
			for(int i = 0; i < s0.length(); ++i) {//去!.,:*?
				if('!' == s0.charAt(i) || '.' == s0.charAt(i) || ',' == s0.charAt(i) || ':' == s0.charAt(i) || '*' == s0.charAt(i) || '?' == s0.charAt(i)) {
					continue;
				}
				else {
					s1.append(s0.charAt(i));
				}
			}
			String s = s1.toString().toLowerCase();
			
			if("".equals(s)) {//已处理完毕后的"!!!!!"即""
				break;
			}
			else {
				if(words.containsKey(s)) {
					words.put(s, new Integer((int)words.get(s) + 1));
				}
				else {
					words.put(s, new Integer(1));
				}
			}
		}
		scan.close();
	}
}
