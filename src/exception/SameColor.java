package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SameColor extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2210358159147034672L;
	String message;

	public SameColor(String message) {
		this.message = message;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Warning");
		alert.setContentText(this.message);
		alert.showAndWait().ifPresent(rs -> {
		});

	}
}
