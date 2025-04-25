package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.utils;

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


    public static void load(TextField killsField, TextField assistsField, TextField deathsField, TextField desiredKDAField) {
        try {
            ensureFileExists();

            Properties props = new Properties();
            try (InputStream input = Files.newInputStream(FILE_PATH)) {
                props.load(input);
            }

            killsField.setText(props.getProperty("kills", ""));
            assistsField.setText(props.getProperty("assists", ""));
            deathsField.setText(props.getProperty("deaths", ""));
            desiredKDAField.setText(props.getProperty("desiredKDA", ""));

        } catch (IOException e) {
           Reporter.alertErrorReporting("Error",e.getMessage());
        }
    }

    public static void save(String kills, String assists, String deaths, String desiredKDA) {
        try {
            ensureFileExists();

            Properties props = new Properties();
            props.setProperty("kills", kills);
            props.setProperty("assists", assists);
            props.setProperty("deaths", deaths);
            props.setProperty("desiredKDA", desiredKDA);

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
