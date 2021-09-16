package fr.arinonia.arijsoncreator.ui.controls;

import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MaterialSwitchButton extends GridPane {

    private final Rectangle background = new Rectangle();
    private final Rectangle button = new Rectangle();
    private boolean isActive = false;

    public MaterialSwitchButton() {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        this.setCursor(Cursor.HAND);
        this.setMaxSize(58.0D, 28.0D);

        GridPane.setHgrow(background, Priority.ALWAYS);
        GridPane.setVgrow(background, Priority.ALWAYS);
        background.setFill(Color.TRANSPARENT);
        background.setWidth(58.0D);
        background.setHeight(28.0D);
        background.setStroke(Color.web("#40415F"));
        background.setStrokeWidth(1.0D);
        background.setArcHeight(10.0D);
        background.setArcWidth(10.0D);
        this.getChildren().add(background);

        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        button.setWidth(20.0D);
        button.setHeight(23.0D);
        button.setFill(Color.web("#4E4F78"));
        button.setArcHeight(10.0D);
        button.setArcWidth(10.0D);
        button.setTranslateX(5.0D);
        this.getChildren().add(button);

        this.setOnMouseClicked(this::setOnClicked);
    }

    public void setSize(double width, double height) {
        this.background.setWidth(width);
        this.background.setHeight(height);
        this.button.setWidth(width / 2 - 10);
        this.button.setHeight(height - 5);
    }

    public void setOnClicked(MouseEvent value) {
        if (!this.isDisable()) {
            this.isActive = !this.isActive;
            if (this.isActive) {
                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setNode(this.button);
                translateTransition.setDuration(Duration.millis(500.0D));
                translateTransition.setToX(this.background.getWidth() - this.button.getWidth() - 5);
                translateTransition.play();
            } else {
                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setNode(this.button);
                translateTransition.setDuration(Duration.millis(500.0D));
                translateTransition.setToX(5);
                translateTransition.play();
            }
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
        if (this.isActive) {
            this.button.setTranslateX(this.background.getWidth() - this.button.getWidth() - 5);
            System.out.println(this.background.getWidth() - this.button.getWidth() - 5);
        } else {
            this.button.setX(5.0D);
        }
    }

}
