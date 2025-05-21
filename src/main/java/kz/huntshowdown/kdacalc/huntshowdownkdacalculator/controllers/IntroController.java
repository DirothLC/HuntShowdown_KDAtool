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

        double currentKDA = calculator.kdaCalculate(kills, assists, deaths);
        double nextKDA = currentKDA+0.01;
        double previousKDA = currentKDA-0.01;

        result+="Current KDA: "+currentKDA+"\n";
        result+="Difference: "+calculator.diff(kills,deaths)+" kills\n";

        result+="Kills to "+nextKDA+" KDA: "+calculator.desiredKDA(kills, assists, deaths, nextKDA)+" kills\n";
        result+="Deaths to "+previousKDA+" KDA: "+calculator.deathsToLowerKDA(kills, assists, deaths, previousKDA)+" deaths\n";

        if(desiredKDAField.getValue()!=null) {
            double desiredKDA = desiredKDAField.getValue();
            if(desiredKDA<currentKDA){
                result += "Deaths to target KDA (lower): " + calculator.deathsToLowerKDA(kills, assists, deaths, desiredKDA);
            }else {
                result += "Kills to desired KDA: " + calculator.desiredKDA(kills, assists, deaths, desiredKDA) + " \n";
            }
        }
        resultArea.setText(result);

    }

    @FXML
    private void onSaveButtonClick(){
    SaveLoader.save(killsField, assistsField, deathsField, desiredKDAField);
    }
}