package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.Main;
import base.FontUse;
import base.SoundUse;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import object.Character;
import object.CharacterList;
import object.Player;
import skill.Skill;

public class WinnerPane extends StackPane {
	private ArrayList<Player> playerCheckList = Main.playerList;
	private GridPane announce = new GridPane();
	private VBox pane = new VBox();
	private Pane background = new Pane();
	private StackPane packing = new StackPane();
	private Button exitBtn = new Button("EXIT");
	private Button playAgain = new Button("PLAY AGAIN");

	// show the winner of the game//
	public WinnerPane() {
		//setup this pane
		SoundUse.endingSound.setCycleCount(AudioClip.INDEFINITE);
		SoundUse.endingSound.play();
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(1200, 675);
		announce.setVgap(30);
		announce.setHgap(80);
		pane.setAlignment(Pos.CENTER);
		packing.setAlignment(Pos.CENTER);
		announce.setAlignment(Pos.CENTER);

		//setup exitBtn
		exitBtn.setPrefSize(180, 40);
		exitBtn.setFont(FontUse.font30);
		exitBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		exitBtn.setOpacity(0.5);
		exitBtn.setTextFill(Color.WHITE);
		exitBtn.setAlignment(Pos.CENTER);

		//setup playagain button
		playAgain.setPrefSize(180, 40);
		playAgain.setFont(FontUse.font30);
		playAgain.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		playAgain.setOpacity(0.5);
		playAgain.setTextFill(Color.WHITE);

		Image image = new Image(ClassLoader.getSystemResource("img/" + "EndingBackground.gif").toString());
		BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		this.setBackground(new Background(myBI));

		ImageView first = new ImageView(
				new Image(ClassLoader.getSystemResource("img/" + "1st.png").toString(), 40, 40, false, false));
		ImageView second = new ImageView(
				new Image(ClassLoader.getSystemResource("img/" + "2nd.png").toString(), 40, 40, false, false));
		ImageView third = new ImageView(
				new Image(ClassLoader.getSystemResource("img/" + "3rd.png").toString(), 40, 40, false, false));

		Label announcement = new Label("ANNOUNCEMENT");
		announcement.setFont(FontUse.font40);

		//setup background
		background.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		background.setOpacity(0.7);
		background.setMaxSize(600, 450);

		//list the winner which sorted by total  money
		this.sortPlayerList();
		Label player1 = new Label(this.playerCheckList.get(0).getName());
		Label player2 = new Label(this.playerCheckList.get(1).getName());
		Label player3 = new Label(this.playerCheckList.get(2).getName());
		Label player4 = new Label(this.playerCheckList.get(3).getName());
		Label player5 = new Label(this.playerCheckList.get(4).getName());
		Label money1 = new Label(Integer.toString(this.playerCheckList.get(0).getTotalMoney()));
		Label money2 = new Label(Integer.toString(this.playerCheckList.get(1).getTotalMoney()));
		Label money3 = new Label(Integer.toString(this.playerCheckList.get(2).getTotalMoney()));
		Label money4 = new Label(Integer.toString(this.playerCheckList.get(3).getTotalMoney()));
		Label money5 = new Label(Integer.toString(this.playerCheckList.get(4).getTotalMoney()));

		//set all font
		player1.setFont(FontUse.font30);
		player2.setFont(FontUse.font30);
		player3.setFont(FontUse.font30);
		player4.setFont(FontUse.font30);
		player5.setFont(FontUse.font30);
		money1.setFont(FontUse.font30);
		money2.setFont(FontUse.font30);
		money3.setFont(FontUse.font30);
		money4.setFont(FontUse.font30);
		money5.setFont(FontUse.font30);

		//add to announce
		announce.add(first, 0, 0);
		announce.add(second, 0, 1);
		announce.add(third, 0, 2);
		announce.add(player1, 1, 0);
		announce.add(player2, 1, 1);
		announce.add(player3, 1, 2);
		announce.add(player4, 1, 3);
		announce.add(player5, 1, 4);
		announce.add(money1, 2, 0);
		announce.add(money2, 2, 1);
		announce.add(money3, 2, 2);
		announce.add(money4, 2, 3);
		announce.add(money5, 2, 4);
		announce.add(playAgain, 1, 5);
		announce.add(exitBtn, 2, 5);

		announcement.setUnderline(true);

		pane.getChildren().addAll(announcement, announce);

		packing.getChildren().addAll(background, pane);

		this.getChildren().add(packing);

		//---------------------------add action-----------------------//
		//click to exit the game
		exitBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			exitBtn.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
			exitBtn.setOpacity(1);
		});
		exitBtn.setOnMouseExited(event -> {
			exitBtn.setAlignment(Pos.CENTER);
			exitBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			exitBtn.setOpacity(0.5);
		});

		exitBtn.setOnMouseClicked(event -> {
			Platform.exit();
			SoundUse.endingSound.stop();
		});

		//click to play again
		playAgain.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			playAgain.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
			playAgain.setOpacity(1);
		});
		playAgain.setOnMouseExited(event -> {
			playAgain.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			playAgain.setOpacity(0.5);
		});

		playAgain.setOnMouseClicked(event -> {
			for (Character c : CharacterList.characterList) {
				c.getCharacterSkill().setUsed(false);
			}
			Skill.enable = true;
			Main.sceneList.removeAll(Main.sceneList);
			Main.checkRound = 0;
			Main.PlayerName.removeAll(Main.PlayerName);
			Main.ColorList.removeAll(Main.ColorList);
			Main.idCharacterList.removeAll(Main.idCharacterList);
			Main.casinoList.removeAll(Main.casinoList);
			Main.playerList.removeAll(Main.playerList);
			Main.monDeck = null;
			Main.skillDeck = null;
			Main.prepare();
			Main.currentScene = null;
			Main.root.getChildren().removeAll(Main.root.getChildren());
			StartPane start = new StartPane();

			SelectCharacterPane select = new SelectCharacterPane();
			Main.sceneList.add(start);
			Main.sceneList.add(select);

			Main.root.getChildren().add(start);
			SoundUse.endingSound.stop();
		});
	}

	class SortbyTotalMoney implements Comparator<Player> {
		@Override
		public int compare(Player o1, Player o2) {
			// TODO Auto-generated method stub
			return o2.getTotalMoney() - o1.getTotalMoney();
		}
	} // use for sort diceList

	public ArrayList<Player> sortPlayerList() {
		Collections.sort(this.playerCheckList, new SortbyTotalMoney());
		return this.playerCheckList;
	}
}
