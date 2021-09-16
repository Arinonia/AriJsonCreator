package fr.arinonia.arijsoncreator.ui.controls;

import com.jfoenix.controls.JFXComboBox;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.utils.Constants;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public abstract class MaterialComboBox extends JFXComboBox<String> {

    public MaterialComboBox() {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        this.getStylesheets().add(Main.class.getResource("/css/combobox.css").toExternalForm());
        this.setStyle("-jfx-focus-color: "  + Constants.PURPLE + " -jfx-unfocus-color: rgba(0, 0, 0, 0);");
    }

}
