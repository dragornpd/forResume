package gui;

import application.Main;
import base.FontUse;
import base.SoundUse;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class StartPane extends StackPane {
	private Button start = new Button("Start");
	private Button tutorial = new Button("Tutorial");
	private VBox tutorialPane = new VBox();
	private Button back = new Button("Back");
	private Label tutorialInfo = new Label();
	private Label createdByUs = new Label("Created by PogUMeth");
	private VBox pack = new VBox();
	private Tooltip tooltip = new Tooltip();

	//start pane//
	public StartPane() {
		SoundUse.startSound.setCycleCount(AudioClip.INDEFINITE);
		SoundUse.startSound.play();
		
		this.setPrefSize(1200, 675);
		Image image = new Image(ClassLoader.getSystemResource("img/" + "StartBackground.gif").toString());
		BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		this.setBackground(new Background(myBI));

		//setup tooltip to tell our name
		tooltip.setFont(FontUse.font30);
		tooltip.setText("Dragorn Pandungpattanodom 6231323221\nNatchanon Panthuwadeethorn 6231321021");
		this.createdByUs.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this.createdByUs, e.getScreenX(), e.getScreenY() + 10);
		});
		this.createdByUs.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});

		//setup start button
		start.setTextFill(Color.WHITE);
		start.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		start.setFont(FontUse.font70);
		start.setPrefSize(300, 125);
		start.setOpacity(0.75);
		start.setAlignment(Pos.CENTER);
		
		//setup tutorial button
		tutorial.setTextFill(Color.WHITE);
		tutorial.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		tutorial.setFont(FontUse.font70);
		tutorial.setPrefSize(300, 125);
		tutorial.setOpacity(0.5);
		tutorial.setAlignment(Pos.CENTER);

		//setup label createdby
		createdByUs.setAlignment(Pos.CENTER);
		createdByUs.setFont(FontUse.font30);
		createdByUs.setPrefSize(300, 50);
		createdByUs.setTextFill(Color.WHITE);
		createdByUs.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		createdByUs.setOpacity(0.5);

		//pack start tutorial createdbyus together
		pack.setAlignment(Pos.CENTER);
		pack.getChildren().addAll(start, tutorial, createdByUs);
		pack.setSpacing(30);

		//setup tutorial infomation
		tutorialInfo.setPrefSize(1280, 575);
		tutorialInfo.setTextAlignment(TextAlignment.CENTER);
		tutorialInfo
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		tutorialInfo.setFont(FontUse.font30);
		tutorialInfo.setTextFill(Color.BLACK);
		tutorialInfo.setAlignment(Pos.CENTER);
		tutorialInfo.setText("How to play: this game is very easy. "
				+ "\nFirst, roll the dice. Then, you choose the number of the dice. "
				+ "\nFor example, if your roll is 1,2,3,3,3,3,4,5 and you choose number 3. "
				+ "\nYou will lose 4 dices and those are going to the casino number 3. "
				+ "\nOr you can use your character skill to gain an advantage, but you cannot roll in that turn.\r"
				+ "\nHow to gain money? : It’s so simple. "
				+ "\nThe dice with the highest amount which is not the same amount as others win the prize. "
				+ "\nIf dices of two player have the same amount, both of them won’t be used to calculate the winner in that casino. "
				+ "\nFor example, if number 1 casino‘s dice amount list is 5,5,5,4 and 1. "
				+ "\nPlayer that has 4 dices will win and get the first prize. "
				+ "\nPlayer that has 1 dice will get the second prize if second prize is available. "
				+ "\nAnd players with 5 dices won’t get anything. \r"
				+ "\nHow to win the game? : The player with the highest money wins. ");

		//setup back button
		back.setFont(FontUse.font30);
		back.setTextFill(Color.WHITE);
		back.setPrefSize(200, 50);
		back.setAlignment(Pos.CENTER);
		back.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		back.setOpacity(0.5);

		//setup tutorial pane
		tutorialPane.setAlignment(Pos.CENTER);
		tutorialPane.setSpacing(10);
		tutorialPane.setPrefSize(1280, 675);
		tutorialPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		tutorialPane.setDisable(true);
		tutorialPane.setOpacity(0.0);
		tutorialPane.getChildren().addAll(tutorialInfo, back);

		this.getChildren().addAll(pack, tutorialPane);

		// ----------------------------------set action-------------------------
		//click to start game
		start.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			start.setOpacity(1);
			start.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

		});
		start.setOnMouseExited(event -> {
			start.setOpacity(0.5);
			start.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		start.setOnMouseClicked(event -> {
			SoundUse.mouseClick.play();
			Main.setScene(1);

		});

		//click to read tutorial
		tutorial.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			tutorial.setOpacity(1);
			tutorial.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

		});
		tutorial.setOnMouseExited(event -> {
			tutorial.setOpacity(0.5);
			tutorial.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		tutorial.setOnMouseClicked(event -> {
			SoundUse.mouseClick.play();
			pack.setDisable(true);
			tutorialPane.setDisable(false);
			FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), tutorialPane);
			fadeIn.setFromValue(0.0);
			fadeIn.setToValue(1.0);
			fadeIn.play();
		});

		//click to go back to start
		back.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			back.setOpacity(1);
			back.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

		});
		back.setOnMouseExited(event -> {
			back.setOpacity(0.5);
			back.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		back.setOnMouseClicked(event -> {
			SoundUse.mouseClick.play();
			FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), tutorialPane);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			tutorialPane.setDisable(true);
			pack.setDisable(false);
		});
	}
}
