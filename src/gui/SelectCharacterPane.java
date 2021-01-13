package gui;

import java.util.ArrayList;

import application.Main;
import base.FontUse;
import base.SoundUse;
import exception.NotEnoughData;
import exception.SameColor;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import object.CharacterList;
import object.Player;

public class SelectCharacterPane extends HBox {
	private EventHandler<MouseEvent> select;
	private static String color;
	private static String name;
	private boolean selectComplete;
	private int CharacterID;
	HBox root;

	public SelectCharacterPane() {
		// create character selection scene
		root = new HBox();
		root.setPrefSize(1200, 675);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		ArrayList<Button> buttonList = createCharacButton();
		addToopTip(buttonList);
		select = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				color = null;
				name = null;
				selectComplete = false;
				SoundUse.mouseClick.play();

				String id = ((((Control) (event.getSource())).getId()));
				CharacterID = getID(id);
				for (Button btn : buttonList) {
					btn.setDisable(true);
				}
				// create new enter data window
				FlowPane root = new FlowPane();
				root .setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
				root.setPrefSize(300, 200);
				root.setPadding(new Insets(10, 10, 10, 10));
				root.setHgap(10);
				root.setVgap(10);
				Label enter = new Label("Enter Name : ");
				enter.setPrefSize(100, 20);
				enter.setFont(FontUse.font20);
				root.getChildren().add(enter);
				TextField nameField = new TextField();
				nameField.setPrefSize(180, 20);
				nameField.setFont(FontUse.font20);
				nameField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				root.getChildren().add(nameField);

				Label selectColor = new Label("Select Color : ");
				selectColor.setPrefSize(300, 20);
				selectColor.setFont(FontUse.font20);
				root.getChildren().add(selectColor);

				// create color button
				createColorButton(root);

				// create confirm button
				Label Blank = new Label("");
				Blank.setPrefWidth(200);
				root.getChildren().add(Blank);

				Button confirmBtn = new Button("Confirm");
				confirmBtn.setFont(FontUse.font20);
				confirmBtn.setPrefWidth(80);
				root.getChildren().add(confirmBtn);
				
				//----------------------add set on---------------//
				
				nameField.setOnMouseEntered(KeyEvent -> {
					SoundUse.mouseEnter.play();
				});
				nameField.setOnMouseClicked(KeyEvent -> {
					SoundUse.mouseClick.play();
				});
				nameField.setOnKeyPressed(Keyevent -> {
					SoundUse.diceClick.isPlaying();
				});
				confirmBtn.setOnMouseEntered(MouseEvent -> {
					SoundUse.mouseEnter.play();
				});

				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setTitle("Confirm Character");
				stage.setScene(scene);
				stage.show();

				// occur when click x button on top right
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent arg0) {
						// TODO Auto-generated method stub
						setClickOnButton(buttonList);
					}
				});

				// press confirm and add data
				confirmBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						name = nameField.getText();

						try {
							SoundUse.mouseClick.play();
							addData();
						} catch (NotEnoughData e) {
							// TODO Auto-generated catch block
						} catch (SameColor e) {

						}
						if (selectComplete) {
							// cant click on the button that already select
							setClickOnButton(buttonList);
							stage.close();
							// change that button appearance
							for (int i = 0; i < 8; i++) {
								Button btn = buttonList.get(i);
								if (getID(btn.getId()) == CharacterID) {
									btn.setStyle("-fx-background-color: #000000");
									btn.setGraphic(null);
								}
							}
						} else {
							for (Button btn : buttonList) {
								btn.setDisable(true);
								;
							}
						}

						if (Main.idCharacterList.size() == 5) {
							for (int i = 0; i < 5; i++) {
								Main.playerList.add(new Player(Main.PlayerName.get(i),
										Main.idCharacterList.get(i), Main.ColorList.get(i)));
							}
							Main.root.getChildren().remove(0);
							PlayPane play = new PlayPane();
							Main.sceneList.add(play);
							Main.setScene(2);
							SoundUse.startSound.stop();
						}
					}
				});
			}
		};
		setClickOnButton(buttonList);
		for (Button btn : buttonList) {
			btn.setOnMouseClicked(select);
		}
	}

	public void addData() throws NotEnoughData, SameColor {
		if (color == null || name.equals("")) {
			// if not enter name or color you need to enter
			this.selectComplete = false;
			throw new NotEnoughData("Enter Name or Color");
		} else if (Main.ColorList.contains(color)) {
			throw new SameColor("This color is Already taken");
		} else {

			Main.idCharacterList.add(CharacterID);
			Main.ColorList.add(color);
			Main.PlayerName.add(name);
			this.selectComplete = true;

		}
	}

	public int getID(String id) {
		switch (id) {
		case "charac1":
			return 1;
		case "charac2":
			return 2;
		case "charac3":
			return 3;
		case "charac4":
			return 4;
		case "charac5":
			return 5;
		case "charac6":
			return 6;
		case "charac7":
			return 7;
		case "charac8":
			return 8;
		}
		return 0;
	}

	public void thisColorIsAlreadySelect() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Warning");
		alert.setContentText("This color is already selected");
		alert.showAndWait().ifPresent(rs -> {
		});
	}

	public void setClickOnButton(ArrayList<Button> buttonList) {
		for (int i = 0; i < 8; i++) {
			Button btn = buttonList.get(i);
			int id = getID(buttonList.get(i).getId());
			;
			if (Main.idCharacterList.contains(id)) {
				btn.setDisable(true);

			} else {
				btn.setDisable(false);
			}

		}
	}

	public void addToopTip(ArrayList<Button> btnList) {
//		CharacterList charList = new CharacterList();
		for (int i = 0; i < 8; i++) {
			Button btn = btnList.get(i);
			Tooltip tooltip = new Tooltip();
			tooltip.setFont(FontUse.font20);
			tooltip.setText(CharacterList.characterList[i].getCharacterSkill().getDescription());
			btn.setOnMouseMoved((MouseEvent e) -> {
				tooltip.show(btn, e.getScreenX(), e.getScreenY() + 10);
			});
			btn.setOnMouseExited((MouseEvent e) -> {
				btn.setBackground(
						new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				tooltip.hide();
			});
		}
	}

	public ArrayList<Button> createCharacButton() {

		ArrayList<Button> buttonList = new ArrayList<>();
		// new button
		Button charac1 = new Button();
		charac1.setId("charac1");
		Button charac2 = new Button();
		charac2.setId("charac2");
		Button charac3 = new Button();
		charac3.setId("charac3");
		Button charac4 = new Button();
		charac4.setId("charac4");
		Button charac5 = new Button();
		charac5.setId("charac5");
		Button charac6 = new Button();
		charac6.setId("charac6");
		Button charac7 = new Button();
		charac7.setId("charac7");
		Button charac8 = new Button();
		charac8.setId("charac8");
		// add button to list
		buttonList.add(charac1);
		buttonList.add(charac2);
		buttonList.add(charac3);
		buttonList.add(charac4);
		buttonList.add(charac5);
		buttonList.add(charac6);
		buttonList.add(charac7);
		buttonList.add(charac8);
		// get image
		Image img1 = new Image("img/" + "Character1.png");
		Image img2 = new Image("img/" + "Character2.png");
		Image img3 = new Image("img/" + "Character3.png");
		Image img4 = new Image("img/" + "Character4.png");
		Image img5 = new Image("img/" + "Character5.png");
		Image img6 = new Image("img/" + "Character6.png");
		Image img7 = new Image("img/" + "Character7.png");
		Image img8 = new Image("img/" + "Character8.png");

		// set background
		charac1.setGraphic(new ImageView(img1));
		charac2.setGraphic(new ImageView(img2));
		charac3.setGraphic(new ImageView(img3));
		charac4.setGraphic(new ImageView(img4));
		charac5.setGraphic(new ImageView(img5));
		charac6.setGraphic(new ImageView(img6));
		charac7.setGraphic(new ImageView(img7));
		charac8.setGraphic(new ImageView(img8));

		// set size
		for (int i = 0; i < 8; i++) {
			Button btn = buttonList.get(i);
			btn.setMaxSize(150, 675);
			btn.setMinSize(150, 675);
			btn.setPrefSize(150, 675);
			btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			btn.setOnMouseEntered(event -> {
				SoundUse.mouseEnter.play();
				if (!SoundUse.startSound.isPlaying()) {
					SoundUse.startSound.play();
				}
				btn.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
			});
		}

		// add to root
		for (int i = 0; i < 8; i++) {
			Button btn = buttonList.get(i);
			this.getChildren().add(btn);
		}
		return buttonList;
	}

	public void createColorButton(FlowPane root) {
		Button redBtn = new Button();
		redBtn.setShape(new Circle(25));
		redBtn.setMinSize(50, 50);
		redBtn.setMaxSize(50, 50);
		redBtn.setStyle("-fx-border-color: black");
		redBtn.setStyle("-fx-background-color: #FF0000");
		redBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
		});
		redBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SoundUse.mouseClick.play();
				if (Main.ColorList.contains("red")) {
					thisColorIsAlreadySelect();
				} else {
					color = "Red";
				}
			}

		});
		root.getChildren().add(redBtn);

		Button greenBtn = new Button();
		greenBtn.setShape(new Circle(25));
		greenBtn.setMinSize(50, 50);
		greenBtn.setMaxSize(50, 50);
		greenBtn.setStyle("-fx-border-color: black");
		greenBtn.setStyle("-fx-background-color: #008000");
		greenBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
		});
		greenBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SoundUse.mouseClick.play();
				if (Main.ColorList.contains("green")) {
					thisColorIsAlreadySelect();
				} else {
					color = "Green";
				}
			}

		});
		root.getChildren().add(greenBtn);

		Button blueBtn = new Button();
		blueBtn.setShape(new Circle(25));
		blueBtn.setMinSize(50, 50);
		blueBtn.setMaxSize(50, 50);
		blueBtn.setStyle("-fx-border-color: black");
		blueBtn.setStyle("-fx-background-color: #0000FF");
		blueBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
		});
		blueBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SoundUse.mouseClick.play();
				if (Main.ColorList.contains("blue")) {
					thisColorIsAlreadySelect();
				} else {
					color = "Blue";
				}
			}

		});
		root.getChildren().add(blueBtn);

		Button blackBtn = new Button();
		blackBtn.setShape(new Circle(25));
		blackBtn.setMinSize(50, 50);
		blackBtn.setMaxSize(50, 50);
		blackBtn.setStyle("-fx-background-color: #000000");
		blackBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
		});
		blackBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SoundUse.mouseClick.play();
				if (Main.ColorList.contains("black")) {
					thisColorIsAlreadySelect();
				} else {
					color = "Black";
				}
			}

		});
		root.getChildren().add(blackBtn);

		Button yellowBtn = new Button();
		yellowBtn.setShape(new Circle(25));
		yellowBtn.setMinSize(50, 50);
		yellowBtn.setMaxSize(50, 50);
		yellowBtn.setStyle("-fx-border-color: black");
		yellowBtn.setStyle("-fx-background-color: #FFFF00");
		yellowBtn.setOnMouseEntered(event -> {
			SoundUse.mouseEnter.play();
		});
		yellowBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SoundUse.mouseClick.play();
				if (Main.ColorList.contains("yellow")) {
					thisColorIsAlreadySelect();
				} else {
					color = "Yellow";
				}
			}

		});
		root.getChildren().add(yellowBtn);
	}

}