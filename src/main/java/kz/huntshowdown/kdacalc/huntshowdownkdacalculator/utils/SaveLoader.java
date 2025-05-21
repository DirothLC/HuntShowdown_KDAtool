package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SaveLoader {
    private static final String FILE_NAME = ".huntkda_saved_data.properties";
    private static final Path FILE_PATH = Path.of(System.getProperty("user.home"), FILE_NAME);


    public static void load(Spinner<Integer> killsField, Spinner<Integer> assistsField, Spinner<Integer> deathsField, Spinner<Double> desiredKDAField) {
        try {
            ensureFileExists();

            Properties props = new Properties();
            try (InputStream input = Files.newInputStream(FILE_PATH)) {
                props.load(input);
            }

            String killsStr = props.getProperty("kills", "0");
            String assistsStr = props.getProperty("assists", "0");
            String deathsStr = props.getProperty("deaths", "1");
            String desiredKdaStr = props.getProperty("desiredKDA", "1.0");

            try {
                killsField.getValueFactory().setValue(Integer.parseInt(killsStr));
            } catch (NumberFormatException ignored) {}
            try {
                assistsField.getValueFactory().setValue(Integer.parseInt(assistsStr));
            } catch (NumberFormatException ignored) {}
            try {
                deathsField.getValueFactory().setValue(Integer.parseInt(deathsStr));
            } catch (NumberFormatException ignored) {}
            try {
                desiredKDAField.getValueFactory().setValue(Double.parseDouble(desiredKdaStr));
            } catch (NumberFormatException ignored) {}


        } catch (IOException e) {
           Reporter.alertErrorReporting("Error",e.getMessage());
        }
    }

    public static void save(Spinner<Integer> killsSpinner, Spinner<Integer> assistsSpinner, Spinner<Integer> deathsSpinner, Spinner<Double> desiredKDASpinner) {
        try {
            ensureFileExists();

            Properties props = new Properties();
            props.setProperty("kills", String.valueOf(killsSpinner.getValue()));
            props.setProperty("assists", String.valueOf(assistsSpinner.getValue()));
            props.setProperty("deaths", String.valueOf(deathsSpinner.getValue()));
            props.setProperty("desiredKDA", String.valueOf(desiredKDASpinner.getValue()));

            try (OutputStream output = Files.newOutputStream(FILE_PATH)) {
                props.store(output, "Hunt KDA Tool Saved Data");

                Reporter.alertConfirmReporting("Success","Data saved successfully.");
            }

        } catch (IOException e) {
            Reporter.alertErrorReporting("Error",e.getMessage());
        }
    }

    private static void ensureFileExists() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            try (InputStream template = SaveLoader.class.getResourceAsStream("/saved_data.properties")) {
                if (template == null) throw new FileNotFoundException("Template saved_data.properties not found in resources.");
                Files.copy(template, FILE_PATH);
            }
        }
    }
}
