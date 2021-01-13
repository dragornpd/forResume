package gui;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import object.Player;
import object.Dice;

public class PlayerRollingPane extends HBox {
	private Player player;
	private ArrayList<DiceButton> storeDice = new ArrayList<DiceButton>();
	private int selected;
	
	//rollind dice pane use when click roll
	public PlayerRollingPane(Player player) {
		this.player = player;
		this.setSpacing(10);
		this.setPrefSize(800, 185);
		this.setAlignment(Pos.CENTER);
	}

	//show the dice that you roll
	public void showUpdate() {
		this.clear();
		for (Dice d : this.player.getDiceList()) {
			DiceButton diceBtn = new DiceButton(d);
			storeDice.add(diceBtn);
			this.getChildren().add(diceBtn);
		}
	}

	//clear all dice
	public void clear() {
		storeDice.removeAll(storeDice);
		this.getChildren().removeAll(this.getChildren());
	}

	//--------------------------GETTER&SETTER--------------------//
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getSelected() {
		return selected;
	}

	public ArrayList<DiceButton> getStoreDice() {
		return storeDice;
	}

}
