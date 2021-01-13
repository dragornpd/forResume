package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import base.SoundUse;
import card.MoneyCard;
import card.SkillCard;
import deck.MoneyCardDeck;
import deck.SkillCardDeck;
import gui.CasinoPane;

import gui.SelectCharacterPane;
import gui.StartPane;
import object.Casino;
import object.Player;
import skill.CardSkill;

public class Main extends Application {

	public static Pane root = new Pane();
	public static ArrayList<Pane> sceneList = new ArrayList<Pane>();

	public static Pane currentScene = new Pane();

	public static int checkRound = 0;
	public static ArrayList<String> PlayerName = new ArrayList<>();
	public static ArrayList<String> ColorList = new ArrayList<>();
	public static ArrayList<Integer> idCharacterList = new ArrayList<>();
	public static ArrayList<Casino> casinoList = new ArrayList<>();
	public static ArrayList<Player> playerList = new ArrayList<>();
	public static MoneyCardDeck monDeck;
	public static SkillCardDeck skillDeck;

	public void start(Stage stage) {
		// TODO Auto-generated method stub
		prepare();

		root.setPrefSize(1200, 675);
		StartPane start = new StartPane();
		SelectCharacterPane select = new SelectCharacterPane();
		Main.sceneList.add(start);
		Main.sceneList.add(select);
		root.getChildren().add(start);

		stage.getIcons().add(new Image(ClassLoader.getSystemResource("img/logo.png").toString(), 48, 48, false, false));
		stage.setTitle("LAS VEGAS");
		stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static void setScene(int index) {
		root.getChildren().add(sceneList.get(index));
		FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), sceneList.get(index));
		FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentScene);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeIn.play();
		fadeOut.play();
		currentScene = sceneList.get(index);

	}

	public static void dealCard() {
		Main.monDeck.shuffle();
		for (Casino c : Main.casinoList) {
			while (!c.isOver50000()) {
				c.addMoneyCard(Main.monDeck.removeTopCard());
			}
			CasinoPane.casinoInHash.get(c).update();
		}
	}

	public static SkillCard gableSkillCard() {
		Main.skillDeck.shuffle();
		SkillCard removed = Main.skillDeck.removeTopCard();
		removed.useSkill();
		return removed;
	}

	public static void prepare() {
		ArrayList<MoneyCard> monDeck = new ArrayList<MoneyCard>();
		ArrayList<SkillCard> skillDeck = new ArrayList<SkillCard>();
		new SoundUse();

		SkillCard skillCard1 = new SkillCard("silence", new CardSkill("silence"));
		SkillCard skillCard2 = new SkillCard("plusAll_10000", new CardSkill("plusAll_10000"));
		SkillCard skillCard3 = new SkillCard("plusAllOneDice", new CardSkill("plusAllOneDice"));
		SkillCard skillCard4 = new SkillCard("plueMinPlayer", new CardSkill("plusMinPlayer"));

		skillDeck.add(skillCard1);
		skillDeck.add(skillCard2);
		skillDeck.add(skillCard3);
		skillDeck.add(skillCard4);
		for (int i = 0; i < 6; i++) {
			monDeck.add(new MoneyCard("$10000", 10000));
			monDeck.add(new MoneyCard("$20000", 20000));
			monDeck.add(new MoneyCard("$30000", 30000));
			monDeck.add(new MoneyCard("$40000", 40000));
			monDeck.add(new MoneyCard("$50000", 50000));
			monDeck.add(new MoneyCard("$60000", 60000));
			monDeck.add(new MoneyCard("$70000", 70000));
			monDeck.add(new MoneyCard("$80000", 80000));
			monDeck.add(new MoneyCard("$90000", 90000));
		}

		Main.skillDeck = new SkillCardDeck(skillDeck);
		Main.monDeck = new MoneyCardDeck(monDeck);
		Main.casinoList.add(new Casino("1", 1));
		Main.casinoList.add(new Casino("2", 2));
		Main.casinoList.add(new Casino("3", 3));
		Main.casinoList.add(new Casino("4", 4));
		Main.casinoList.add(new Casino("5", 5));
		Main.casinoList.add(new Casino("6", 6));
	}

}
