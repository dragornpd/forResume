package object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import application.Main;
import card.MoneyCard;

public class Casino {
	private String name;
	private int stataicNumber; //id of casino
	private boolean over50000 = false; //to check that this casino have total money more than 50000
	public HashMap<String, ArrayList<Dice>> countDice = new HashMap<String, ArrayList<Dice>>();
	public HashMap<String, Integer> playerDiceCountList = new HashMap<String, Integer>();
	private ArrayList<MoneyCard> moneyCardList = new ArrayList<MoneyCard>();
	private int totalMoney = 0;

	// construct the casino and add color to be a key in hashmap
	public Casino(String name, int staticNumber) {
		this.setName(name);
		this.setStataicNumber(staticNumber);
		this.countDice.put("Red", new ArrayList<Dice>());
		this.countDice.put("Blue", new ArrayList<Dice>());
		this.countDice.put("Green", new ArrayList<Dice>());
		this.countDice.put("Black", new ArrayList<Dice>());
		this.countDice.put("Yellow", new ArrayList<Dice>());
		this.playerDiceCountList.put("Red", 0);
		this.playerDiceCountList.put("Blue", 0);
		this.playerDiceCountList.put("Green", 0);
		this.playerDiceCountList.put("Black", 0);
		this.playerDiceCountList.put("Yellow", 0);
	}

	// use to add dice to the casino
	public void addDice(Dice d) {
		this.countDice.get(d.getColor()).add(d);
		this.playerDiceCountList.computeIfPresent(d.getColor(), (k, v) -> v + 1);
	}

	//clear the casino to default
	public void refreshCasino() {
		this.moneyCardList.clear();
		for (Entry<String, ArrayList<Dice>> i : this.countDice.entrySet()) {
			i.getValue().clear();
		}
		this.playerDiceCountList.put("Red", 0);
		this.playerDiceCountList.put("Blue", 0);
		this.playerDiceCountList.put("Green", 0);
		this.playerDiceCountList.put("Black", 0);
		this.playerDiceCountList.put("Yellow", 0);
		this.over50000 = false;
		this.setTotalMoney(0);
	}

	//clear the money list in this casino
	public void refreshMoney() {
		this.moneyCardList.clear();
		this.over50000 = false;
		this.setTotalMoney(0);
	}

	// use for sort moneyCardList
	class Sortbycost implements Comparator<MoneyCard> {
		@Override
		public int compare(MoneyCard o1, MoneyCard o2) {
			// TODO Auto-generated method stub
			return o2.getCost() - o1.getCost();
		}
	} 

	//use when deal card in each round
	public void addMoneyCard(MoneyCard m) {
		if (!over50000) {
			this.moneyCardList.add(m);
			this.setTotalMoney(this.totalMoney + m.getCost());
		}
		if (this.totalMoney >= 50000) {
			this.over50000 = true;
		}
		Collections.sort(this.moneyCardList, new Sortbycost());
	}

	//use for deal card when activate skill
	public void addMoneyCardBySkill(MoneyCard m) {
		this.moneyCardList.add(m);
		this.setTotalMoney(this.totalMoney + m.getCost());
		Collections.sort(this.moneyCardList, new Sortbycost());
	}

	//find player in playerList that have same color as parameter
	public Player findPlayer(String color) {
		for (Player p : Main.playerList) {
			if (p.getColor().equals(color)) {
				return p;
			}
		}
		return null;
	}

	//use for sort
	public HashMap<String, Integer> sortedPlayerDiceCountList() {
		return this.playerDiceCountList = sortByComparator(this.playerDiceCountList, false);
	}

	//find the winner in this casino
	public ArrayList<Player> showWinner() {
		this.sortedPlayerDiceCountList();
		HashMap<String, Integer> copyOfPlayerDiceCountList = new HashMap<String, Integer>();
		HashMap<String, Integer> winnerCount = new HashMap<String, Integer>();
		Entry<String, Integer> previous = null; //store the previous one
		boolean isFirst = true; // to check that this is first time?
		boolean isSameStreak = false; //to check that have the samestreak?
		int k = 0;
		for (Entry<String, Integer> i : this.playerDiceCountList.entrySet()) {
			if (k == 4) {
				if (previous.getValue().equals(i.getValue())) {
					break;
				} else {
					if (!isSameStreak) {
						copyOfPlayerDiceCountList.put(previous.getKey(), previous.getValue());
					}
					copyOfPlayerDiceCountList.put(i.getKey(), i.getValue());
				}
				break;
			}
			if (isFirst) {
				previous = i;
				isFirst = false;
			} else {
				if (previous.getValue().equals(i.getValue())) {
					isSameStreak = true;
				} else {
					if (!isSameStreak) {
						copyOfPlayerDiceCountList.put(previous.getKey(), previous.getValue());
						isSameStreak = false;
					} else {
						isSameStreak = false;
					}
				}
				previous = i;
			}
			k++;
		}

		for (Entry<String, Integer> j : copyOfPlayerDiceCountList.entrySet()) {
			if (j.getValue() != 0) {
				winnerCount.put(j.getKey(), j.getValue());
			}
		}
		winnerCount = sortByComparator(winnerCount, false);

		ArrayList<Player> winnerList = new ArrayList<Player>();

		for (Entry<String, Integer> i : winnerCount.entrySet()) {
			winnerList.add(this.findPlayer(i.getKey()));
		}

		return winnerList;
	}

	//seperate money in this casino to player
	public void addMoneyToPlayer() {
		int sizeOfMoney = this.getMoneyCardList().size();
		ArrayList<Player> theWinner = this.showWinner();
		int sizeOfWinner = theWinner.size();
		if (sizeOfWinner == 1) {
			for (int k = 0; k < sizeOfMoney; k++) {
				theWinner.get(0).addMoneyCard(this.getMoneyCardList().get(k));
			}
		} else if (sizeOfMoney > sizeOfWinner) {
			for (int j = 0; j < sizeOfWinner; j++) {
				theWinner.get(j).addMoneyCard(this.getMoneyCardList().get(j));
			}
		} else {
			for (int j = 0; j < sizeOfMoney; j++) {
				theWinner.get(j).addMoneyCard(this.getMoneyCardList().get(j));
			}
		}

	}

	private static HashMap<String, Integer> sortByComparator(HashMap<String, Integer> unsortMap, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});
		// Maintaining insertion order with the help of LinkedList
		HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	// -----------------------------------Getter&Setter--------------------------------------//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStataicNumber() {
		return stataicNumber;
	}

	public void setStataicNumber(int stataicNumber) {
		this.stataicNumber = stataicNumber;
	}

	public boolean isOver50000() {
		return over50000;
	}

	public void setOver50000(boolean over50000) {
		this.over50000 = over50000;
	}

	public HashMap<String, ArrayList<Dice>> getCountDice() {
		return countDice;
	}

	public void addCountDice(Dice d) {
		this.countDice.get(d.getColor()).add(d);
	}

	public void removeCountDice(Dice d) {
		this.countDice.get(d.getColor()).remove(d);
	}

	public HashMap<String, Integer> getPlayerDiceCountList() {
		return playerDiceCountList;
	}

	public void setPlayerDiceCountList(HashMap<String, Integer> playerDiceCountList) {
		this.playerDiceCountList = playerDiceCountList;
	}

	public ArrayList<MoneyCard> getMoneyCardList() {
		return moneyCardList;
	}

	public void setMoneyCardList(ArrayList<MoneyCard> moneyCardList) {
		this.moneyCardList = moneyCardList;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setCountDice(HashMap<String, ArrayList<Dice>> countDice) {
		this.countDice = countDice;
	}

}
