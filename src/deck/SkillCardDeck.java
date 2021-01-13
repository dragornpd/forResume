package deck;

import java.util.ArrayList;
import java.util.Collections;

import base.FontUse;
import card.SkillCard;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SkillCardDeck implements Deck {
	private ArrayList<SkillCard> skillCardList;
	private int totalCardLeft;
	private ImageView skillDeck = new ImageView(ClassLoader.getSystemResource("img/" + "SkillDeck.png").toString());
	private Tooltip tooltip = new Tooltip();

	public SkillCardDeck(ArrayList<SkillCard> skillCardList) {
		this.setSkillCardList(skillCardList);
		this.setTotalCardLeft(this.getSkillCardList().size());
		// tooltip show size of moneydeck
		tooltip.setFont(FontUse.font20);
		tooltip.setText(Integer.toString(this.getTotalCardLeft()) + " cards left");
		this.skillDeck.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this.getSkillDeck(), e.getScreenX(), e.getScreenY() + 10);
		});
		this.getSkillDeck().setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	//shuffle the deck
	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle(this.skillCardList);
	}

	//draw top card
	public SkillCard removeTopCard() {
		// TODO Auto-generated method stub
		SkillCard removed = this.getSkillCardList().get(0);
		this.skillCardList.remove(0);
		this.setTotalCardLeft(this.totalCardLeft - 1);
		tooltip.setText(Integer.toString(this.getTotalCardLeft()) + " cards left");
		return removed;
	}

	//---------------------------GETTER&SETTER-------------------------//
	public ArrayList<SkillCard> getSkillCardList() {
		return skillCardList;
	}

	public void setSkillCardList(ArrayList<SkillCard> skillCardList) {
		this.skillCardList = skillCardList;
	}

	public int getTotalCardLeft() {
		return totalCardLeft;
	}

	public void setTotalCardLeft(int totalCardLeft) {
		this.totalCardLeft = totalCardLeft;
	}

	public ImageView getSkillDeck() {
		return skillDeck;
	}

	public void setSkillDeck(ImageView skillDeck) {
		this.skillDeck = skillDeck;
	}

}
