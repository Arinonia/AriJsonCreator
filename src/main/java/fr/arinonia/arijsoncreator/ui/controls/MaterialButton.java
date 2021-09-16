package fr.arinonia.arijsoncreator.ui.controls;

import com.jfoenix.controls.JFXButton;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class MaterialButton extends JFXButton {

    public MaterialButton(String text) {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        this.setText(!text.equals("") ? I18n.LANG.getTranslation(text) : "");
        this.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: " + Constants.PURPLE);
        this.setSkin(new MaterialButtonSkin(this));
        this.setCursor(Cursor.HAND);
    }
    public MaterialButton() {
        this("");
    }


    public void setSize(double width, double height) {
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
    }

    public void setFontSize(double size) {
        this.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), size));
    }
}
