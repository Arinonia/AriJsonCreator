package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panel.Panel;
import javafx.application.Platform;
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
 * Created by Arinonia on 27/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class SelectIgnoredPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label title = new Label("Select Ignored");
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        title.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),55));
        title.setTranslateY(35);
        title.setStyle("-fx-text-fill: #fff");

        Label message = new Label("Select the ignored file(s) or folder(s) who not deleted by updater");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setHgrow(message, Priority.ALWAYS);
        GridPane.setVgrow(message, Priority.ALWAYS);
        message.setTranslateY(150.0D);
        message.setStyle("-fx-text-fill: #fff");
        message.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(), 30));

        JFXTextField jfxTextField = new JFXTextField();
        GridPane.setValignment(jfxTextField, VPos.CENTER);
        GridPane.setHalignment(jfxTextField, HPos.CENTER);
        GridPane.setVgrow(jfxTextField, Priority.ALWAYS);
        GridPane.setHgrow(jfxTextField, Priority.ALWAYS);
        jfxTextField.setMaxSize(300,60);
        jfxTextField.setPrefSize(300,60);
        jfxTextField.setStyle("-fx-background-color: #2F3131; -jfx-focus-color: #aa652f; -fx-text-fill: #fff;");
        jfxTextField.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),22));
        jfxTextField.setTranslateY(-90);

        JFXListView jfxListView = new JFXListView();
        GridPane.setValignment(jfxListView, VPos.CENTER);
        GridPane.setHalignment(jfxListView, HPos.RIGHT);
        GridPane.setVgrow(jfxListView, Priority.ALWAYS);
        GridPane.setHgrow(jfxListView, Priority.ALWAYS);
        jfxListView.setMaxSize(200,250);
        jfxListView.setPrefSize(200,250);
        jfxListView.setTranslateX(-40);
        jfxListView.setStyle("-fx-background-color: #2F3131; -fx-inner-background: #2F3131; -fx-font-size: 20px; -fx-border-color: #aa652f");

        JFXButton add = new JFXButton("Add");
        GridPane.setValignment(add, VPos.CENTER);
        GridPane.setHalignment(add, HPos.CENTER);
        GridPane.setHgrow(add, Priority.ALWAYS);
        GridPane.setVgrow(add, Priority.ALWAYS);
        add.setPrefSize(200,50);
        add.setMinSize(200,50);
        add.setMaxSize(200,50);
        add.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        add.setOnMouseClicked(e->{
            if (jfxTextField.getText() != null && !jfxTextField.getText().equalsIgnoreCase("")){
                panelManager.getAriJsonCreator().getData().getIgnoredFiles().add(jfxTextField.getText());
                jfxTextField.setText("");
                jfxListView.getItems().clear();
                jfxListView.getItems().addAll(panelManager.getAriJsonCreator().getData().getIgnoredFiles());
            }else{
                JFXDialogLayout layout = new JFXDialogLayout();
                layout.setBody(new Label("You can't add null file | folder."));
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
        add.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        add.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        add.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

        JFXButton delete = new JFXButton("Delete");
        GridPane.setValignment(delete, VPos.CENTER);
        GridPane.setHalignment(delete, HPos.RIGHT);
        GridPane.setHgrow(delete, Priority.ALWAYS);
        GridPane.setVgrow(delete, Priority.ALWAYS);
        delete.setPrefSize(200,50);
        delete.setMinSize(200,50);
        delete.setMaxSize(200,50);
        delete.setTranslateX(-280);
        delete.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        delete.setOnMouseClicked(e->{
            if(jfxListView.getSelectionModel().getSelectedItem() == null) return;
            panelManager.getAriJsonCreator().getData().getIgnoredFiles().remove(jfxListView.getSelectionModel().getSelectedItem());
            jfxListView.getItems().remove(jfxListView.getSelectionModel().getSelectedItem());
        });
        delete.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        delete.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        delete.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

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
            panelManager.getAriJsonCreator().getData().getIgnoredFiles().clear();
            panelManager.getAriJsonCreator().getSizeFileList().clear();
            panelManager.getAriJsonCreator().getFileNameList().clear();
            panelManager.getAriJsonCreator().getFilesList().clear();
            panelManager.showPanel(new SelectFolderPanel());
        });

        JFXSpinner spinner = new JFXSpinner();
        GridPane.setValignment(spinner, VPos.CENTER);
        GridPane.setHalignment(spinner, HPos.CENTER);
        GridPane.setHgrow(spinner, Priority.ALWAYS);
        GridPane.setVgrow(spinner, Priority.ALWAYS);
        spinner.setPrefSize(50,50);
        spinner.setMaxSize(50,50);
        spinner.setTranslateY(70.0D);

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
            Thread t = new Thread(){
                @Override
                public void run() {
                    jfxTextField.setDisable(true);
                    back.setDisable(true);
                    next.setDisable(true);
                    add.setDisable(true);
                    String result = formatJSONStr(panelManager.getAriJsonCreator().getJSON().toJson(panelManager.getAriJsonCreator().getData()), 2);
                    Platform.runLater(() -> panelManager.showPanel(new FinishedPanel(result)));
                }
            };
            t.start();
            panelManager.getLayout().getChildren().add(spinner);
        });
        next.setTranslateY(-90.0D);
        next.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        next.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        next.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),18));

        panelManager.getLayout().getChildren().addAll(title, message, jfxTextField, jfxListView, add, delete, back, next);
    }

    private String formatJSONStr(final String json_str, final int indent_width) {
        final char[] chars = json_str.toCharArray();
        final String newline = System.lineSeparator();

        String ret = "";
        boolean begin_quotes = false;

        for (int i = 0, indent = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '\"') {
                ret += c;
                begin_quotes = !begin_quotes;
                continue;
            }

            if (!begin_quotes) {
                switch (c) {
                    case '{':
                    case '[':
                        ret += c + newline + String.format("%" + (indent += indent_width) + "s", "");
                        continue;
                    case '}':
                    case ']':
                        ret += newline + ((indent -= indent_width) > 0 ? String.format("%" + indent + "s", "") : "") + c;
                        continue;
                    case ':':
                        ret += c + " ";
                        continue;
                    case ',':
                        ret += c + newline + (indent > 0 ? String.format("%" + indent + "s", "") : "");
                        continue;
                    default:
                        if (Character.isWhitespace(c)) continue;
                }
            }

            ret += c + (c == '\\' ? "" + chars[++i] : "");
        }

        return ret;
    }
}
