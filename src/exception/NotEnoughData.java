package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotEnoughData extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public NotEnoughData(String message) {
		this.message = message;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Warning");
		alert.setContentText(this.message);
		alert.showAndWait().ifPresent(rs -> {
		});

	}
}
