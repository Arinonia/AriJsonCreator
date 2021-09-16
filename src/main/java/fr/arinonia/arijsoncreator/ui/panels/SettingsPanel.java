package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXComboBox;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.IPanel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.controls.JFXLabel;
import fr.arinonia.arijsoncreator.ui.controls.MaterialButton;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Locale;

public class SettingsPanel implements IPanel {

    private final UiManager uiManager;
    private final GridPane layout;

    public SettingsPanel(UiManager uiManager) {
        this.uiManager = uiManager;
        this.layout = new GridPane();

        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);

        this.layout.setStyle("-fx-background-color: #202029;");

        JFXLabel selectLang = new JFXLabel("settings.selectLang.text");
        GridPane.setValignment(selectLang, VPos.CENTER);
        GridPane.setHalignment(selectLang, HPos.CENTER);
        selectLang.setTranslateY(-50.0D);
        selectLang.setFontSize(18.0D);
        this.layout.getChildren().add(selectLang);

        JFXComboBox<String> comboBox = new JFXComboBox<>();
        GridPane.setHgrow(comboBox, Priority.ALWAYS);
        GridPane.setVgrow(comboBox, Priority.ALWAYS);
        GridPane.setValignment(comboBox, VPos.CENTER);
        GridPane.setHalignment(comboBox, HPos.CENTER);
        comboBox.setMaxSize(200, 50);
        comboBox.getItems().add("English");
        comboBox.getItems().add("French");
        comboBox.getStylesheets().add(Main.class.getResource("/css/combobox.css").toExternalForm());
        comboBox.setStyle("-jfx-focus-color: " + Constants.PURPLE +" -jfx-unfocus-color: rgba(0, 0, 0, 0);");
        comboBox.getSelectionModel().select(I18n.LANG.getSelectedLanguage().getDisplayLanguage(Locale.ENGLISH));
        comboBox.valueProperty().addListener(((observable, oldValue, newValue) -> {
            uiManager.getAriJsonCreator().getSettings().set("lang", newValue);
            I18n.LANG.setSelectedLanguage(Locale.forLanguageTag(newValue.substring(0, 2)));
            uiManager.showPanel(this.layout, new HomePanel(uiManager));
        }));
        this.layout.getChildren().add(comboBox);

        MaterialButton back = new MaterialButton("settings.btnBack.text");
        GridPane.setValignment(back, VPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        back.setSize(200.0D, 50.0D);
        back.setTranslateY(100.0D);
        back.setOnMouseClicked(e -> uiManager.showPanel(this.layout, new HomePanel(uiManager)));
        back.setFontSize(18.0D);
        this.layout.getChildren().add(back);
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
