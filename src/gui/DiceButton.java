package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import object.Dice;

public class DiceButton extends Button {
	private Dice dice;
	private ImageView faceValueImage;

	//create to be a dice pane
	public DiceButton(Dice dice) {
		this.setDice(dice);
		Image faceValueImage = new Image(ClassLoader
				.getSystemResource(
						"img/" + this.getDice().getColor() + "dice_" + this.getDice().getFaceValue() + ".png")
				.toString(), 50, 50, false, false);
		this.faceValueImage = new ImageView(faceValueImage);
		this.setGraphic(this.faceValueImage);
		this.setBackground(
				new Background(new BackgroundFill(this.getDice().getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	//highlight with Gold color
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	//unhighlight
	public void unhighlight() {
		this.setBackground(
				new Background(new BackgroundFill(this.getDice().getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	//------------------------------GETTER&SETTER----------------------------------//
	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public ImageView getFaceValueImage() {
		return faceValueImage;
	}

	public void setFaceValueImage(ImageView faceValueImage) {
		this.faceValueImage = faceValueImage;
	}

}
