package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.file.Config;
import fr.arinonia.arijsoncreator.file.ConfigJson;
import fr.arinonia.arijsoncreator.ui.Panel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.controls.Alert;
import fr.arinonia.arijsoncreator.ui.controls.JFXLabel;
import fr.arinonia.arijsoncreator.ui.controls.MaterialButton;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.io.File;

public class HomePanel extends Panel {


    public HomePanel(UiManager uiManager) {
        super(uiManager);
    }

    @Override
    public void initPanel() {


        JFXLabel welcome = new JFXLabel("home.welcome.text");
        GridPane.setValignment(welcome, VPos.TOP);
        GridPane.setHalignment(welcome, HPos.CENTER);
        welcome.setTranslateY(50.0D);
        welcome.setFontSize(55.0D);
        this.layout.getChildren().add(welcome);

        JFXLabel message = new JFXLabel("home.message.text");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        message.setTranslateY(150.0D);
        message.setFontSize(30.0D);
        this.layout.getChildren().add(message);

        JFXLabel message2 = new JFXLabel("home.message2.text");
        GridPane.setValignment(message2, VPos.TOP);
        GridPane.setHalignment(message2, HPos.CENTER);
        message2.setTranslateY(188.0D);
        message2.setFontSize(30.0D);
        this.layout.getChildren().add(message2);

        MaterialButton start = new MaterialButton("home.btnStart.text");
        GridPane.setValignment(start, VPos.CENTER);
        GridPane.setHalignment(start, HPos.CENTER);
        start.setSize(240.0D, 50.0D);
        start.setTranslateX(-30.0D);
        start.setOnMouseClicked(e -> this.uiManager.showPanel(this.layout, new CreateConfigPanel(this.uiManager)));
        start.setFontSize(18.0D);
        this.layout.getChildren().add(start);

        JFXLabel copyright = new JFXLabel("home.copyright.text");
        GridPane.setValignment(copyright, VPos.BOTTOM);
        GridPane.setHalignment(copyright, HPos.RIGHT);
        copyright.setStyle("-fx-text-fill: " + Constants.PURPLE);
        copyright.setTranslateX(-5.0D);
        copyright.setFontSize(18.0D);
        this.layout.getChildren().add(copyright);

        MaterialButton settings = new MaterialButton();
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Color.rgb(152, 47, 170));
        GridPane.setValignment(settings, VPos.CENTER);
        GridPane.setHalignment(settings, HPos.CENTER);
        settings.setSize(50.0D, 50.0D);
        settings.setTranslateX(124.0D);
        settings.setGraphic(settingsIcon);
        settings.setOnMouseClicked(e -> this.uiManager.showPanel(this.layout, new SettingsPanel(this.uiManager)));
        this.layout.getChildren().add(settings);

        JFXComboBox<String> comboBox = new JFXComboBox<>();
        GridPane.setHgrow(comboBox, Priority.ALWAYS);
        GridPane.setVgrow(comboBox, Priority.ALWAYS);
        GridPane.setValignment(comboBox, VPos.CENTER);
        GridPane.setHalignment(comboBox, HPos.CENTER);
        comboBox.setMaxSize(300, 50);
        comboBox.setTranslateY(70.0D);
        comboBox.getStylesheets().add(Main.class.getResource("/css/combobox.css").toExternalForm());
        comboBox.setStyle("-jfx-focus-color: "  + Constants.PURPLE + " -jfx-unfocus-color: rgba(0, 0, 0, 0);");
        if (this.uiManager.getAriJsonCreator().getFileManager().getConfigurationFolder().listAllConfig().isEmpty()) {
            comboBox.getItems().add(I18n.LANG.getTranslation("home.emptyconfig.text"));
            comboBox.getSelectionModel().select(0);
        } else {
            String currentConfig = this.uiManager.getAriJsonCreator().getSettings().get("config");

            for (File file : this.uiManager.getAriJsonCreator().getFileManager().getConfigurationFolder().listAllConfig()) {
                String fileName = file.getName();
                int pos = fileName.lastIndexOf(".");

                if (pos > 0 && pos < (fileName.length() - 1)) {
                    fileName = fileName.substring(0, pos);
                }
                comboBox.getItems().add(fileName);
            }
            if (currentConfig.equalsIgnoreCase("null")) {
                comboBox.getSelectionModel().select(0);
            } else {
                int pos = currentConfig.lastIndexOf(".");

                if (pos > 0 && pos < (currentConfig.length() - 1)) {
                    currentConfig = currentConfig.substring(0, pos);
                }
                comboBox.getSelectionModel().select(currentConfig);
            }
        }
        this.layout.getChildren().add(comboBox);

        MaterialButton loadConfig = new MaterialButton("home.btnLoad.text");
        GridPane.setValignment(loadConfig, VPos.CENTER);
        GridPane.setHalignment(loadConfig, HPos.CENTER);
        loadConfig.setSize(250.0D, 50.0D);
        loadConfig.setTranslateY(140.0D);
        loadConfig.setOnMouseClicked(e -> {
            if (comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase(I18n.LANG.getTranslation("home.emptyconfig.text"))) {
                new Alert(uiManager, I18n.LANG.getTranslation("home.alert.noconfig.text"));
            }
            Config config = new Config(this.uiManager.getAriJsonCreator());
            ConfigJson configJson = config.loadConfig(comboBox.getSelectionModel().getSelectedItem());
            this.uiManager.showPanel(this.layout, new CreateConfigPanel(this.uiManager, configJson.getName(), configJson.getUrl(), configJson.getPath(), configJson.getIgnored()));
            this.uiManager.getAriJsonCreator().getSettings().set("config", configJson.getName());
        });
        loadConfig.setFontSize(18.0D);
        this.layout.getChildren().add(loadConfig);
    }

    @Override
    public UiManager getUiManager() {
        return this.uiManager;
    }

    @Override
    public GridPane getLayout() {
        return this.layout;
    }
}
