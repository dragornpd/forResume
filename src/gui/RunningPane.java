package gui;

import application.Main;
import base.SoundUse;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import object.Casino;
import object.Player;

public class RunningPane extends StackPane {
	private Player player;
	private SelectChoicePane selectChoicePane;
	private PlayerRollingPane playerRollingPane;

	//after call playpane it will be here to run logic
	public RunningPane(Player player) {
		this.player = player;
		selectChoicePane = new SelectChoicePane(this.player);
		playerRollingPane = new PlayerRollingPane(this.player);
		playerRollingPane.setDisable(true);
		playerRollingPane.setOpacity(0);
		this.getChildren().addAll(selectChoicePane, playerRollingPane);

		//dealcard to make casino have money
		Main.dealCard();

		//click roll to roll the dice and pick the dice
		this.selectChoicePane.getRollBtn().setOnMouseClicked(event -> {
			Thread clickRoll = new Thread(() -> {
				try {
					SoundUse.mouseClick.play();
					Thread.sleep(100);
					SoundUse.rollDice.play();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			clickRoll.start();
			this.player.playerRoll();
			this.player.sortDiceList();
			this.playerRollingPane.showUpdate();
			Thread runningRoll = new Thread(() -> {
				try {
					clickRoll.join();
					this.swapPane();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			runningRoll.start();

			for (DiceButton dBtn : this.playerRollingPane.getStoreDice()) {
				dBtn.setOnMouseEntered(MouseEvent -> {
					SoundUse.mouseEnterDice.play();
					for (DiceButton diceBtn : this.playerRollingPane.getStoreDice()) {
						if (dBtn.getDice().getFaceValue() == diceBtn.getDice().getFaceValue()) {
							diceBtn.highlight();
						}
					}
				});
				dBtn.setOnMouseExited(MouseEvent -> {
					for (DiceButton diceBtn : this.playerRollingPane.getStoreDice()) {
						if (dBtn.getDice().getFaceValue() == diceBtn.getDice().getFaceValue()) {
							diceBtn.unhighlight();
						}
					}
				});
				dBtn.setOnMouseClicked(MouseEvent -> {
					SoundUse.diceClick.play();
					int selected = dBtn.getDice().getFaceValue();
					for (DiceButton diceBtn : this.playerRollingPane.getStoreDice()) {
						if (dBtn.getDice().getFaceValue() == diceBtn.getDice().getFaceValue()) {
							diceBtn.unhighlight();
						}
					}
					this.player.pickDice(selected);
					CasinoPane.casinoInHash.get(Main.casinoList.get(selected - 1)).update();
					PlayerPane.playerInHash.get(this.player).update();
					selected = 0;

					changePane();
					this.unswapPane();
				});
			}
		});

		//click useskill to activate the skill
		this.selectChoicePane.getUseSkillBtn().setOnMouseClicked(event -> {
			this.selectChoicePane.setDisable(true);
			this.selectChoicePane.getPlayer().getMyCharacter().useSkill();
			PlayerPane.playerInHash.get(this.player).update();
			this.selectChoicePane.changePane();

		});

	}

	// after use skill or roll dice will change the pane by this method
	public static void changePane() {
		Player p = PlayerPane.playerRunning.get(0);
		PlayerPane.playerInHash.get(p).unhighlight();
		PlayerPane.playerRunning.remove(0);
		if (!p.isEmptyDice()) {
			PlayerPane.playerRunning.add(p);
		}

		if (!(Main.playerList.get(0).isEmptyDice() && Main.playerList.get(1).isEmptyDice()
				&& Main.playerList.get(2).isEmptyDice() && Main.playerList.get(3).isEmptyDice()
				&& Main.playerList.get(4).isEmptyDice())) {
			PackingPane.setPlayerSelectChoice(PlayerPane.playerRunning.get(0));
			PlayerPane.playerInHash.get(PlayerPane.playerRunning.get(0)).highlight();
		} else {
			if (Main.checkRound == 4) {
				for (Player player : Main.playerList) {
					PlayerPane.playerInHash.get(player).update();
				}
				Main.root.getChildren().remove(0);
				WinnerPane winner = new WinnerPane();
				Main.sceneList.add(winner);
				Main.setScene(3);
				SoundUse.playingSound.stop();
			} else {
				Main.checkRound += 1;
				PlayPane.clickCuttingScene(Main.checkRound + 1);

				for (Casino c : Main.casinoList) {
					c.addMoneyToPlayer();
					c.refreshCasino();
				}
				Main.dealCard();
				for (Player p1 : Main.playerList) {
					p1.refreshDiceList();
					PlayerPane.playerInHash.get(p1).update();
					PlayerPane.playerRunning.removeAll(PlayerPane.playerRunning);
				}
				for (int i = Main.checkRound; i < (5 + Main.checkRound); i++) {
					PlayerPane.playerRunning.add(Main.playerList.get(i % 5));
				}
				PackingPane.setPlayerSelectChoice(PlayerPane.playerRunning.get(0));
				PlayerPane.playerInHash.get(PlayerPane.playerRunning.get(0)).highlight();

			}
		}

	}

	//swap the rollinfpane up and disbla another
	public void swapPane() {
		selectChoicePane.setDisable(true);
		playerRollingPane.setDisable(false);
		FadeTransition fadeIn = new FadeTransition(Duration.millis(500), playerRollingPane);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}

	//unswap
	public void unswapPane() {
		selectChoicePane.setDisable(false);
		playerRollingPane.setDisable(true);
		FadeTransition fadeOut = new FadeTransition(Duration.millis(500), playerRollingPane);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public SelectChoicePane getSelectChoicePane() {
		return selectChoicePane;
	}

	public void setSelectChoicePane(SelectChoicePane selectChoicePane) {
		this.selectChoicePane = selectChoicePane;
	}

	public PlayerRollingPane getPlayerRollingPane() {
		return playerRollingPane;
	}

	public void setPlayerRollingPane(PlayerRollingPane playerRollingPane) {
		this.playerRollingPane = playerRollingPane;
	}

}
