package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.algorithms.KDATool;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils.Reporter;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils.SaveLoader;

public class IntroController {
    @FXML
    private TextField killsField;
    @FXML
    private TextField assistsField;
    @FXML
    private TextField deathsField;
    @FXML
    private TextField desiredKDAField;

    @FXML
    private TextArea resultArea;

    public void initialize(){
        SaveLoader.load(killsField, assistsField, deathsField, desiredKDAField);
    }

    @FXML
    private void onCalculateButtonClick(){
        KDATool calculator= new KDATool();
        String result="";
    if(killsField.getText().isEmpty()||assistsField.getText().isEmpty()||deathsField.getText().isEmpty()){
        Reporter.alertErrorReporting("Error","Fill in all the necessary data");
    } else {

        int kills = Integer.parseInt(killsField.getText());
        int assists= Integer.parseInt(assistsField.getText());
        int deaths= Integer.parseInt(deathsField.getText());

        result+="Current KDA: "+calculator.kdaCalculate(kills,assists,deaths)+"\n\n";
        result+="Difference: "+calculator.diff(kills,deaths)+" kills\n\n";
        if(!desiredKDAField.getText().isEmpty()){

            double desiredKDA=Double.parseDouble(desiredKDAField.getText());

            result+="Kills to desired KDA: "+calculator.desiredKDA(kills,assists,deaths,desiredKDA)+" \n";
        }
        resultArea.setText(result);
    }
    }

    @FXML
    private void onSaveButtonClick(){
    SaveLoader.save(killsField.getText(), assistsField.getText(), deathsField.getText(), desiredKDAField.getText());
    }
}