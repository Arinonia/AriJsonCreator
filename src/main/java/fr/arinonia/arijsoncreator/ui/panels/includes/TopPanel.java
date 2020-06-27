package fr.arinonia.arijsoncreator.ui.panels.includes;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels.includes
 */
public class TopPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        GridPane topBarButton = new GridPane();

        this.layout.setStyle("-fx-background-color: rgb(31,35,37)");
        this.layout.getChildren().add(topBarButton);

        GridPane.setHgrow(topBarButton, Priority.ALWAYS);
        GridPane.setVgrow(topBarButton, Priority.ALWAYS);
        Label title = new Label("AriJsonCreator");
        this.layout.getChildren().add(title);
        title.setStyle("-fx-text-fill: white");
        title.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, 22.0f));
        title.setTranslateX(10);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(topBarButton, HPos.RIGHT);
        topBarButton.setMaxWidth(100.0d);
        topBarButton.setMinWidth(100.0d);
        MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        MaterialDesignIconView hide = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);

        GridPane.setVgrow(close, Priority.ALWAYS);
        GridPane.setVgrow(hide, Priority.ALWAYS);

        close.setFill(Color.WHITE);
        close.setOpacity(0.70f);
        close.setSize("18.0px");
        close.setOnMouseEntered(e-> close.setOpacity(1.0f));
        close.setOnMouseExited(e-> close.setOpacity(0.70f));
        close.setOnMouseClicked(e-> System.exit(0));
        close.setTranslateX(70.0D);

        hide.setFill(Color.WHITE);
        hide.setOpacity(0.70f);
        hide.setSize("18.0px");
        hide.setOnMouseEntered(e-> hide.setOpacity(1.0f));
        hide.setOnMouseExited(e-> hide.setOpacity(0.70f));
        hide.setOnMouseClicked(e-> this.panelManager.getStage().setIconified(true));
        hide.setTranslateX(50.0D);

        topBarButton.getChildren().addAll(close, hide);
    }

}
