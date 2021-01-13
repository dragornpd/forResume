package card;

import base.FontUse;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MoneyCard implements Cloneable, Comparable<Object> {
	private int cost;
	private String name;
	private String url;
	private ImageView imageMoney;
	private Tooltip tooltip = new Tooltip();

	public MoneyCard(String name, int cost) {
		this.name = name;
		this.setCost(cost);
		switch (this.getName()) {
		case "$10000":
			this.setUrl("Money_01.png");
			break;
		case "$20000":
			this.setUrl("Money_02.png");
			break;
		case "$30000":
			this.setUrl("Money_03.png");
			break;
		case "$40000":
			this.setUrl("Money_04.png");
			break;
		case "$50000":
			this.setUrl("Money_05.png");
			break;
		case "$60000":
			this.setUrl("Money_06.png");
			break;
		case "$70000":
			this.setUrl("Money_07.png");
			break;
		case "$80000":
			this.setUrl("Money_08.png");
			break;
		case "$90000":
			this.setUrl("Money_09.png");
			break;
		case "$100000":
			this.setUrl("Money_10.png");
			break;
		default:
			break;
		}
		String image = ClassLoader.getSystemResource("img/" + this.getUrl()).toString();
		this.imageMoney = new ImageView(image);
		//set tooltip to show money
		tooltip.setFont(FontUse.font20);
		tooltip.setText(this.getName());
		this.imageMoney.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this.imageMoney, e.getScreenX(), e.getScreenY() + 10);
		});
		this.imageMoney.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	public MoneyCard clone() {
		try {
			return (MoneyCard) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// use to sort the cost of money card
	@Override
	public int compareTo(Object o) {
		MoneyCard e = (MoneyCard) o;
		if (this.getCost() > e.getCost()) {
			return 1;
		} else if (this.getCost() < e.getCost()) {
			return -1;
		}
		return 0;
	}
	//---------------------------------Getter&Setter-------------------------------//
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageView getImageMoney() {
		return imageMoney;
	}

	public void setImageMoney(ImageView imageMoney) {
		this.imageMoney = imageMoney;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
