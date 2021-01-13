package object;

import javafx.scene.paint.Color;

public class Dice {
	private String color;
	private int faceValue = 0;
	private Color colorCode;

	public Dice(String color) {
		super();
		this.setColor(color);
		;
		switch (this.getColor()) {
		case "Red":
			this.colorCode = Color.RED;
			break;
		case "Blue":
			this.colorCode = Color.BLUE;
			break;
		case "Green":
			this.colorCode = Color.GREEN;
			break;
		case "Yellow":
			this.colorCode = Color.YELLOW;
			break;
		case "Black":
			this.colorCode = Color.BLACK;
			break;
		default:
			break;
		}
	}
	
	//construct with facevalue use for checking in early phase of project
	public Dice(String color, int faceValue) {
		this.setColor(color);
		this.setFaceValue(faceValue);
	}

	//random the face value
	public int roll() {
		faceValue = (int) (Math.random() * 6) + 1;
		this.setFaceValue(faceValue);
		return faceValue;
	}
	//---------------------------------Getter&Setter-------------------------------//
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	public Color getColorCode() {
		return colorCode;
	}

	public void setColorCode(Color colorCode) {
		this.colorCode = colorCode;
	}

}
