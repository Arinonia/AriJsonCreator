package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXToolbar;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.json.DataMaintenance;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class MaintenancePanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label title = new Label("Maintenance");
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        title.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),55));
        title.setTranslateY(50);
        title.setStyle("-fx-text-fill: #fff");
        Label message = new Label("Put your message on area and enable\n                   or disable with button.");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setVgrow(message, Priority.ALWAYS);
        GridPane.setHgrow(message, Priority.ALWAYS);
        message.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),42));
        message.setTranslateY(150);
        message.setStyle("-fx-text-fill: #fff");

        JFXTextArea jfxTextArea = new JFXTextArea();
        GridPane.setValignment(jfxTextArea, VPos.CENTER);
        GridPane.setHalignment(jfxTextArea, HPos.CENTER);
        GridPane.setVgrow(jfxTextArea, Priority.ALWAYS);
        GridPane.setHgrow(jfxTextArea, Priority.ALWAYS);
        jfxTextArea.setMaxSize(565,176);
        jfxTextArea.setTranslateY(20.0D);
        jfxTextArea.setStyle("-fx-background-color: #2F3131; -jfx-focus-color: #aa652f; -fx-text-fill: #fff;");
        jfxTextArea.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),22));

        JFXToggleButton switchButton = new JFXToggleButton ();
        GridPane.setValignment(switchButton, VPos.CENTER);
        GridPane.setHalignment(switchButton, HPos.CENTER);
        GridPane.setVgrow(switchButton, Priority.ALWAYS);
        GridPane.setHgrow(switchButton, Priority.ALWAYS);
        switchButton.setStyle("-jfx-toggle-color: #aa652f; -fx-text-fill: #fff");
        switchButton.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),22));
        switchButton.setText("Disable");
        switchButton.setOnMouseClicked(e->{
            if (!switchButton.isSelected()){
                switchButton.setText("Disable");
            }else{
                switchButton.setText("Enable");
            }
        });
        switchButton.setTranslateY(150.0D);
        JFXButton next = new JFXButton("Next");
        GridPane.setValignment(next, VPos.BOTTOM);
        GridPane.setHalignment(next, HPos.CENTER);
        GridPane.setHgrow(next, Priority.ALWAYS);
        GridPane.setVgrow(next, Priority.ALWAYS);
        next.setPrefSize(200,50);
        next.setMinSize(200,50);
        next.setMaxSize(200,50);
        next.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        next.setOnMouseClicked(e->{
            DataMaintenance maintenance = new DataMaintenance();
            maintenance.setMessage(jfxTextArea.getText());
            maintenance.setMaintenance(switchButton.isSelected());
            panelManager.getAriJsonCreator().getData().setMaintenance(maintenance);
            panelManager.showPanel(new UrlPanel());
        });
        next.setTranslateY(-90.0D);
        next.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        next.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        next.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

        MaterialDesignIconView backIcon = new MaterialDesignIconView(MaterialDesignIcon.ARROW_LEFT);
        backIcon.setSize("80px");
        backIcon.setFill(Color.WHITE);
        Button back = new Button();
        GridPane.setValignment(back, VPos.CENTER);
        GridPane.setHalignment(back, HPos.LEFT);
        GridPane.setHgrow(back, Priority.ALWAYS);
        GridPane.setVgrow(back, Priority.ALWAYS);
        back.setGraphic(backIcon);
        back.setTranslateY(-20.0D);
        back.setStyle("-fx-background-color: rgba(0,0,0,0.0); -fx-border-color: #aa652f");
        back.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        back.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        back.setMaxSize(50,50);
        back.setOnMouseClicked(e->{
            panelManager.showPanel(new HomePanel());
        });
        panelManager.getLayout().getChildren().addAll(title, message, jfxTextArea, switchButton, next, back);
    }
}
