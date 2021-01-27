package homework.poker;

import java.util.ArrayList;
import java.util.List;

public class Game {
	public final int N = 4;//4副牌
	public final int P = 5;//5个人
	private List<String> cardsHasKing;
	private List<String> cards;
	private String[][] people;
	
	/**
	 * 初始化玩家与牌
	 * 
	 * 4副扑克牌，标记皇上的一个List，其余3副一个List； 5个用户，1个String[][]
	 * */
	public Game() {
		cardsHasKing = new ArrayList<String>();
		cards = new ArrayList<String>();
		Dealer dealer = new Dealer();
		dealer.initialCards(cardsHasKing, 1);
		dealer.initialCards(cards, 0);
		
		people = new String[5][];
		int maxCardsOfEach = (int)Math.ceil((54 * (double)N / P));//54*4/5=43.2,每人最多44张牌,向上取整
		for(int i = 0; i < P; ++i) {
			people[i] = new String[maxCardsOfEach];
		}
	}
	
	/**
	 * 控制游戏进行
	 * */
	public void run() {
		Dealer dealer = new Dealer();
		Players player = new Players();
		dealer.getNumOfCardsFromGame(N);
		dealer.getNumOfPeopleFromGame(P);
		player.getNumOfPeopleFromGame(P);
		
		dealer.handOutCards(people, cardsHasKing, cards);
		for(int i = 0; i < P; ++i) {
			player.sortCards(people[i]);
		}
		
		for(int i = 0; i < P; ++i) {
			System.out.print("玩家"+(i+1)+"的牌是:");
			player.showCards(people, i);
			System.out.println("");
		}
		
		int king = player.searchAimPlayer(people, "皇上", 0);
		System.out.println("皇帝是：玩家"+(king + 1));
		System.out.print("皇帝的牌是：");
		player.showCards(people, king);
		
		player.searchGuard(people, king);
	}
}
