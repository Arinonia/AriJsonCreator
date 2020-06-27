package fr.arinonia.arijsoncreator.ui;

import fr.arinonia.arijsoncreator.AriJsonCreator;
import fr.arinonia.arijsoncreator.ui.panel.IPanel;
import fr.arinonia.arijsoncreator.ui.panels.includes.TopPanel;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui
 */
public class PanelManager {

    private final AriJsonCreator ariJsonCreator;
    private final Stage stage;

    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    private double xOffset = 0;
    private double yOffset = 0;

    public PanelManager(AriJsonCreator ariJsonCreator, Stage stage) {
        this.ariJsonCreator = ariJsonCreator;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("AriJsonCreator");
        this.stage.setMinWidth(1280);
        this.stage.setWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setHeight(720);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();
        this.stage.centerOnScreen();
        this.layout = new GridPane();
        this.layout.setStyle("-fx-background-color: rgb(40,40,40);");
        this.stage.setScene(new Scene(this.layout));

        RowConstraints constraints = new RowConstraints();
        constraints.setValignment(VPos.TOP);
        constraints.setMaxHeight(25.0D);
        constraints.setMinHeight(25.0D);

        this.layout.getRowConstraints().addAll(constraints, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(), 0, 0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel, 0, 1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);


        this.layout.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        this.layout.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public void showPanel(IPanel panel){
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    public AriJsonCreator getAriJsonCreator() {
        return ariJsonCreator;
    }
    public Stage getStage() {
        return stage;
    }
    public GridPane getLayout() {
        return centerPanel;
    }
}
