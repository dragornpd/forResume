package gui;

import java.util.ArrayList;

import base.FontUse;
import card.MoneyCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.Casino;

public class CasinoInformation extends StackPane {
	private Casino casino;
	private GridPane grid = new GridPane();
	private Pane pane = new Pane();
	private String idBackground;
	private HBox countRed = new HBox();
	private HBox countBlue = new HBox();
	private HBox countGreen = new HBox();
	private HBox countYellow = new HBox();
	private HBox countBlack = new HBox();
	private HBox moneyBox = new HBox();
	private ArrayList<MoneyCard> checkList = new ArrayList<MoneyCard>();
	private Tooltip redDice = new Tooltip();
	private Tooltip blueDice = new Tooltip();
	private Tooltip greenDice = new Tooltip();
	private Tooltip yellowDice = new Tooltip();
	private Tooltip blackDice = new Tooltip();

	//pane refer to casino contain information of casino
	public CasinoInformation(Casino casino) {
		this.setCasino(casino);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(200, 250);
		
		//pane use to be a background
		this.idBackground = "Casino" + Integer.toString(this.getCasino().getStataicNumber()) + ".png";
		Image image = new Image(ClassLoader.getSystemResource("img/" + this.idBackground).toString());
		BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBI));
		pane.setOpacity(0.7);
		
		//add moneycard to checklist
		for (int i = 0; i < this.getCasino().getMoneyCardList().size(); i++) {
			this.checkList.add(this.getCasino().getMoneyCardList().get(i));
		}
		
		//grid setup
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(10);

		//set space of counting dice
		countRed.setSpacing(2);
		countBlue.setSpacing(2);
		countGreen.setSpacing(2);
		countYellow.setSpacing(2);
		countBlack.setSpacing(2);
		
		//setting reddice//
		String red = ClassLoader.getSystemResource("img/" + "Reddice_" + this.casino.getStataicNumber() + ".png")
				.toString();
		ImageView redDice = new ImageView(red);
		this.redDice.setFont(FontUse.font20);
		this.redDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Red")) + " Dices");
		redDice.setOnMouseMoved((MouseEvent e) -> {
			this.redDice.show(redDice, e.getScreenX(), e.getScreenY() + 10);
		});
		redDice.setOnMouseExited((MouseEvent e) -> {
			this.redDice.hide();
		});
		
		//setting bluedice//
		String blue = ClassLoader.getSystemResource("img/" + "Bluedice_" + this.casino.getStataicNumber() + ".png")
				.toString();
		ImageView blueDice = new ImageView(blue);
		this.blueDice.setFont(FontUse.font20);
		this.blueDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Blue")) + " Dices");
		blueDice.setOnMouseMoved((MouseEvent e) -> {
			this.blueDice.show(blueDice, e.getScreenX(), e.getScreenY() + 10);
		});
		blueDice.setOnMouseExited((MouseEvent e) -> {
			this.blueDice.hide();
		});
		
		//setting greendice//
		String green = ClassLoader.getSystemResource("img/" + "Greendice_" + this.casino.getStataicNumber() + ".png")
				.toString();
		ImageView greenDice = new ImageView(green);
		this.greenDice.setFont(FontUse.font20);
		this.greenDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Green")) + " Dices");
		greenDice.setOnMouseMoved((MouseEvent e) -> {
			this.greenDice.show(greenDice, e.getScreenX(), e.getScreenY() + 10);
		});
		greenDice.setOnMouseExited((MouseEvent e) -> {
			this.greenDice.hide();
		});
		
		//setting yellowdice//
		String yellow = ClassLoader.getSystemResource("img/" + "Yellowdice_" + this.casino.getStataicNumber() + ".png")
				.toString();
		ImageView yellowDice = new ImageView(yellow);
		this.yellowDice.setFont(FontUse.font20);
		this.yellowDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Yellow")) + " Dices");
		yellowDice.setOnMouseMoved((MouseEvent e) -> {
			this.yellowDice.show(yellowDice, e.getScreenX(), e.getScreenY() + 10);
		});
		yellowDice.setOnMouseExited((MouseEvent e) -> {
			this.yellowDice.hide();
		});
		
		//setting blackdice//
		String black = ClassLoader.getSystemResource("img/" + "Blackdice_" + this.casino.getStataicNumber() + ".png")
				.toString();
		ImageView blackDice = new ImageView(black);
		this.blackDice.setFont(FontUse.font20);
		this.blackDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Black")) + " Dices");
		blackDice.setOnMouseMoved((MouseEvent e) -> {
			this.blackDice.show(blackDice, e.getScreenX(), e.getScreenY() + 10);
		});
		blackDice.setOnMouseExited((MouseEvent e) -> {
			this.blackDice.hide();
		});
		
		//setup alignment to pos.center
		countRed.setAlignment(Pos.CENTER_LEFT);
		countBlue.setAlignment(Pos.CENTER_LEFT);
		countGreen.setAlignment(Pos.CENTER_LEFT);
		countYellow.setAlignment(Pos.CENTER_LEFT);
		countBlack.setAlignment(Pos.CENTER_LEFT);

		//set icon money
		String moneyIcon = ClassLoader.getSystemResource("img/" + "money.png").toString();
		ImageView moneyIconView = new ImageView(moneyIcon);
		moneyIconView.setFitHeight(35);
		moneyIconView.setFitWidth(22);

		//moneybox use to contain money card
		moneyBox.setSpacing(5);
		for (MoneyCard money : this.casino.getMoneyCardList()) {
			moneyBox.getChildren().add(money.getImageMoney());
		}

		grid.add(redDice, 0, 0);
		grid.add(blueDice, 0, 1);
		grid.add(greenDice, 0, 2);
		grid.add(yellowDice, 0, 3);
		grid.add(blackDice, 0, 4);
		grid.add(moneyIconView, 0, 5);
		grid.add(countRed, 1, 0);
		grid.add(countBlue, 1, 1);
		grid.add(countGreen, 1, 2);
		grid.add(countYellow, 1, 3);
		grid.add(countBlack, 1, 4);
		grid.add(moneyBox, 1, 5);

		this.getChildren().addAll(pane, grid);

	}

	//use to update all information in this casino//
	public void update() {
		refreshToolTip();
		refreshDice();
		boolean checkChange = false; //check if this have changed, do the code below to update data

		if (this.checkList.size() != this.getCasino().getMoneyCardList().size()) {
			checkChange = true;
		} else {
			for (int i = 0; i < checkList.size(); i++) {
				if (this.getCasino().getMoneyCardList().get(i) != checkList.get(i)
						|| this.getCasino().getMoneyCardList().get(i).getName() != checkList.get(i).getName()) {
					checkChange = true;
				}
			}
		}
		if (checkChange) {
			this.moneyBox.getChildren().removeAll(this.moneyBox.getChildren());
			for (MoneyCard money : this.casino.getMoneyCardList()) {
				moneyBox.getChildren().add(money.getImageMoney());
			}
			this.checkList.removeAll(this.checkList);
			for (int i = 0; i < this.getCasino().getMoneyCardList().size(); i++) {
				this.checkList.add(this.getCasino().getMoneyCardList().get(i));
			}
			checkChange = false;
		}
	}

	//highlight with gold color
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pane.setOpacity(1);
	}

	//highlight with red color
	public void pickedhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pane.setOpacity(1);
	}

	//unhighlight
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pane.setOpacity(0.7);
	}
	
	//update the dice in this casino
	public void refreshDice() {
		this.countRed.getChildren().removeAll(countRed.getChildren());
		for (int i = 0; i < this.casino.getPlayerDiceCountList().get("Red"); i++) {
			this.countRed.getChildren()
					.add(new ImageView(new Image(
							ClassLoader.getSystemResource("img/" + "Reddice_" + this.casino.getStataicNumber() + ".png")
									.toString(),
							10, 10, false, false)));
		}

		this.countBlue.getChildren().removeAll(countBlue.getChildren());
		for (int i = 0; i < this.casino.getPlayerDiceCountList().get("Blue"); i++) {
			this.countBlue.getChildren()
					.add(new ImageView(new Image(ClassLoader
							.getSystemResource("img/" + "Bluedice_" + this.casino.getStataicNumber() + ".png")
							.toString(), 10, 10, false, false)));
		}

		this.countGreen.getChildren().removeAll(countGreen.getChildren());
		for (int i = 0; i < this.casino.getPlayerDiceCountList().get("Green"); i++) {
			this.countGreen.getChildren()
					.add(new ImageView(new Image(ClassLoader
							.getSystemResource("img/" + "Greendice_" + this.casino.getStataicNumber() + ".png")
							.toString(), 10, 10, false, false)));
		}

		this.countYellow.getChildren().removeAll(countYellow.getChildren());
		for (int i = 0; i < this.casino.getPlayerDiceCountList().get("Yellow"); i++) {
			this.countYellow.getChildren()
					.add(new ImageView(new Image(ClassLoader
							.getSystemResource("img/" + "Yellowdice_" + this.casino.getStataicNumber() + ".png")
							.toString(), 10, 10, false, false)));
		}

		this.countBlack.getChildren().removeAll(countBlack.getChildren());
		for (int i = 0; i < this.casino.getPlayerDiceCountList().get("Black"); i++) {
			this.countBlack.getChildren()
					.add(new ImageView(new Image(ClassLoader
							.getSystemResource("img/" + "Blackdice_" + this.casino.getStataicNumber() + ".png")
							.toString(), 10, 10, false, false)));
		}
	}

	//update the information of tooltip
	public void refreshToolTip() {
		this.redDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Red")) + " Dices");
		this.blueDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Blue")) + " Dices");
		this.greenDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Green")) + " Dices");
		this.yellowDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Yellow")) + " Dices");
		this.blackDice.setText(Integer.toString(this.casino.getPlayerDiceCountList().get("Black")) + " Dices");
	}
	
	//----------------------------GETTER & SETTER-------------------------//

	public Casino getCasino() {
		return casino;
	}

	public void setCasino(Casino casino) {
		this.casino = casino;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}
}
