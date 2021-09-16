package fr.arinonia.arijsoncreator.ui.controls;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import fr.arinonia.arijsoncreator.utils.Constants;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class MaterialButtonSkin extends ButtonSkin {

    public MaterialButtonSkin(Button button) {
        super(button);
        button.setOnMouseEntered(e -> button.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: " + Constants.PURPLE + " -fx-background-color: rgba(255, 255, 255, 0.15)"));
        button.setOnMouseExited(e -> button.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: " + Constants.PURPLE + " -fx-background-color: rgba(255, 255, 255, 0.0)"));
    }
}
