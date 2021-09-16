package fr.arinonia.arijsoncreator.ui.includes;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.IPanel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.utils.Constants;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TopPanel implements IPanel {

    private final UiManager uiManager;
    private final GridPane layout;

    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public TopPanel(UiManager uiManager) {
        this.uiManager = uiManager;
        this.layout = new GridPane();

        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);

        this.layout.setOnMousePressed(e -> {
            this.xOffset = this.uiManager.getStage().getX() - e.getScreenX();
            this.yOffset = this.uiManager.getStage().getY() - e.getScreenY();
        });

        this.layout.setOnMouseDragged(e -> {
            this.uiManager.getStage().setX(e.getScreenX() + this.xOffset);
            this.uiManager.getStage().setY(e.getScreenY() + this.yOffset);
        });

        this.layout.setStyle("-fx-background-color: #1e1e1e");


        Label title = new Label(Constants.APP_NAME + " " + Constants.APP_VERSION);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.CENTER);
        title.setStyle("-fx-text-fill: #fff;");
        title.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 18));
        this.layout.getChildren().add(title);

        GridPane iconButton = new GridPane();
        GridPane.setHgrow(iconButton, Priority.ALWAYS);
        GridPane.setVgrow(iconButton, Priority.ALWAYS);
        GridPane.setHalignment(iconButton, HPos.RIGHT);
        iconButton.setMaxWidth(150.0D);
        this.layout.getChildren().add(iconButton);
        this.setupTopPanel(iconButton);
    }

    private void setupTopPanel(GridPane layout) {
        MaterialDesignIconView closeIcon = new MaterialDesignIconView(MaterialDesignIcon.CLOSE);
        GridPane.setVgrow(closeIcon, Priority.ALWAYS);
        GridPane.setHgrow(closeIcon, Priority.ALWAYS);
        GridPane.setHalignment(closeIcon, HPos.RIGHT);
        GridPane.setValignment(closeIcon, VPos.CENTER);
        closeIcon.setStyle("-fx-font-size: 22px");
        closeIcon.setFill(Color.rgb(200, 200, 200));
        closeIcon.setTranslateX(-5.0D);
        closeIcon.setOnMouseEntered(e -> {
            closeIcon.setFill(Color.rgb(255, 255, 255));
            layout.setCursor(Cursor.HAND);
        });
        closeIcon.setOnMouseExited(e -> {
            closeIcon.setFill(Color.rgb(200, 200, 200));
            layout.setCursor(Cursor.DEFAULT);
        });
        closeIcon.setOnMouseClicked(e -> System.exit(0));
        layout.getChildren().add(closeIcon);

        MaterialDesignIconView hideIcon = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);
        GridPane.setVgrow(hideIcon, Priority.ALWAYS);
        GridPane.setHgrow(hideIcon, Priority.ALWAYS);
        GridPane.setHalignment(hideIcon, HPos.RIGHT);
        GridPane.setValignment(hideIcon, VPos.CENTER);
        hideIcon.setStyle("-fx-font-size: 22px");
        hideIcon.setFill(Color.rgb(200, 200, 200));
        hideIcon.setTranslateX(-35.0D);
        hideIcon.setOnMouseEntered(e -> {
            hideIcon.setFill(Color.rgb(255, 255, 255));
            layout.setCursor(Cursor.HAND);
        });
        hideIcon.setOnMouseExited(e -> {
            hideIcon.setFill(Color.rgb(200, 200, 200));
            layout.setCursor(Cursor.DEFAULT);
        });
        hideIcon.setOnMouseClicked(e -> uiManager.getStage().setIconified(true));
        layout.getChildren().add(hideIcon);
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
