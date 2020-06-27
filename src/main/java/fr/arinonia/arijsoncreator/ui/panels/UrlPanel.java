package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class UrlPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label title = new Label("URL");
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        title.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),55));
        title.setTranslateY(35);
        title.setStyle("-fx-text-fill: #fff");

        Label message = new Label("Enter your url here.");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setVgrow(message, Priority.ALWAYS);
        GridPane.setHgrow(message, Priority.ALWAYS);
        message.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),42));
        message.setTranslateY(110);
        message.setStyle("-fx-text-fill: #fff");

        JFXTextField jfxTextField = new JFXTextField();
        GridPane.setValignment(jfxTextField, VPos.CENTER);
        GridPane.setHalignment(jfxTextField, HPos.CENTER);
        GridPane.setVgrow(jfxTextField, Priority.ALWAYS);
        GridPane.setHgrow(jfxTextField, Priority.ALWAYS);
        jfxTextField.setMaxSize(350,60);
        jfxTextField.setPrefSize(350,60);
        jfxTextField.setStyle("-fx-background-color: #2F3131; -jfx-focus-color: #aa652f; -fx-text-fill: #fff;");
        jfxTextField.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),22));
        jfxTextField.setTranslateY(-120);

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
            if (jfxTextField.getText() != null && !jfxTextField.getText().equalsIgnoreCase("") && jfxTextField.getText().startsWith("http")){
                panelManager.getAriJsonCreator().setUrl(jfxTextField.getText());
                System.out.println("Message: " + panelManager.getAriJsonCreator().getData().getMaintenance().getMessage());
                System.out.println("Is in maintenance: " + panelManager.getAriJsonCreator().getData().getMaintenance().isMaintenance());
                System.out.println("Url: "+ panelManager.getAriJsonCreator().getUrl());
                panelManager.showPanel(new SelectFolderPanel());
            }else{
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setBody(new Label("You can't skip this step ! You need to enter URL."));
                JFXAlert<Void> alert = new JFXAlert<>(panelManager.getStage());
                alert.setOverlayClose(true);
                alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
                alert.setContent(layout);
                alert.initModality(Modality.NONE);
                alert.getDialogPane().setStyle("-fx-background-color: rgba(12,12,12,0.3)");
                layout.setStyle("-fx-background-color: #333; -fx-border-color: #AA2523");
                alert.show();
            }
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
            panelManager.getAriJsonCreator().setUrl("");
            panelManager.getAriJsonCreator().getData().getMaintenance().setMessage("");
            panelManager.getAriJsonCreator().getData().setMaintenance(null);
            panelManager.showPanel(new MaintenancePanel());
        });

        panelManager.getLayout().getChildren().addAll(title, message, jfxTextField, next, back);
    }
}
