package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXTextArea;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.Panel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.controls.JFXLabel;
import fr.arinonia.arijsoncreator.ui.controls.MaterialButton;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndPanel extends Panel {

    private final String json;

    public EndPanel(UiManager uiManager, String json) {
        super(uiManager);
        this.json = json;
        this.postInit();
    }

    @Override
    public void initPanel() {
        JFXLabel title = new JFXLabel(I18n.LANG.getTranslation("end.title.text"));
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        title.setFontSize(55.0D);
        title.setTranslateY(35.0D);
        this.layout.getChildren().add(title);

        JFXTextArea area = new JFXTextArea();
        GridPane.setVgrow(area, Priority.ALWAYS);
        GridPane.setHgrow(area, Priority.ALWAYS);
        GridPane.setValignment(area, VPos.TOP);
        GridPane.setHalignment(area, HPos.CENTER);
        area.setMaxSize(800.0D, 400.0D);
        area.setTranslateY(140.0D);
        area.setStyle("-fx-background-color: #2F3131; -jfx-focus-color: " + Constants.PURPLE + "; -fx-text-fill: #fff;");
        area.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 16.0D));
        area.getStylesheets().add(Main.class.getResource("/css/bar.css").toExternalForm());
        this.layout.getChildren().add(area);

        MaterialButton exit = new MaterialButton("end.btnexit.text");
        GridPane.setValignment(exit, VPos.BOTTOM);
        GridPane.setHalignment(exit, HPos.CENTER);
        exit.setSize(200.0D, 50.0D);
        exit.setTranslateY(-60.0D);
        exit.setOnMouseClicked(e -> System.exit(0));
        exit.setFontSize(18.0D);
        this.layout.getChildren().add(exit);

        MaterialButton save = new MaterialButton("end.btnsave.text");
        GridPane.setValignment(save, VPos.CENTER);
        GridPane.setHalignment(save, HPos.RIGHT);
        save.setSize(150.0D, 50.0D);
        save.setTranslateX(-30.0D);
        save.setOnMouseClicked(e -> System.exit(0));
        save.setFontSize(18.0D);
        save.setOnMouseClicked(e -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle(Constants.APP_NAME + " - DirChooser");
            chooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
            File dir = chooser.showDialog(null);
            File jsonFile = new File(dir.getAbsolutePath() + File.separator + "instance.json");
            try {
                jsonFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                FileWriter fileWriter = new FileWriter(jsonFile);
                fileWriter.write(this.json);
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.layout.getChildren().add(save);
    }

    @Override
    public void postInit(Object... objects) {
        for (Node node : this.layout.getChildren()) {
            if (node instanceof JFXTextArea) {
                JFXTextArea area = (JFXTextArea) node;
                area.setText(this.json);
            }
        }
    }
}
