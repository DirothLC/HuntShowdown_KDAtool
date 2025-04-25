package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils;

import javafx.scene.control.Alert;

public class Reporter {
    public static void alertConfirmReporting(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void alertErrorReporting(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
