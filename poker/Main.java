package homework.poker;

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
public class Main{
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}

