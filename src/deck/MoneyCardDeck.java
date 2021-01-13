package deck;

import java.util.ArrayList;
import java.util.Collections;

import base.FontUse;
import card.MoneyCard;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MoneyCardDeck implements Deck {
	private ArrayList<MoneyCard> moneyCardList;
	private int totalCard;
	private ImageView moneyDeck = new ImageView(ClassLoader.getSystemResource("img/" + "MoneyDeck.png").toString());
	private Tooltip tooltip = new Tooltip();

	public MoneyCardDeck(ArrayList<MoneyCard> cardList) {
		this.setMoneyCardList(cardList);
		totalCard = cardList.size();
		// tooltip show size of this deck
		tooltip.setFont(FontUse.font20);
		tooltip.setText(Integer.toString(this.getTotalCard()) + " cards left");
		this.moneyDeck.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this.getMoneyDeck(), e.getScreenX(), e.getScreenY() + 10);
		});
		this.getMoneyDeck().setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	// shuffle the deck
	public void shuffle() {
		Collections.shuffle(this.moneyCardList);
	}

	// draw top card and update tooltip
	public MoneyCard removeTopCard() {
		MoneyCard removed = this.moneyCardList.get(0);
		this.moneyCardList.remove(0);
		this.setTotalCard(totalCard - 1);
		tooltip.setText(Integer.toString(this.getTotalCard()) + " cards left");

		return removed;
	}

	// -------------------------------------Getter&setter--------------------------------------
	public ArrayList<MoneyCard> getMoneyCardList() {
		return moneyCardList;
	}

	public void setMoneyCardList(ArrayList<MoneyCard> moneyCardList) {
		this.moneyCardList = moneyCardList;
	}

	public int getTotalCard() {
		return totalCard;
	}

	public void setTotalCard(int totalCard) {
		this.totalCard = totalCard;
	}

	public void printMoney() {
		for (MoneyCard m : this.moneyCardList) {
			System.out.println(m.getName());
		}
	}

	public ImageView getMoneyDeck() {
		return moneyDeck;
	}

	public void setMoneyDeck(ImageView moneyDeck) {
		this.moneyDeck = moneyDeck;
	}

}
