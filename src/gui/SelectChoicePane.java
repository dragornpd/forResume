package gui;

import base.SoundUse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import object.Player;
import skill.Skill;

public class SelectChoicePane extends VBox {
	private Player player;
	private Button rollBtn = new Button();
	private Button useSkillBtn = new Button();

	//contain roll button and use skill button
	public SelectChoicePane(Player player) {
		this.setPlayer(player);
		this.setPrefSize(800, 185);
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setSpacing(35);
		//setup rollBtn
		rollBtn.setPrefSize(200, 50);
		ImageView imageviewBtn1 = new ImageView(ClassLoader.getSystemResource("img/" + "RollBtn.png").toString());
		imageviewBtn1.setFitWidth(200);
		imageviewBtn1.setFitHeight(50);
		rollBtn.setGraphic(imageviewBtn1);
		rollBtn.setBackground(
				new Background(new BackgroundFill(this.player.getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		rollBtn.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		//setup useskillBtn
		useSkillBtn.setPrefSize(200, 50);
		ImageView imageviewBtn2 = new ImageView(ClassLoader.getSystemResource("img/" + "UseSkillBtn.png").toString());
		imageviewBtn2.setFitWidth(200);
		imageviewBtn2.setFitHeight(50);
		useSkillBtn.setGraphic(imageviewBtn2);
		useSkillBtn.setBackground(
				new Background(new BackgroundFill(this.player.getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		useSkillBtn.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		//setup this pane
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(rollBtn);
		this.getChildren().add(useSkillBtn);

		//------add listener-------------//
		rollBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			this.rollBtn.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		rollBtn.setOnMouseExited(event -> {
			this.rollBtn.setBackground(new Background(
					new BackgroundFill(this.getPlayer().getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		});

		useSkillBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
			this.useSkillBtn
					.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		});

		useSkillBtn.setOnMouseExited(event -> {
			this.useSkillBtn.setBackground(new Background(
					new BackgroundFill(this.getPlayer().getColorCode(), CornerRadii.EMPTY, Insets.EMPTY)));
		});

	}

	// setup the skill check that can use?
	public void setup() {
		if (Skill.enable) {
			this.useSkillBtn.setDisable(false);
			this.useSkillBtn.setVisible(true);
		} else {
			this.useSkillBtn.setDisable(true);
		}
	}
	
	//after use skill change pane
	public void changePane() {
		this.getChildren().removeAll(rollBtn, useSkillBtn);
		this.getChildren().add(rollBtn);
	}

	//-----------------------------GETTER&SETTER---------------------------//
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Button getRollBtn() {
		return rollBtn;
	}

	public Button getUseSkillBtn() {
		return useSkillBtn;
	}

}
