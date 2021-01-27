package homework.test;

/**
 * 模拟保皇发牌
 * (扑克排序后10在3之后，目前尚无解决办法，欢迎交流想法)
 * 
 * 4副扑克牌，标记皇上的一个List，其余3副一个List； 5个用户，1个String[][]
 * Collections.shuffle()洗牌，5个用户分别依次取牌，取好后排序，按：方片-梅花-黑桃-红桃，并优先把相同花色的牌放在一起
 * 皇上挑选侍卫：要求匹配点数和花色都相同的牌，选牌不可为2/3/大小王。分：自己做侍卫/选一人做侍卫/无法获得侍卫
 * 输出皇上/皇上牌/侍卫对应牌/侍卫/侍卫牌
 * 
 * 改进：让皇上；用户改list；花色和点数改枚举
 * treemap?linkedhashmap自动排序?
 * */
import java.util.*;
import java.lang.String;
public class Poker{
	public static final int N = 4;//4副牌
	public static final int P = 5;//5个人
	
	public static void main(String[] args) {
		List<String> cardsHasKing = new ArrayList<String>();
		List<String> cards = new ArrayList<String>();
		initialCards(cardsHasKing, 1);
		initialCards(cards, 0);
		String[][] people = new String[5][];
		int maxCardsOfEach = (int)Math.ceil((54 * (double)N / P));//54*4/5=43.2,每人最多44张牌,向上取整
		for(int i = 0; i < P; ++i) {
			people[i] = new String[maxCardsOfEach];
		}
		
		handOutCards(people, cardsHasKing, cards);
		sortCards(people);
		
		int king = searchAimPlayer(people, "皇上", 0);
		System.out.println("皇帝是：玩家"+(king + 1));
		System.out.print("皇帝的牌是：");
		showPlayerCards(people, king);
		
		searchGuard(people, king);
	}
	
	/**
	 * 初始化每副扑克牌
	 * 
	 * @param op 标记该牌是否含King(1含,0不含)
	 * */
	public static void initialCards(List<String> cards, int op) {
		if(op == 1) {
			cards.add("皇上");
		}
		else {
			cards.add("大王");
		}
		cards.add("小王");
		String[] pattern = {"方片","梅花","黑桃","红桃"};
		String[] num = {"A","K","Q","J"};
		for(int i = 0; i < pattern.length; ++i) {
			for(int j = 0; j < num.length; ++j) {
				cards.add(pattern[i] + num[j]);
			}
			for(int j = 10; j >= 2; --j) {
				cards.add(pattern[i] + j);
			}
		}
	}
	
	/**
	 * 发牌给5位玩家
	 * */
	public static void handOutCards(String[][] people, List<String> cardsHasKing, List<String> cards) {
		List<String> cardsAll = new ArrayList<String>();//拼接
		for(int i = 0; i < N; ++i) {
			if(i == 0) {
				Collections.shuffle(cardsHasKing);
				cardsAll.addAll(cardsHasKing);
			}
			else {
				Collections.shuffle(cards);
				cardsAll.addAll(cards);
			}
		}		
		int tmpPeople = 0;
		int tmpCards = 0;
		for(String tmp:cardsAll) {//一人一张循环发牌
			people[tmpPeople][tmpCards] = tmp;
			++tmpPeople;
			if(tmpPeople >= P) {
				tmpPeople = 0;
				++tmpCards;
			}
		}
	}
	
	/**
	 * 玩家牌排序
	 * */
	public static void sortCards(String[][] people) {
		MyComparator cmp = new MyComparator();
		for(int i = 0; i < P; ++i) {
			Arrays.sort(people[i], cmp);
		}
//		玩家牌输出测试可在此选择输出
//		System.out.println("----------------");
//		for(String[] i:people) {
//			for(String j:i) {
//				System.out.format("%-7s",j);
//			}
//			System.out.println("");
//		}
	}
	
	/**
	 * 寻找带有目标牌的玩家
	 * 
	 * @return 该玩家id
	 * @param op 标记是否需要跳过皇帝(0 不跳过; 1 跳过)
	 * */
	public static int searchAimPlayer(String[][] people, String aim, int op) {
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
	public static void searchGuard(String[][] people, int king) {
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
			showPlayerCards(people, guard);
		}
		else {
			System.out.println("\n无法获得侍卫");
		}
		
	}
	
	/**
	 * 打印玩家牌
	 * */
	public static void showPlayerCards(String[][] people, int id) {
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