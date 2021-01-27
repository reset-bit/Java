package homework.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {
	private int N;//N副牌
	private int P;//P个人
	
	public void getNumOfPeopleFromGame(int p) {
		P = p;
	}
	
	public void getNumOfCardsFromGame(int n) {
		N = n;
	}
	
	/**
	 * 初始化每副扑克牌
	 * 
	 * @param op 标记该牌是否含King(1含,0不含)
	 * */
	public void initialCards(List<String> cards, int op) {
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
	public void handOutCards(String[][] people, List<String> cardsHasKing, List<String> cards) {
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
}
