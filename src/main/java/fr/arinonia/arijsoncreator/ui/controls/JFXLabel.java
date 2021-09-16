package fr.arinonia.arijsoncreator.ui.controls;

import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class JFXLabel extends Label {

    public JFXLabel(String text) {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        this.setText(I18n.LANG.getTranslation(text));
        this.setStyle("-fx-text-fill: #FFF;");
    }

    public void setFontSize(double size) {
        this.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), size));
    }

}
