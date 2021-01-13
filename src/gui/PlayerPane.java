package gui;

import java.util.ArrayList;
import java.util.HashMap;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import object.Player;

public class PlayerPane extends HBox {
	public static HashMap<Player, PlayerInformation> playerInHash = new HashMap<Player, PlayerInformation>();
	public static HashMap<Player, RunningPane> playerSelectChoice = new HashMap<Player, RunningPane>();
	public static HashMap<Player, SelectChoicePane> playerCheckSelectChoice = new HashMap<Player, SelectChoicePane>();
	public static ArrayList<Player> playerRunning = new ArrayList<Player>();

	//pack all player information pane into one pane//
	public PlayerPane() {
		this.setPrefSize(1200, 240);
		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		for (int i = 0; i < 5; i++) {
			playerRunning.add(Main.playerList.get(i));
			PlayerInformation p = new PlayerInformation(Main.playerList.get(i));
			this.getChildren().add(p);
			playerInHash.put(Main.playerList.get(i), p);
			PlayerPane.playerInHash.get(Main.playerList.get(i)).init(Main.playerList.get(i));
			playerSelectChoice.put(Main.playerList.get(i), new RunningPane(Main.playerList.get(i)));
			playerCheckSelectChoice.put(Main.playerList.get(i),
					playerSelectChoice.get(Main.playerList.get(i)).getSelectChoicePane());
			playerInHash.put(Main.playerList.get(i), p);
		}

	}

	//highlight with gold every casino but only mouseenter get it red
	public static void highlight() {
		for (Player player : Main.playerList) {
			PlayerPane.playerInHash.get(player).highlight();
			PlayerPane.playerInHash.get(player).setOnMouseEntered(event -> {
				PlayerPane.playerInHash.get(player).pickedhighlight();
			});
			PlayerPane.playerInHash.get(player).setOnMouseExited(event -> {
				PlayerPane.playerInHash.get(player).highlight();
			});

		}

	}
}
