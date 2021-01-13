package skill;

import application.Main;
import base.SoundUse;
import card.MoneyCard;
import gui.CasinoPane;
import gui.PlayerPane;
import gui.RunningPane;
import object.Casino;
import object.Player;

public class CharacterSkill extends Skill {
	
	private String description;

	public CharacterSkill(String skillName) {
		super(skillName);
		switch (skillName) {
		case "plus_10000":
			this.description = "Plus $10000 to the highest card in selected casino.";
			break;
		case "sub_10000":
			this.description = "Decrease $10000 to the highest card in selected casino.";
			break;
		case "addOneDice":
			this.description = "Add One More Dice to your list";
			break;
		case "add_8000":
			this.description = "Add $8000 to yourself";
			break;
		case "subtract_8000":
			this.description = "Choose one player and decrease his money $8000";
			break;
		case "steal_4000":
			this.description = "Choose one player and steal his money $4000";
			break;
		case "stealAll":
			this.description = "Steal all player money $1000";
			break;
		case "reDeal":
			this.description = "renew casino money card";
			break;

		}
	}
	//activate this character skill//
	public void activateMySkill() {
		switch (this.getSkillName()) {
		case "plus_10000":
			CasinoPane.highlight();
			for (Casino casino : Main.casinoList) {
				CasinoPane.casinoInHash.get(casino).setOnMouseClicked(event -> {
					SoundUse.useSkill.play();
					for (Casino c : Main.casinoList) {
						CasinoPane.casinoInHash.get(c).setOnMouseEntered(null);
						CasinoPane.casinoInHash.get(c).unhighlight();
						CasinoPane.casinoInHash.get(c).setOnMouseExited(null);
						CasinoPane.casinoInHash.get(c).setOnMouseClicked(null);
					}
					this.plus_10000(casino);
					CasinoPane.casinoInHash.get(casino).update();
					RunningPane.changePane();
					for (Player player : Main.playerList) {
						if (player.getMyCharacterID() == 1) {
							PlayerPane.playerSelectChoice.get(player).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				});
			}
			super.setUsed(true);
			break;
		case "sub_10000":
			CasinoPane.highlight();
			for (Casino casino : Main.casinoList) {
				CasinoPane.casinoInHash.get(casino).setOnMouseClicked(event -> {
					SoundUse.useSkill.play();
					for (Casino c : Main.casinoList) {
						CasinoPane.casinoInHash.get(c).setOnMouseEntered(null);
						CasinoPane.casinoInHash.get(c).unhighlight();
						CasinoPane.casinoInHash.get(c).setOnMouseExited(null);
						CasinoPane.casinoInHash.get(c).setOnMouseClicked(null);
					}
					this.sub_10000(casino);
					CasinoPane.casinoInHash.get(casino).update();
					RunningPane.changePane();

					for (Player player : Main.playerList) {
						if (player.getMyCharacterID() == 2) {
							PlayerPane.playerSelectChoice.get(player).getSelectChoicePane().setDisable(false);
							break;
						}
					}

				});
			}
			super.setUsed(true);
			break;
		case "reDeal":
			CasinoPane.highlight();
			for (Casino casino : Main.casinoList) {
				CasinoPane.casinoInHash.get(casino).setOnMouseClicked(event -> {
					SoundUse.useSkill.play();
					for (Casino c : Main.casinoList) {
						CasinoPane.casinoInHash.get(c).setOnMouseEntered(null);
						CasinoPane.casinoInHash.get(c).unhighlight();
						CasinoPane.casinoInHash.get(c).setOnMouseExited(null);
						CasinoPane.casinoInHash.get(c).setOnMouseClicked(null);
					}
					this.reDeal(casino);
					CasinoPane.casinoInHash.get(casino).update();
					RunningPane.changePane();

					for (Player player : Main.playerList) {
						if (player.getMyCharacterID() == 3) {
							PlayerPane.playerSelectChoice.get(player).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				});
			}
			super.setUsed(true);
			break;
		case "addOneDice":

			for (Player player : Main.playerList) {
				if (player.getMyCharacterID() == 4) {
					this.addOneDice(player);

					PlayerPane.playerInHash.get(player).update();

					RunningPane.changePane();

					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 4) {
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
					SoundUse.useSkill.play();
					super.setUsed(true);
					break;
				}
			}
			break;
		case "add_8000":
			for (Player player : Main.playerList) {
				if (player.getMyCharacterID() == 5) {
					this.add_8000(player);

					PlayerPane.playerInHash.get(player).update();

					RunningPane.changePane();

					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 5) {
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
					SoundUse.useSkill.play();
					super.setUsed(true);
					break;
				}
			}
			break;
		case "subtract_8000":
			PlayerPane.highlight();
			int i = 0;
			for (Player player : Main.playerList) {
				if (player.getMyCharacterID() == 6 || player.getTotalMoney() == 0) {
					PlayerPane.playerInHash.get(player).setOnMouseEntered(null);
					PlayerPane.playerInHash.get(player).unhighlight();
					PlayerPane.playerInHash.get(player).setOnMouseExited(null);
					PlayerPane.playerInHash.get(player).setOnMouseClicked(null);
					i++;
				}
				PlayerPane.playerInHash.get(player).setOnMouseClicked(event -> {
					SoundUse.useSkill.play();
					for (Player p : Main.playerList) {
						PlayerPane.playerInHash.get(p).setOnMouseEntered(null);
						PlayerPane.playerInHash.get(p).unhighlight();
						PlayerPane.playerInHash.get(p).setOnMouseExited(null);
						PlayerPane.playerInHash.get(p).setOnMouseClicked(null);
					}

					this.subtract_8000(player);
					PlayerPane.playerInHash.get(player).update();
					RunningPane.changePane();

					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 6) {
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				});
				if (i == 5) {
					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 6) {
							RunningPane.changePane();
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				}
			}
			super.setUsed(true);
			break;
		case "steal_4000":
			PlayerPane.highlight();
			int j = 0;
			for (Player player : Main.playerList) {
				if (player.getMyCharacterID() == 7 || player.getTotalMoney() == 0) {
					PlayerPane.playerInHash.get(player).setOnMouseEntered(null);
					PlayerPane.playerInHash.get(player).unhighlight();
					PlayerPane.playerInHash.get(player).setOnMouseExited(null);
					PlayerPane.playerInHash.get(player).setOnMouseClicked(null);
					j++;
				}
				PlayerPane.playerInHash.get(player).setOnMouseClicked(event -> {
					for (Player p : Main.playerList) {
						PlayerPane.playerInHash.get(p).setOnMouseEntered(null);
						PlayerPane.playerInHash.get(p).unhighlight();
						PlayerPane.playerInHash.get(p).setOnMouseExited(null);
						PlayerPane.playerInHash.get(p).setOnMouseClicked(null);
					}

					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 7) {
							this.steal_4000(p, player);
							PlayerPane.playerInHash.get(p).update();
							PlayerPane.playerInHash.get(player).update();
							RunningPane.changePane();
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				});
				if (j == 5) {
					for (Player p : Main.playerList) {
						if (p.getMyCharacterID() == 7) {
							RunningPane.changePane();
							PlayerPane.playerSelectChoice.get(p).getSelectChoicePane().setDisable(false);
							break;
						}
					}
				}
			}
			SoundUse.useSkill.play();
			super.setUsed(true);
			break;

		case "stealAll":

			// System.out.println("you steal everyone noob shit");
			for (Player player : Main.playerList) {
				if (player.getMyCharacterID() == 8) {
					this.stealAll(player);
					super.setUsed(true);
					RunningPane.changePane();
					PlayerPane.playerSelectChoice.get(player).getSelectChoicePane().setDisable(false);
					break;
				}
			}
			for (Player player : Main.playerList) {
				PlayerPane.playerInHash.get(player).update();
			}
			SoundUse.useSkill.play();
			super.setUsed(true);
			break;
		default:
			break;

		}
	}

	// --------------------------------------------method for using skill----------------------------//

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void plus_10000(Casino casino) {
		int cost = casino.getMoneyCardList().get(0).getCost() + 10000;
		casino.getMoneyCardList().remove(0);
		casino.addMoneyCardBySkill(new MoneyCard("$" + cost, cost));
	}

	public void sub_10000(Casino casino) {
		int cost = casino.getMoneyCardList().get(0).getCost() - 10000;
		casino.getMoneyCardList().remove(0);
		casino.addMoneyCardBySkill(new MoneyCard("$" + cost, cost));
	}

	public void reDeal(Casino casino) {
		casino.refreshMoney();
		while (!casino.isOver50000()) {
			casino.addMoneyCard(Main.monDeck.removeTopCard());
		}

	}

	public void addOneDice(Player p) {
		p.addDice();
	}

	public void add_8000(Player p) {
		p.setTotalMoney(p.getTotalMoney() + 8000);
	}

	public void subtract_8000(Player p) {
		if (p.getTotalMoney() < 8000) {
			p.subMoney(p.getTotalMoney());
		} else {
			p.subMoney(8000);
		}
	}

	public void steal_4000(Player self, Player target) {
		if (target.getTotalMoney() < 4000) {
			self.addMoney(target.getTotalMoney());
			target.subMoney(target.getTotalMoney());
		} else {
			self.addMoney(4000);
			target.subMoney(4000);
		}
	}

	public void stealAll(Player self) {
		int getTotal = 0;
		for (Player p : Main.playerList) {
			if (p.getMyCharacterID() != self.getMyCharacterID()) {
				if (p.getTotalMoney() < 1000) {
					p.subMoney(p.getTotalMoney());
					getTotal += p.getTotalMoney();
				} else {
					p.subMoney(1000);
					getTotal += 1000;
				}
			}
		}
		self.addMoney(getTotal);
	}

}
