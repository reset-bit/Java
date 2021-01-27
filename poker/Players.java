package homework.poker;

import java.util.Arrays;
import java.util.Comparator;

public class Players {
	private int P;//P个人
	
	public void getNumOfPeopleFromGame(int p) {
		P = p;
	}
	
	
	/**
	 * 玩家牌排序
	 * */
	public void sortCards(String[] cards) {//
		MyComparator cmp = new MyComparator();
		Arrays.sort(cards, cmp);
		
//		//玩家牌输出测试可在此选择输出
//		System.out.println("----------------");
//		for(String[] i:people) {
//			for(String j:i) {
//				System.out.print(j+",");//System.out.format("%-7s",j);
//			}
//			System.out.println("");
//		}
	}
	
	/**
	 * 打印玩家牌
	 * */
	public void showCards(String[][] people, int id) {
		System.out.print("[");
		int flag = 0;
		for(String s:people[id]) {
			if(flag == 0) {
				System.out.print(s);
				++flag;
			}
			else {
				System.out.print(", "+s);
			}
		}
		System.out.print("]");
	}
	
	/**
	 * 寻找带有目标牌的玩家
	 * 
	 * @return 该玩家id
	 * @param op 标记是否需要跳过皇帝(0 不跳过; 1 跳过)
	 * */
	public int searchAimPlayer(String[][] people, String aim, int op) {
		if(aim == null || aim.length() == 0) {
			return -1;
		}
		for(int i = 0; i < P; ++i) {
			for(int j = 0; j < people[i].length; ++j) {
				if(op == 1 && "皇上".equals(people[i][j])) {
					break;
				}
				if(aim.equals(people[i][j])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 寻找侍卫
	 * */
	public void searchGuard(String[][] people, int king) {
		int flag = 0;
		String guardCard = null;
		for(int i = 0; i < people[king].length - 1; ++i) {
			if(people[king][i].equals(people[king][i+1])) {
				++flag;
				if(flag == 3) {
					System.out.println("\n皇帝可自保");
					System.out.println("自保对应的牌是："+people[king][i]);
					return;
				}
				if(flag == 2) {
					if("大王".equals(people[king][i]) || "小王".equals(people[king][i])) {
						flag = 0;
						continue;
					}
					String subNum = people[king][i].substring(2);
					if("2".equals(subNum) || "3".equals(subNum)) {
						flag = 0;
						continue;
					}
					
					guardCard = people[king][i];
					System.out.println("\n侍卫对应的牌是："+people[king][i]);
					break;
				}
			}
			else {
				flag = 0;
			}
		}
		if(flag == 2) {
			int guard = searchAimPlayer(people, guardCard, 1);
			System.out.println("侍卫是：玩家"+(guard + 1));
			System.out.print("侍卫的牌是：");
			showCards(people, guard);
		}
		else {
			System.out.println("\n无法获得侍卫");
		}
		
	}
}

class MyComparator implements Comparator<String>{
	
	/**
	 * 扑克牌降序排列
	 * 
	 * @return 1 s1排在s2之后(s1<s2)
	 * @return 0 s1 = s2
	 * @return -1 s1排在s2之前(s1>s2)
	 * */
	public int compare(String s1, String s2) {
		//牌尾空串处理
		if(s1 == null || s1.length() <= 0) {
			return 1;
		}
		else if(s2 == null || s2.length() <= 0) {
			return -1;
		}
		String[] pettern = {"方片","梅花","黑桃","红桃"};
		String[] num = {"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
		int len1 = s1.length();
		int len2 = s2.length();
		
		if(len1 != len2) {//s1||s2为大王或小王
			return len1 - len2;
		}
		if(len1 == 2 && len2 == 2) {//s1&&s2为大王或小王
			if(s1.equals(s2)) {
				return 0;
			}
			if("皇上".equals(s1)) {
				return -1;
			}
			else if("皇上".equals(s2)) {
				return 1;
			}
			if("小王".equals(s1)) {
				return 1;
			}
			else if("小王".equals(s2)) {
				return -1;
			}
		}		
		
		//s1&&s2为正常数字+花色
		//数字不同
		String subNumS1 = s1.substring(2);
		String subNumS2 = s2.substring(2);
		for(int i = 0; i < num.length; ++i) {
			if(num[i].equals(subNumS1) && !num[i].equals(subNumS2)) {
				return -1;
			}
			else if(num[i].equals(subNumS2) && !num[i].equals(subNumS1)) {
				return 1;
			}
		}
		//数字相同,花色不同
		String subPetternS1 = s1.substring(0, 2);
		String subPetternS2 = s2.substring(0, 2);
		for(int i = 0; i < pettern.length; ++i) {
			if(pettern[i].equals(subPetternS1) && !pettern[i].equals(subPetternS2)) {
				return -1;
			}
			if(pettern[i].equals(subPetternS2) && !pettern[i].equals(subPetternS1)) {
				return 1;
			}
		}
		return 0;
	}
}
