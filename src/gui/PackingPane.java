package gui;

import application.Main;
import deck.MoneyCardDeck;
import deck.SkillCardDeck;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import object.Player;

public class PackingPane extends BorderPane {
	private CasinoPane casinoPane = new CasinoPane();
	public static Pane playerSelectChoice = new Pane();
	private PlayerPane playerPane = new PlayerPane();
	private MoneyCardDeck moneyCardDecks = Main.monDeck;
	private SkillCardDeck skillCardDecks = Main.skillDeck;

	//use to pack all pane into one pane//
	public PackingPane() {
		PackingPane.setPlayerSelectChoice(Main.playerList.get(0));
		PlayerPane.playerInHash.get(PlayerPane.playerRunning.get(0)).highlight();

		//setting moneyCardDeck//
		StackPane moneyCardDeck = new StackPane();
		moneyCardDeck.setAlignment(Pos.CENTER);
		moneyCardDeck.setMinSize(200, 185);
		moneyCardDeck
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		moneyCardDeck.getChildren().add(this.moneyCardDecks.getMoneyDeck());

		//setting skillCardDeck//
		StackPane skillCardDeck = new StackPane();
		skillCardDeck.setAlignment(Pos.CENTER);
		skillCardDeck.setMinSize(200, 185);
		skillCardDeck
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		skillCardDeck.getChildren().add(this.skillCardDecks.getSkillDeck());

		//setup this pane//
		this.setPrefSize(1200, 675);
		this.setLeft(moneyCardDeck);
		this.setRight(skillCardDeck);
		this.setBottom(this.playerPane);
		this.setTop(this.casinoPane);
		this.setCenter(PackingPane.playerSelectChoice);

		BorderPane.setAlignment(playerSelectChoice, Pos.CENTER);
	}

	//set pane of playerselection pane for run the program//
	public static void setPlayerSelectChoice(Player player) {
		playerSelectChoice.getChildren().removeAll(playerSelectChoice.getChildren());
		playerSelectChoice.getChildren().add(PlayerPane.playerSelectChoice.get(player));
		FadeTransition fadeIn = new FadeTransition(Duration.millis(500), PlayerPane.playerSelectChoice.get(player));
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
	}

	//-------------------GETTER&SETTER------------------------------//
	public CasinoPane getCasinoPane() {
		return casinoPane;
	}

	public void setCasinoPane(CasinoPane casinoPane) {
		this.casinoPane = casinoPane;
	}

	public PlayerPane getPlayerPane() {
		return playerPane;
	}

	public void setPlayerPane(PlayerPane playerPane) {
		this.playerPane = playerPane;
	}

}
