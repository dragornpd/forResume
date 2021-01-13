package object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.Main;
import card.MoneyCard;
import javafx.scene.paint.Color;

public class Player {
	private String name;
	private ArrayList<Dice> diceList = new ArrayList<>();
	private int totalMoney = 0;
	private int numDice = 8;
	private int myCharacterID;
	private Character myCharacter;
	private String color;
	private ArrayList<MoneyCard> myMoneyCard = new ArrayList<>();
	private Color colorCode;

	public Player(String name, int myCharacterID, String color) {
		this.setName(name);

		this.setMyCharacterID(myCharacterID);
		this.setColor(color);
		switch (this.getColor()) {
		case "Red":
			this.colorCode = Color.RED;
			break;
		case "Blue":
			this.colorCode = Color.BLUE;
			break;
		case "Green":
			this.colorCode = Color.GREEN;
			break;
		case "Yellow":
			this.colorCode = Color.YELLOW;
			break;
		case "Black":
			this.colorCode = Color.BLACK;
			break;
		default:
			break;
		}

		for (Character character : CharacterList.characterList) {
			if (character.getCharacterID() == this.getMyCharacterID()) {
				this.setMyCharacter(character);
			}
		}
		for (int i = 0; i < this.getNumDice(); i++) {
			this.diceList.add(new Dice(this.getColor()));
		}
	}

	//roll all dice of this player
	public ArrayList<Dice> playerRoll() {
		for (int i = 0; i < this.getNumDice(); i++) {
			this.diceList.get(i).roll();
		}

		return this.getDiceList();
	}

	// use for sort diceList
	class Sortbyfacevalue implements Comparator<Dice> {
		@Override
		public int compare(Dice o1, Dice o2) {
			// TODO Auto-generated method stub
			return o1.getFaceValue() - o2.getFaceValue();
		}
	} 

	//sort dice list
	public ArrayList<Dice> sortDiceList() {
		Collections.sort(this.diceList, new Sortbyfacevalue());
		return this.getDiceList();
	}

	// use for pick dice and return the dice that is picked
	public ArrayList<Dice> pickDice(int diceNum) {
		try {
			ArrayList<Dice> pickedDice = new ArrayList<Dice>();
			for (int i = 0; i < this.getNumDice(); i++) {
				if (this.getDiceList().get(i).getFaceValue() == diceNum) {
					pickedDice.add(new Dice(this.getColor(), diceNum));
				}
			}
			this.diceList.removeIf(e -> (e.getFaceValue() == diceNum));
			this.setNumDice(this.getNumDice() - pickedDice.size());
			for (int i = 0; i < 6; i++) {
				if (Main.casinoList.get(i).getStataicNumber() == diceNum) {
					for (Dice d : pickedDice) {
						Main.casinoList.get(i).addCountDice(d);
						Main.casinoList.get(i).playerDiceCountList.computeIfPresent(d.getColor(), (k, v) -> v + 1);
					}
					break;

				}
			}
			return pickedDice;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	//refresh the dice to next round
	public void refreshDiceList() {
		if (this.getDiceList().isEmpty()) {
			this.setNumDice(8);
			for (int i = 0; i < this.getNumDice(); i++) {
				this.diceList.add(new Dice(this.getColor()));
			}
		}
	}

	//add 1 dice to player
	public void addDice() {
		this.diceList.add(new Dice(this.getColor()));
		this.setNumDice(this.getNumDice() + 1);
	}

	//add amount money to player
	public void addMoney(int i) {
		this.setTotalMoney(this.getTotalMoney() + i);
	}

	//subtract amount money of this player
	public void subMoney(int i) {
		this.setTotalMoney(this.getTotalMoney() - i);
	}

	//add money card to player money card list
	public void addMoneyCard(MoneyCard m) {
		this.myMoneyCard.add(m);
		this.setTotalMoney(this.getTotalMoney() + m.getCost());
	}

	//to check that this player have any dice?
	public boolean isEmptyDice() {
		if (this.getNumDice() == 0) {
			return true;
		}
		return false;
	}

	// -----------------------------------Getter&Setter--------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<Dice> getDiceList() {
		return diceList;
	}

	public void setDiceList(ArrayList<Dice> diceList) {
		this.diceList = diceList;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public ArrayList<MoneyCard> getMyMoneyCard() {
		return myMoneyCard;
	}

	public int getNumDice() {
		return numDice;
	}

	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}

	public int getMyCharacterID() {
		return myCharacterID;
	}

	public void setMyCharacterID(int myCharacter) {
		this.myCharacterID = myCharacter;
	}

	public Character getMyCharacter() {
		return myCharacter;
	}

	public void setMyCharacter(Character myCharacter) {
		this.myCharacter = myCharacter;
	}

	public void setMyMoneyCard(ArrayList<MoneyCard> myMoneyCard) {
		this.myMoneyCard = myMoneyCard;
	}

	public Color getColorCode() {
		return colorCode;
	}

	public void setColorCode(Color colorCode) {
		this.colorCode = colorCode;
	}

}
