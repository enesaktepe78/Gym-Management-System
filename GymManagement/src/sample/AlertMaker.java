package sample;

import javafx.scene.control.Alert;

public class AlertMaker {

    public static void AlertInformation(String contents) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contents);
        alert.showAndWait();
    }

    public static void AlertError(String contents) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(contents);
        alert.showAndWait();

    }
}
