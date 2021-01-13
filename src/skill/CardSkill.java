package skill;

import application.Main;
import card.MoneyCard;
import gui.CasinoPane;
import gui.PlayerPane;
import object.Casino;
import object.Player;

public class CardSkill extends Skill {

	private String description;
	
	public CardSkill(String skillname) {
		super(skillname);
		switch (this.getSkillName()) {
		case "silence":
			this.setDescription("Everyone cant use character skill in this turn.");
			break;
		case "plusAll_10000":
			this.setDescription("Add $10000 to all Casino.");
			break;
		case "plusAllOneDice":
			this.setDescription("Add 1 more dice to everyone hand.");
			break;
		case "plusMinPlayer":
			this.setDescription("Add $10000 to the noobest player in this room.");
			break;

		default:
			break;
		}
	}

// use for activate
	@Override
	public void activateMySkill() {
		switch (this.getSkillName()) {
		case "silence":
			this.silence();
			break;
		case "plusAll_10000":
			this.plusAll_10000();
			break;
		case "plusAllOneDice":
			this.plusAllOneDice();
			break;
		case "plusMinPlayer":
			this.plusMinPlayer();
			break;

		default:
			break;
		}

	}

	// ----------------------------------------method for use
	// skill------------------------------------

	public void silence() {
		super.setEnable(false);
	}

	public void plusAll_10000() {
		super.setEnable(true);
		for (Casino casino : Main.casinoList) {
			casino.addMoneyCardBySkill(new MoneyCard("$10000", 10000));
			CasinoPane.casinoInHash.get(casino).update();
		}
	}

	public void plusAllOneDice() {
		super.setEnable(true);
		for (Player player : Main.playerList) {
			player.addDice();
			PlayerPane.playerInHash.get(player).update();
		}
	}

	public void plusMinPlayer() {
		super.setEnable(true);
		int checkMinValue = Main.playerList.get(0).getTotalMoney();
		for (int i = 0; i < 5; i++) {
			if (Main.playerList.get(i).getTotalMoney() <= checkMinValue) {
				checkMinValue = Main.playerList.get(i).getTotalMoney();
			}
		}

		for (Player player : Main.playerList) {
			if (player.getTotalMoney() == checkMinValue) {
				player.addMoney(10000);
				PlayerPane.playerInHash.get(player).update();
			}
		}
	}
//---------------------------------Getter&Setter-------------------------------//
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
