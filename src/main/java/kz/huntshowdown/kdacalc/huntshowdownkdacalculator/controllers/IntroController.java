package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.algorithms.KDATool;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils.Reporter;
import kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils.SaveLoader;

public class IntroController {
    @FXML
    private Spinner<Integer> killsField;
    @FXML
    private Spinner<Integer> assistsField;
    @FXML
    private Spinner<Integer> deathsField;
    @FXML
    private Spinner<Double> desiredKDAField;

    @FXML
    private TextArea resultArea;

    public void initialize(){

        killsField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000,0,1));
        deathsField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000,0,1));
        assistsField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000,0,1));
        desiredKDAField.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,1000000.0,0.0,0.01));

        killsField.getEditor().setPromptText("Kills");
        deathsField.getEditor().setPromptText("Deaths");
        assistsField.getEditor().setPromptText("Assists");
        desiredKDAField.getEditor().setPromptText("Desired by KDA");

        SaveLoader.load(killsField, assistsField, deathsField, desiredKDAField);

    }

    @FXML
    private void onCalculateButtonClick(){
        KDATool calculator= new KDATool();
        String result="";

        if(killsField.getValue() == null || deathsField.getValue() == null || assistsField.getValue() == null){
            Reporter.alertErrorReporting("Error","Please fill all fields");
            return;
        }

        int kills = killsField.getValue();
        int assists= assistsField.getValue();
        int deaths= deathsField.getValue();

        result+="Current KDA: "+calculator.kdaCalculate(kills,assists,deaths)+"\n\n";
        result+="Difference: "+calculator.diff(kills,deaths)+" kills\n\n";

        if(desiredKDAField.getValue()!=null) {
            double desiredKDA = desiredKDAField.getValue();
            result += "Kills to desired KDA: " + calculator.desiredKDA(kills, assists, deaths, desiredKDA) + " \n";
        }
        resultArea.setText(result);

    }

    @FXML
    private void onSaveButtonClick(){
    SaveLoader.save(killsField, assistsField, deathsField, desiredKDAField);
    }
}