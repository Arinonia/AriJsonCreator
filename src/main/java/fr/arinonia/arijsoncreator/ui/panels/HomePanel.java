package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class HomePanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label welcome = new Label("Welcome on AriJsonCreator");
        GridPane.setValignment(welcome, VPos.TOP);
        GridPane.setHalignment(welcome, HPos.CENTER);
        GridPane.setHgrow(welcome, Priority.ALWAYS);
        GridPane.setVgrow(welcome, Priority.ALWAYS);
        welcome.setTranslateY(50.0D);
        welcome.setStyle("-fx-text-fill: #fff");
        welcome.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(), 55));
        Label message = new Label("In this software you will be able to create a JSON to use my updater.\n" +
                                        "            This JSON will contain: path, url, md5 and size of file.");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setHgrow(message, Priority.ALWAYS);
        GridPane.setVgrow(message, Priority.ALWAYS);
        message.setTranslateY(150.0D);
        message.setStyle("-fx-text-fill: #fff");
        message.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(), 30));
        JFXButton start = new JFXButton("Start now !");
        GridPane.setValignment(start, VPos.CENTER);
        GridPane.setHalignment(start, HPos.CENTER);
        GridPane.setHgrow(start, Priority.ALWAYS);
        GridPane.setVgrow(start, Priority.ALWAYS);
        start.setPrefSize(200,50);
        start.setMinSize(200,50);
        start.setMaxSize(200,50);
        start.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        start.setOnMouseClicked(e->{
            panelManager.showPanel(new MaintenancePanel());
        });
        start.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        start.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        start.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));
        Label copyright = new Label("© Developed by Arinonia");
        GridPane.setValignment(copyright, VPos.BOTTOM);
        GridPane.setHalignment(copyright, HPos.RIGHT);
        GridPane.setHgrow(copyright, Priority.ALWAYS);
        GridPane.setVgrow(copyright, Priority.ALWAYS);
        copyright.setStyle("-fx-text-fill: #aa652f");
        copyright.setTranslateX(-5.0D);
        copyright.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));
        JFXButton settings = new JFXButton();
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Color.rgb(170,101,47));
        GridPane.setValignment(settings, VPos.CENTER);
        GridPane.setHalignment(settings, HPos.CENTER);
        GridPane.setHgrow(settings, Priority.ALWAYS);
        GridPane.setVgrow(settings, Priority.ALWAYS);
        settings.setPrefSize(50,50);
        settings.setMinSize(50,50);
        settings.setMaxSize(50,50);
        settings.setTranslateX(140.0D);
        settings.setStyle("-fx-padding: 0.7em 0.57em; -fx-font-size: 18px; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        settings.setGraphic(settingsIcon);
        settings.setOnMouseClicked(e->{
            SettingsPanel settingsPanel = new SettingsPanel();
            RowConstraints constraints = new RowConstraints();
            constraints.setValignment(VPos.CENTER);
            constraints.setMaxHeight(300.0D);
            constraints.setMinHeight(300.0D);

            this.layout.getRowConstraints().addAll(constraints, new RowConstraints());
            this.layout.add(settingsPanel.getLayout(), 0, 1);
            settingsPanel.init(panelManager);
        });
        settings.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        settings.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));

        MaterialDesignIconView moneyIcon = new MaterialDesignIconView(MaterialDesignIcon.CASH_MULTIPLE);
        moneyIcon.setSize("18px");
        moneyIcon.setFill(Color.rgb(170,101,47));
        JFXButton money = new JFXButton();
        GridPane.setValignment(money, VPos.BOTTOM);
        GridPane.setHalignment(money, HPos.LEFT);
        GridPane.setHgrow(money, Priority.ALWAYS);
        GridPane.setVgrow(money, Priority.ALWAYS);
        money.setPrefSize(50,50);
        money.setMinSize(50,50);
        money.setMaxSize(50,50);
        money.setStyle("-fx-padding: 0.7em 0.57em; -fx-font-size: 18px; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        money.setGraphic(moneyIcon);
        money.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        money.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        money.setOnMouseClicked(e->{
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("I'm sorry but for the moment this button is disabled :/ if you want help me go on paypal: neverwar.yt@gmail.com"));
            JFXAlert<Void> alert = new JFXAlert<>(panelManager.getStage());
            alert.setOverlayClose(true);
            alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
            alert.setContent(layout);
            alert.initModality(Modality.NONE);
            alert.getDialogPane().setStyle("-fx-background-color: rgba(12,12,12,0.3)");
            layout.setStyle("-fx-background-color: #333; -fx-border-color: #aa652f");
            alert.show();
        });
        Label support = new Label("Support me !");
        GridPane.setValignment(support, VPos.BOTTOM);
        GridPane.setHalignment(support, HPos.LEFT);
        GridPane.setHgrow(support, Priority.ALWAYS);
        GridPane.setVgrow(support, Priority.ALWAYS);
        support.setTranslateX(54.0D);
        support.setTranslateY(-14.0D);
        support.setStyle("-fx-text-fill: #aa652f");
        support.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),16));

        this.panelManager.getLayout().getChildren().addAll(welcome, message, start, copyright, settings, money, support);
    }
}
