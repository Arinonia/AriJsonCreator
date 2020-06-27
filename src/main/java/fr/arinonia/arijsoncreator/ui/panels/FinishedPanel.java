package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

/**
 * Created by Arinonia on 27/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class FinishedPanel extends Panel {

    private final String text;

    public FinishedPanel(String text) {
        this.text = text;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label title = new Label("Copy JSON");
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        title.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),55));
        title.setTranslateY(35);
        title.setStyle("-fx-text-fill: #fff");

        JFXTextArea jfxTextArea = new JFXTextArea();
        GridPane.setValignment(jfxTextArea, VPos.TOP);
        GridPane.setHalignment(jfxTextArea, HPos.CENTER);
        GridPane.setVgrow(jfxTextArea, Priority.ALWAYS);
        GridPane.setHgrow(jfxTextArea, Priority.ALWAYS);
        jfxTextArea.setMaxSize(800,400);
        jfxTextArea.setTranslateY(160.0D);
        jfxTextArea.setStyle("-fx-background-color: #2F3131; -jfx-focus-color: #aa652f; -fx-text-fill: #fff;");
        jfxTextArea.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),16));
        jfxTextArea.setText(text);
        //jfxTextArea.selectAll();

        JFXButton exit = new JFXButton("Exit.");
        GridPane.setValignment(exit, VPos.BOTTOM);
        GridPane.setHalignment(exit, HPos.CENTER);
        GridPane.setHgrow(exit, Priority.ALWAYS);
        GridPane.setVgrow(exit, Priority.ALWAYS);
        exit.setPrefSize(200,50);
        exit.setMinSize(200,50);
        exit.setMaxSize(200,50);
        exit.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        exit.setTranslateY(-60.0D);
        exit.setOnMouseClicked(e->System.exit(0));
        exit.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        exit.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        exit.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

        JFXButton copy = new JFXButton("Select All");
        GridPane.setValignment(copy, VPos.CENTER);
        GridPane.setHalignment(copy, HPos.RIGHT);
        GridPane.setHgrow(copy, Priority.ALWAYS);
        GridPane.setVgrow(copy, Priority.ALWAYS);
        copy.setPrefSize(150,50);
        copy.setMinSize(150,50);
        copy.setMaxSize(200,50);
        copy.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        copy.setTranslateX(-30.0D);
        copy.setOnMouseClicked(e->{
            jfxTextArea.selectAll();
            jfxTextArea.requestFocus();
        });
        copy.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        copy.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        copy.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));
        panelManager.getLayout().getChildren().addAll(title, jfxTextArea, exit, copy);
    }
}
