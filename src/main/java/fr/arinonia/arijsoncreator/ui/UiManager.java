package fr.arinonia.arijsoncreator.ui;

import fr.arinonia.arijsoncreator.AriJsonCreator;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.utils.Constants;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UiManager {

    private final AriJsonCreator ariJsonCreator;
    private Stage stage;
    private GridPane layout;

    public UiManager(final AriJsonCreator ariJsonCreator) {
        this.ariJsonCreator = ariJsonCreator;
    }

    public void createFrame(final Stage stage) {
        this.stage = stage;
        this.stage.setTitle(Constants.APP_NAME);
        this.stage.setMinWidth(1280.0D);
        this.stage.setWidth(1280.0D);
        this.stage.setMinHeight(720.0D);
        this.stage.setHeight(720.0D);
        this.stage.centerOnScreen();
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.getIcons().add(new Image(Main.class.getResource("/images/icon.png").toExternalForm()));
        this.stage.setOnCloseRequest(e -> System.exit(0));
        this.stage.setScene(new Scene(this.layout = new GridPane()));
        this.stage.show();
    }

    public void showPanel(final GridPane layout, final IPanel panel) {
        layout.getChildren().clear();
        layout.getChildren().remove(panel.getLayout());
        layout.getChildren().add(panel.getLayout());
    }


    public AriJsonCreator getAriJsonCreator() {
        return this.ariJsonCreator;
    }

    public Stage getStage() {
        return this.stage;
    }

    public GridPane getLayout() {
        return this.layout;
    }
}
