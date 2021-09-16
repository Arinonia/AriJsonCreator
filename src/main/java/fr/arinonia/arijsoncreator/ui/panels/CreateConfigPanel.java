package fr.arinonia.arijsoncreator.ui.panels;

import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.*;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.file.Config;
import fr.arinonia.arijsoncreator.json.DataFile;
import fr.arinonia.arijsoncreator.json.DataMaintenance;
import fr.arinonia.arijsoncreator.ui.Panel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.controls.Alert;
import fr.arinonia.arijsoncreator.ui.controls.IgnoredComboBox;
import fr.arinonia.arijsoncreator.ui.controls.JFXLabel;
import fr.arinonia.arijsoncreator.ui.controls.MaterialButton;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CreateConfigPanel extends Panel {

    private File file;

    public CreateConfigPanel(UiManager uiManager) {
        super(uiManager);
    }

    public CreateConfigPanel(UiManager uiManager, String name, String url, String path, List<String> ignored) {
        super(uiManager);
        this.postInit(name, url, path, ignored);
    }

    @Override
    public void initPanel() {
        JFXLabel configName = new JFXLabel("createconfig.configname.text");
        GridPane.setValignment(configName, VPos.TOP);
        GridPane.setHalignment(configName, HPos.LEFT);
        configName.setTranslateX(50.0D);
        configName.setTranslateY(50.0D);
        configName.setFontSize(20.0D);
        this.layout.getChildren().add(configName);

        JFXTextField configNameField = new JFXTextField();
        GridPane.setValignment(configNameField, VPos.TOP);
        GridPane.setHalignment(configNameField, HPos.LEFT);
        GridPane.setHgrow(configNameField, Priority.ALWAYS);
        GridPane.setVgrow(configNameField, Priority.ALWAYS);
        configNameField.setMaxSize(200, 30);
        configNameField.setTranslateX(130.0D);
        configNameField.setTranslateY(40.0D);
        configNameField.setStyle("-fx-text-fill: #fff; -jfx-focus-color: " + Constants.PURPLE + "-fx-background-color: " + Constants.BACKGROUND_INPUT_FIELD);
        configNameField.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 20));
        configNameField.setId("name");
        this.layout.getChildren().add(configNameField);

        JFXLabel configUrl = new JFXLabel("URL:");
        GridPane.setValignment(configUrl, VPos.TOP);
        GridPane.setHalignment(configUrl, HPos.LEFT);
        configUrl.setTranslateX(50.0D);
        configUrl.setTranslateY(130.0D);
        configUrl.setFontSize(20.0D);
        this.layout.getChildren().add(configUrl);

        JFXTextField configUrlField = new JFXTextField();
        GridPane.setValignment(configUrlField, VPos.TOP);
        GridPane.setHalignment(configUrlField, HPos.LEFT);
        GridPane.setHgrow(configUrlField, Priority.ALWAYS);
        GridPane.setVgrow(configUrlField, Priority.ALWAYS);
        configUrlField.setId("url");
        configUrlField.setMaxSize(250, 30);
        configUrlField.setTranslateX(130.0D);
        configUrlField.setTranslateY(120.0D);
        configUrlField.setStyle("-fx-text-fill: #fff; -jfx-focus-color: " + Constants.PURPLE + "-fx-background-color: " + Constants.BACKGROUND_INPUT_FIELD);
        configUrlField.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 20));
        this.layout.getChildren().add(configUrlField);

        JFXLabel configMaintenance = new JFXLabel("Maintenance:");
        GridPane.setValignment(configMaintenance, VPos.TOP);
        GridPane.setHalignment(configMaintenance, HPos.LEFT);
        configMaintenance.setTranslateX(50.0D);
        configMaintenance.setTranslateY(200.0D);
        configMaintenance.setFontSize(20.0D);
        this.layout.getChildren().add(configMaintenance);

        JFXToggleButton switchButton = new JFXToggleButton();
        GridPane.setValignment(switchButton, VPos.TOP);
        GridPane.setHalignment(switchButton, HPos.LEFT);
        GridPane.setVgrow(switchButton, Priority.ALWAYS);
        GridPane.setHgrow(switchButton, Priority.ALWAYS);
        switchButton.setTranslateX(170.0D);
        switchButton.setTranslateY(180.0D);
        switchButton.setStyle("-jfx-toggle-color: " + Constants.PURPLE + " -fx-text-fill: #fff");
        switchButton.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 20));
        switchButton.setText(I18n.LANG.getTranslation("createconfig.maintenance.disable.text"));
        switchButton.setOnMouseClicked(e -> {
            if (switchButton.isSelected()) {
                switchButton.setText(I18n.LANG.getTranslation("createconfig.maintenance.enable.text"));
            } else {
                switchButton.setText(I18n.LANG.getTranslation("createconfig.maintenance.disable.text"));
            }
        });
        this.layout.getChildren().add(switchButton);

        JFXTextArea maintenanceText = new JFXTextArea();
        GridPane.setValignment(maintenanceText, VPos.CENTER);
        GridPane.setHalignment(maintenanceText, HPos.LEFT);
        GridPane.setVgrow(maintenanceText, Priority.ALWAYS);
        GridPane.setHgrow(maintenanceText, Priority.ALWAYS);
        maintenanceText.setTranslateX(50.0D);
        maintenanceText.setStyle("-fx-padding: 10px");
        maintenanceText.setMaxSize(500.0D, 175.0D);
        maintenanceText.setPromptText(I18n.LANG.getTranslation("createconfig.maintenance.text"));
        maintenanceText.setStyle("-jfx-focus-color: " + Constants.PURPLE + "; -fx-text-fill: #fff; " + "-fx-background-color: " + Constants.BACKGROUND_INPUT_FIELD);
        maintenanceText.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 22));
        this.layout.getChildren().add(maintenanceText);

        MaterialButton choosefolder = new MaterialButton("createconfig.choosefolder.text");
        GridPane.setValignment(choosefolder, VPos.CENTER);
        GridPane.setHalignment(choosefolder, HPos.LEFT);
        choosefolder.setId("choosefolder");
        choosefolder.setSize(240.0D, 50.0D);
        choosefolder.setTranslateX(50.0D);
        choosefolder.setTranslateY(150.0D);
        choosefolder.setOnMouseClicked(e -> this.file = this.createFileChooser());
        choosefolder.setFontSize(18.0D);
        this.layout.getChildren().add(choosefolder);


        JFXLabel configIgnoredFiles = new JFXLabel("createconfig.ignoredlabel.text");
        GridPane.setValignment(configIgnoredFiles, VPos.TOP);
        GridPane.setHalignment(configIgnoredFiles, HPos.CENTER);
        configIgnoredFiles.setTranslateX(50.0D);
        configIgnoredFiles.setTranslateY(50.0D);
        configIgnoredFiles.setFontSize(20.0D);
        this.layout.getChildren().add(configIgnoredFiles);

        JFXTextField configIgnoredField = new JFXTextField();
        GridPane.setValignment(configIgnoredField, VPos.TOP);
        GridPane.setHalignment(configIgnoredField, HPos.CENTER);
        GridPane.setHgrow(configIgnoredField, Priority.ALWAYS);
        GridPane.setVgrow(configIgnoredField, Priority.ALWAYS);
        configIgnoredField.setMaxSize(200, 30);
        configIgnoredField.setTranslateX(290.0D);
        configIgnoredField.setTranslateY(40.0D);
        configIgnoredField.setStyle("-fx-text-fill: #fff; -jfx-focus-color: " + Constants.PURPLE + "-fx-background-color: " + Constants.BACKGROUND_INPUT_FIELD);
        configIgnoredField.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 16));
        this.layout.getChildren().add(configIgnoredField);

        IgnoredComboBox ignoredList = new IgnoredComboBox();
        GridPane.setValignment(ignoredList, VPos.TOP);
        GridPane.setHalignment(ignoredList, HPos.CENTER);
        ignoredList.setMaxSize(300, 50);
        ignoredList.setTranslateX(290.0D);
        ignoredList.setTranslateY(120.0D);

        this.layout.getChildren().add(ignoredList);

        MaterialButton addIgnored = new MaterialButton("createconfig.addignored.text");
        GridPane.setValignment(addIgnored, VPos.TOP);
        GridPane.setHalignment(addIgnored, HPos.RIGHT);
        addIgnored.setTranslateY(40.0D);
        addIgnored.setTranslateX(-20.0D);
        addIgnored.setSize(140.0D, 40.0D);
        addIgnored.setOnMouseClicked(e -> {
            if (configIgnoredField.getText().isEmpty()) {
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyignored.text"));
            } else {
                if (ignoredList.getItems().contains(I18n.LANG.getTranslation("config.emptyignoredlist.text"))) {
                    ignoredList.getItems().remove(I18n.LANG.getTranslation("config.emptyignoredlist.text"));
                }
                ignoredList.getItems().add(configIgnoredField.getText());
                ignoredList.getSelectionModel().select(ignoredList.getItems().size() - 1);
                configIgnoredField.setText("");
            }
        });
        addIgnored.setFontSize(14.0D);
        this.layout.getChildren().add(addIgnored);

        MaterialButton removeIgnored = new MaterialButton("createconfig.removeignored.text");
        GridPane.setValignment(removeIgnored, VPos.TOP);
        GridPane.setHalignment(removeIgnored, HPos.RIGHT);
        removeIgnored.setTranslateY(125.0D);
        removeIgnored.setTranslateX(-20.0D);
        removeIgnored.setSize(140.0D, 40.0D);
        removeIgnored.setOnMouseClicked(e -> {
            if (ignoredList.isEmptyOrContainDefault()) {
               new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyignoredlist.text"));
            } else {
                ignoredList.getItems().remove(ignoredList.getSelectionModel().getSelectedIndex());
                if (ignoredList.isEmptyOrContainDefault()) {
                    ignoredList.getItems().add(I18n.LANG.getTranslation("config.emptyignoredlist.text"));
                    ignoredList.getSelectionModel().select(0);
                }
            }
        });
        removeIgnored.setFontSize(14.0D);
        this.layout.getChildren().add(removeIgnored);

        JFXSpinner spinner = new JFXSpinner();
        GridPane.setHgrow(spinner, Priority.ALWAYS);
        GridPane.setVgrow(spinner, Priority.ALWAYS);
        GridPane.setValignment(spinner, VPos.BOTTOM);
        GridPane.setHalignment(spinner, HPos.CENTER);
        spinner.setTranslateY(-70.0D);
        spinner.setPrefSize(50.0D, 50.0D);
        spinner.setMaxSize(50.0D, 50.0D);
        spinner.setVisible(false);

        this.layout.getChildren().add(spinner);

        MaterialButton start = new MaterialButton("config.start.text");
        GridPane.setValignment(start, VPos.BOTTOM);
        GridPane.setHalignment(start, HPos.CENTER);
        start.setTranslateY(-130.0D);
        start.setSize(240.0D, 50.0D);
        start.setFontSize(18.0D);
        start.setOnMouseClicked(e -> {
            spinner.setVisible(true);
            this.disabledField(true);

            if (configNameField.getText().isEmpty()) {
                spinner.setVisible(false);
                this.disabledField(false);

                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyname.text"));
                return;
            }
            if (configUrlField.getText().isEmpty()) {
                spinner.setVisible(false);
                this.disabledField(false);

                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyurl.text"));
                return;
            }
            /*if (!this.uiManager.getAriJsonCreator().isValidURL(configUrlField.getText())) {
                spinner.setVisible(false);
                this.disabledField(false);
                System.out.println("why: " + configUrlField.getText());
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.badurl.text"));
                return;
            }*/
            if (this.file == null) {
                spinner.setVisible(false);
                this.disabledField(false);

                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.foldernull.text"));
                return;
            }
            DataMaintenance maintenance = new DataMaintenance();
            uiManager.getAriJsonCreator().setUrl(configUrlField.getText());
            uiManager.getAriJsonCreator().getData().setMaintenance(maintenance);
            uiManager.getAriJsonCreator().getData().getMaintenance().setMaintenance(switchButton.isSelected());
            uiManager.getAriJsonCreator().getData().getMaintenance().setMessage(maintenanceText.getText());
            if (!ignoredList.isEmptyOrContainDefault()) {
                for (String str : ignoredList.getItems()) {
                    if (!str.equalsIgnoreCase(I18n.LANG.getTranslation("config.emptyignoredlist.text"))) {
                        uiManager.getAriJsonCreator().getData().getIgnoredFiles().add(str);
                    }
                }
            }

            Thread t = new Thread(() -> {
                uiManager.getAriJsonCreator().listAllFile(file);
                List<DataFile> files = new ArrayList<>();
                for (int i = 0; i < uiManager.getAriJsonCreator().getFilesList().size(); i++) {
                    String path = uiManager.getAriJsonCreator().getFileNameList().get(i).substring(file.getAbsolutePath().length() + 1).replace("\\", "/");
                    files.add(new DataFile(path, uiManager.getAriJsonCreator().getFilesList().get(i),
                            uiManager.getAriJsonCreator().getUrl().endsWith("/") ? uiManager.getAriJsonCreator().getUrl() + path : uiManager.getAriJsonCreator().getUrl() + "/" + path,
                            uiManager.getAriJsonCreator().getSizeFileList().get(i)));
                }
                uiManager.getAriJsonCreator().getData().setFiles(files);
                String json = uiManager.getAriJsonCreator().formatJSON(uiManager.getAriJsonCreator().getJson().toJson(uiManager.getAriJsonCreator().getData()));
                Platform.runLater(() -> uiManager.showPanel(this.layout, new EndPanel(uiManager, json)));
            });
            t.start();
        });
        this.layout.getChildren().add(start);

        MaterialButton saveConfig = new MaterialButton("config.save.text");
        GridPane.setValignment(saveConfig, VPos.BOTTOM);
        GridPane.setHalignment(saveConfig, HPos.CENTER);
        saveConfig.setTranslateY(-130.0D);
        saveConfig.setTranslateX(210.0D);
        saveConfig.setSize(150.0D, 50.0D);
        saveConfig.setFontSize(18.0D);
        saveConfig.setOnMouseClicked(e -> {

            if (configNameField.getText().isEmpty()) {
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyname.text"));
                return;
            }
            if (configUrlField.getText().isEmpty()) {
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.emptyurl.text"));
                return;
            }
            /*if (!this.uiManager.getAriJsonCreator().isValidURL(configUrlField.getText())) {
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.badurl.text"));
                return;
            }*/
            if (this.file == null) {
                new Alert(uiManager, I18n.LANG.getTranslation("config.alert.foldernull.text"));
                return;
            }
            Config config = new Config(configNameField.getText(), configUrlField.getText(), this.file.getAbsolutePath(),
                    !ignoredList.getSelectionModel().getSelectedItem().equalsIgnoreCase(I18n.LANG.getTranslation("config.emptyignoredlist.text")) ? ignoredList.getItems() : Collections.emptyList(), uiManager.getAriJsonCreator());

            if (!config.fileExist()) {
                config.createFile();
            } else {
                config.updateConfig();
            }

            TrayNotification trayNotification = new TrayNotification(Constants.APP_NAME, "save", Notifications.SUCCESS);
            trayNotification.showAndDismiss(new Duration(1000));

        });
        this.layout.getChildren().add(saveConfig);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void postInit(Object... objects) {
         for (Node node : this.layout.getChildren()) {
            if (node instanceof JFXTextField) {
                JFXTextField field = (JFXTextField) node;

                if (field.getId() != null) {
                    if (field.getId().equalsIgnoreCase("name")) {
                        field.setText((String) objects[0]);
                    }
                    if (field.getId().equalsIgnoreCase("url")) {
                        field.setText((String) objects[1]);
                    }
                }

            }
            if (node instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton)node;

                if (materialButton.getId() != null && materialButton.getId().equalsIgnoreCase("choosefolder")) {
                    File folder = new File((String) objects[2]);
                    if (folder.isDirectory()) {
                        materialButton.setText(folder.getName());
                    }
                }
            }

            if (node instanceof IgnoredComboBox) {
                IgnoredComboBox comboBox = (IgnoredComboBox) node;
                Collection<? extends String> list = (Collection<? extends String>) objects[3];
                if (!list.isEmpty()) {
                    comboBox.getItems().addAll(list);
                    if (comboBox.isEmptyOrContainDefault()) {
                        comboBox.getItems().remove(I18n.LANG.getTranslation("config.emptyignoredlist.text"));
                        comboBox.getSelectionModel().select(comboBox.getItems().size() - 1);
                    }
                }

            }
        }

        this.file = new File((String) objects[2]);
    }

    private void disabledField(boolean disabled) {
        for (Node node : this.layout.getChildren()) {
            if (node instanceof MaterialButton) {
                MaterialButton btn = (MaterialButton) node;
                btn.setDisable(disabled);
            }
            if (node instanceof IgnoredComboBox) {
                IgnoredComboBox box = (IgnoredComboBox) node;
                box.setDisable(disabled);
            }
            if (node instanceof JFXTextField) {
                JFXTextField field = (JFXTextField) node;
                field.setDisable(disabled);
                field.setEditable(!disabled);
            }
            if (node instanceof JFXToggleButton) {
                JFXToggleButton toggleBtn = (JFXToggleButton) node;
                toggleBtn.setDisable(disabled);
            }
            if (node instanceof JFXTextArea) {
                JFXTextArea area = (JFXTextArea) node;
                area.setDisable(disabled);
            }
        }
    }

    private File createFileChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(Constants.APP_NAME + " - DirChooser");
        directoryChooser.setInitialDirectory(this.file == null ? FileSystemView.getFileSystemView().getHomeDirectory() : file);
        return directoryChooser.showDialog(null);
    }

}
