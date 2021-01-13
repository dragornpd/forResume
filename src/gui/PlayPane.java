package gui;

import application.Main;
import base.FontUse;
import base.SoundUse;
import card.SkillCard;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import object.Player;

public class PlayPane extends StackPane {
	private Pane background = new Pane();
	private PackingPane packingPane = new PackingPane();
	public static ImageView skillDeckGif = new ImageView(
			ClassLoader.getSystemResource("img/" + "SkillDeck.gif").toString());
	public static BorderPane cuttingScene = new BorderPane();
	public static Label round = new Label("Round 1");
	public static Label textSkill = new Label();

	//this pane is the playing pane with contain packing pane and adding pane//
	public PlayPane() {
		SoundUse.playingSound.setCycleCount(AudioClip.INDEFINITE);
		SoundUse.playingSound.play();
		
		this.setPrefSize(1200, 675);

		//setup textskill to make you know the skill description
		textSkill.setPrefSize(1200, 165);
		textSkill.setFont(FontUse.font30);
		textSkill.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		textSkill.setDisable(true);
		textSkill.setOpacity(0.0);
		textSkill.setAlignment(Pos.CENTER);

		//setup cuttingscene is a scene with tell you that what round now
		cuttingScene.setPrefSize(1200, 675);
		cuttingScene.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		cuttingScene.setCenter(round);
		
		Label click = new Label("Click to continue");

		round.setFont(FontUse.font120);
		round.setTextFill(Color.WHITE);
		click.setFont(FontUse.font80);
		click.setTextFill(Color.WHITE);
		BorderPane.setAlignment(click, Pos.CENTER);
		cuttingScene.setBottom(click);

		Image image = new Image("img/BackgroundGamePlaying.png");
		BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		background.setBackground(new Background(myBI));
		background.setOpacity(1);

		PlayPane.skillDeckGif.setDisable(true);
		PlayPane.skillDeckGif.setOpacity(0.0);

		this.getChildren().addAll(background, packingPane, textSkill, skillDeckGif, cuttingScene);

		//if click, use skillcard and tell the skill//
		PlayPane.skillDeckGif.setOnMouseClicked(event -> {
			SoundUse.dealSkillCard.play();
			SkillCard skillUsed = Main.gableSkillCard();
			textSkill.setText(skillUsed.getCardSkill().getDescription() + "   .....Click to continue");
			FadeTransition fadeout = new FadeTransition(Duration.millis(1000), PlayPane.skillDeckGif);
			fadeout.setFromValue(1.0);
			fadeout.setToValue(0.0);
			fadeout.play();
			PlayPane.skillDeckGif.setDisable(true);
			PlayPane.textSkill.setDisable(false);
			FadeTransition fadein = new FadeTransition(Duration.millis(1000), PlayPane.textSkill);
			fadein.setFromValue(0.0);
			fadein.setToValue(1.0);
			fadein.play();
			this.getPackingPane().setDisable(false);
			for (Player player : Main.playerList) {
				PlayerPane.playerCheckSelectChoice.get(player).setup();
				PlayerPane.playerInHash.get(player).update();
			}
		});

		//click to continue to play
		PlayPane.textSkill.setOnMouseClicked(event -> {
			SoundUse.mouseClick.play();
			FadeTransition fadeout = new FadeTransition(Duration.millis(1000), PlayPane.textSkill);
			fadeout.setFromValue(1.0);
			fadeout.setToValue(0.0);
			fadeout.play();
			PlayPane.textSkill.setDisable(true);
		});

		clickCuttingScene(1);

	}

	//use to display the skilldeck
	public static void displaySkill() {
		PlayPane.skillDeckGif.setDisable(false);
		FadeTransition fadein = new FadeTransition(Duration.millis(1000), PlayPane.skillDeckGif);
		fadein.setFromValue(0.0);
		fadein.setToValue(1.0);
		fadein.play();

	}

	//click and activate skill
	public static void clickCuttingScene(int i) {
		SoundUse.useSkill.play();
		PlayPane.cuttingScene.setDisable(false);
		PlayPane.round.setText("Round " + i);
		FadeTransition fadein = new FadeTransition(Duration.millis(300), PlayPane.cuttingScene);
		fadein.setFromValue(0.0);
		fadein.setToValue(1.0);
		fadein.play();
		PlayPane.cuttingScene.setOnMouseClicked(event -> {
			SoundUse.mouseClick.play();
			FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), PlayPane.cuttingScene);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			PlayPane.cuttingScene.setDisable(true);
			if (Main.checkRound != 0) {
				displaySkill();
			}
		});
	}
	
	public PackingPane getPackingPane() {
		return packingPane;
	}

	public void setPackingPane(PackingPane packingPane) {
		this.packingPane = packingPane;
	}

}
