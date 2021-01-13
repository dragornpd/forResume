package gui;

import base.FontUse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import object.Player;
import skill.Skill;

public class PlayerInformation extends StackPane {
	private Player player;
	private Label name = new Label("Name :");
	private Label yourName = new Label();
	private Label money = new Label("Money :");
	private Label yourMoney = new Label("0");
	private Label skill = new Label("Skill :");
	private Circle usedSkill = new Circle(10, Color.LIMEGREEN);
	private ImageView colorDiceIcon;
	private Label countDice = new Label("0");
	private GridPane grid = new GridPane();
	private Pane backgroundPlayer = new Pane();
	StackPane backgroundPane = new StackPane();
	private Tooltip tooltip = new Tooltip();

	//pane refer to player contain information of player
	public PlayerInformation(Player player) {
		this.player = player;
		//setup the pane
		name.setTextFill(Color.WHITE);
		name.setFont(FontUse.font30);
		yourName.setTextFill(Color.WHITE);
		yourName.setFont(FontUse.font30);
		yourMoney.setTextFill(Color.WHITE);
		yourMoney.setFont(FontUse.font30);
		money.setTextFill(Color.WHITE);
		money.setFont(FontUse.font30);
		skill.setTextFill(Color.WHITE);
		skill.setFont(FontUse.font30);
		countDice.setTextFill(Color.WHITE);
		countDice.setFont(FontUse.font30);
		name.setAlignment(Pos.CENTER_LEFT);
		money.setAlignment(Pos.CENTER_LEFT);
		skill.setAlignment(Pos.CENTER_LEFT);
		
		this.setPrefSize(240, 240);
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		grid.setPadding(new Insets(20));
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.add(name, 0, 0);
		grid.add(money, 0, 1);
		grid.add(skill, 0, 2);
		grid.add(yourName, 1, 0);
		grid.add(yourMoney, 1, 1);
		grid.add(usedSkill, 1, 2);
		grid.add(countDice, 1, 3);

		tooltip.setFont(FontUse.font20);
		tooltip.setText(this.getPlayer().getMyCharacter().getCharacterSkill().getDescription());
		this.skill.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this.skill, e.getScreenX(), e.getScreenY() + 10);
		});
		this.skill.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	//initial the player infomation by first update
	public void init(Player player) {
		this.setPlayer(player);
		this.update();
		this.colorDiceIcon = new ImageView(new Image(
				ClassLoader.getSystemResource("img/" + this.getPlayer().getColor() + "dice_1.png").toString()));
		grid.add(colorDiceIcon, 0, 3);

		backgroundPane.setMaxSize(230, 230);
		backgroundPane.setBackground(
				new Background(new BackgroundFill(this.getPlayer().getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));

		Image background = new Image(ClassLoader
				.getSystemResource("img/" + "CharacterIcon" + this.getPlayer().getMyCharacterID() + ".png").toString());
		BackgroundImage myBI = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		backgroundPlayer.setBackground(new Background(myBI));

		backgroundPane.getChildren().add(backgroundPlayer);
		backgroundPane.setOpacity(0.7);

		this.getChildren().addAll(backgroundPane, grid);
	}

	//use to update all change in player
	public void update() {
		this.yourName.setText(this.getPlayer().getName());
		this.yourMoney.setText(Integer.toString(this.getPlayer().getTotalMoney()));

		if ((this.getPlayer().getMyCharacter().getCharacterSkill().isUsed()) || (!Skill.enable)) {
			this.usedSkill.setFill(Color.RED);
		} else if (Skill.enable) {
			this.usedSkill.setFill(Color.LIMEGREEN);
		}
		this.countDice.setText(Integer.toString(this.getPlayer().getNumDice()));

	}

	//highlight gold color
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		backgroundPane.setOpacity(1);
	}
	
	//highlight red color
	public void pickedhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		backgroundPane.setOpacity(1);
	}
	
	//unhighlight
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		backgroundPane.setOpacity(0.7);
	}
	
	//---------------------------GETTER&SETTER----------------------------//

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
