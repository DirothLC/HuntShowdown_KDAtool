package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HuntKDACalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HuntKDACalculator.class.getResource("/kz/huntshowdown/kdacalc/huntshowdownkdacalculator/views/intro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hunt KDA tool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}