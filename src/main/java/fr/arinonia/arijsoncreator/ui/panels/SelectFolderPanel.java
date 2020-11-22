package fr.arinonia.arijsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.json.DataFile;
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
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arinonia on 27/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panels
 */
public class SelectFolderPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        Label title = new Label("Select Folder");
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        title.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),55));
        title.setTranslateY(35);
        title.setStyle("-fx-text-fill: #fff");

        Label message = new Label("Select the folder who contain all you need download");
        GridPane.setValignment(message, VPos.TOP);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setHgrow(message, Priority.ALWAYS);
        GridPane.setVgrow(message, Priority.ALWAYS);
        message.setTranslateY(150.0D);
        message.setStyle("-fx-text-fill: #fff");
        message.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(), 30));

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
            panelManager.showPanel(new SelectIgnoredPanel());
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
            panelManager.getAriJsonCreator().getSizeFileList().clear();
            panelManager.getAriJsonCreator().getFileNameList().clear();
            panelManager.getAriJsonCreator().getFilesList().clear();
            panelManager.showPanel(new UrlPanel());
        });

        JFXSpinner spinner = new JFXSpinner();
        GridPane.setValignment(spinner, VPos.CENTER);
        GridPane.setHalignment(spinner, HPos.CENTER);
        GridPane.setHgrow(spinner, Priority.ALWAYS);
        GridPane.setVgrow(spinner, Priority.ALWAYS);
        spinner.setPrefSize(50,50);
        spinner.setMaxSize(50,50);
        spinner.setTranslateY(50.0D);
        JFXButton select = new JFXButton("Select folder");
        GridPane.setValignment(select, VPos.CENTER);
        GridPane.setHalignment(select, HPos.CENTER);
        GridPane.setHgrow(select, Priority.ALWAYS);
        GridPane.setVgrow(select, Priority.ALWAYS);
        select.setPrefSize(210,60);
        select.setMinSize(210,60);
        select.setMaxSize(210,60);
        select.setStyle("-fx-padding: 0.7em 0.57em; -fx-text-fill: #fff; -fx-border-color: #aa652f");
        select.setOnMouseClicked(e->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if(System.getProperty("os.name").toLowerCase().contains("win")){
                directoryChooser.setInitialDirectory(new File("C:\\"));
            }
            else{
                directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            directoryChooser.setTitle("Ari-JsonCreator | Directory chooser");
            File file = directoryChooser.showDialog(null);
            Thread t = new Thread(){
                @Override
                public void run() {
                    next.setDisable(true);
                    back.setDisable(true);
                    listAllFile(file);
                    List<DataFile> files = new ArrayList<DataFile>();
                    for(int i = 0; i < panelManager.getAriJsonCreator().getFilesList().size(); i++){
                        String s = panelManager.getAriJsonCreator().getFileNameList().get(i).substring(file.getAbsolutePath().length()+1).replace("\\", "/");
                        files.add(new DataFile(s, panelManager.getAriJsonCreator().getFilesList().get(i),panelManager.getAriJsonCreator().getUrl().endsWith("/") ? panelManager.getAriJsonCreator().getUrl() + s : panelManager.getAriJsonCreator().getUrl() + "/" + s, panelManager.getAriJsonCreator().getSizeFileList().get(i)));
                    }
                    panelManager.getAriJsonCreator().getData().setFiles(files);
                    spinner.setVisible(false);
                    next.setDisable(false);
                    back.setDisable(false);
                    select.setDisable(true);
                }
            };
            t.start();
            panelManager.getLayout().getChildren().add(spinner);

        });
        select.setTranslateY(-30.0D);
        select.setOnMouseEntered(e->panelManager.getLayout().setCursor(Cursor.HAND));
        select.setOnMouseExited(e->panelManager.getLayout().setCursor(Cursor.DEFAULT));
        select.setFont(Font.loadFont(Main.class.getResource("/font/Poppins-Medium.ttf").toString(),22));


        panelManager.getLayout().getChildren().addAll(title, message, select, next, back);
    }

    private void listAllFile(File file){
        File[] list = file.listFiles();
        if(list != null){
            for (File files : list){
                if(files.isDirectory()){
                    listAllFile(files.getAbsoluteFile());
                }else{
                    try {
                        this.panelManager.getAriJsonCreator().getFilesList().add(getMD5ToFile(files.getAbsolutePath()));
                        this.panelManager.getAriJsonCreator().getSizeFileList().add(Long.toString(files.length()));
                        this.panelManager.getAriJsonCreator().getFileNameList().add(files.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private byte[] creatingChecksum(String fileName) throws Exception{
        InputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do{
            numRead = fis.read(buffer);
            if(numRead>0){
                complete.update(buffer,0,numRead);
            }
        }while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    private String getMD5ToFile(String fileName) throws  Exception{
        byte[] b = creatingChecksum(fileName);
        StringBuilder result = new StringBuilder();
        for(byte aB : b){
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
