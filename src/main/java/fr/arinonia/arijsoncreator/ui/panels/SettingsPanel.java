package fr.arinonia.arijsoncreator.ui.panels;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class SettingsPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        GridPane layout = new GridPane();
        GridPane.setVgrow(layout, Priority.ALWAYS);
        GridPane.setHgrow(layout, Priority.ALWAYS);
        layout.setTranslateY(1);
        layout.setStyle("-fx-background-color: rgba(12,12,12,0.4)");

        GridPane settingsPane = new GridPane();
        GridPane.setValignment(settingsPane, VPos.CENTER);
        GridPane.setHalignment(settingsPane, HPos.CENTER);
        GridPane.setVgrow(settingsPane, Priority.ALWAYS);
        GridPane.setHgrow(settingsPane, Priority.ALWAYS);
        settingsPane.setMaxHeight(500.0D);
        settingsPane.setMaxWidth(500.0D);
        settingsPane.setStyle("-fx-background-color: #000");
        this.panelManager.getLayout().getChildren().addAll(layout, settingsPane);
        this.displaySettingsFrame(settingsPane);
    }

    private void displaySettingsFrame(GridPane pane){
        Rectangle topPanel = new Rectangle();
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setValignment(topPanel, VPos.TOP);
        topPanel.setHeight(26);
        topPanel.setWidth(500);
        topPanel.setFill(Color.rgb(31,35,37));

        MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        GridPane.setValignment(close, VPos.TOP);
        GridPane.setHalignment(close, HPos.RIGHT);
        close.setTranslateX(-5.0D);
        close.setFill(Color.WHITE);
        close.setOpacity(0.70f);
        close.setX(80);
        close.setSize("18.0px");
        close.setTranslateY(3.0D);
        close.setOnMouseEntered(e-> close.setOpacity(1.0f));
        close.setOnMouseExited(e-> close.setOpacity(0.70f));
        close.setOnMouseClicked(e-> panelManager.showPanel(new HomePanel()));

        Label label = new Label("This panel is disabled for the moment");
        label.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

        GridPane.setVgrow(label, Priority.ALWAYS);
        GridPane.setHgrow(label, Priority.ALWAYS);
        GridPane.setValignment(label, VPos.TOP);
        GridPane.setHalignment(label, HPos.CENTER);
        label.setTranslateY(50);
        label.setStyle("-fx-text-fill: #fff");

        MaterialDesignIconView sad = new MaterialDesignIconView(MaterialDesignIcon.EMOTICON_SAD);
        GridPane.setVgrow(sad, Priority.ALWAYS);
        GridPane.setHgrow(sad, Priority.ALWAYS);
        GridPane.setValignment(sad, VPos.CENTER);
        GridPane.setHalignment(sad, HPos.CENTER);
        sad.setSize("155px");
        sad.setFill(Color.WHITE);

        pane.getChildren().addAll(topPanel, close, label, sad);
    }
}
