package fr.arinonia.arijsoncreator;

import com.google.gson.Gson;
import fr.arinonia.arijsoncreator.json.Data;
import fr.arinonia.arijsoncreator.ui.PanelManager;
import fr.arinonia.arijsoncreator.ui.panels.HomePanel;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator
 */
public class AriJsonCreator {

    private PanelManager panelManager;
    private final Gson JSON = new Gson();
    private Data data = new Data();
    private String url;
    private final ArrayList<String> filesList = new ArrayList<>();
    private final ArrayList<String> sizeFileList = new ArrayList<>();
    private final ArrayList<String> fileNameList = new ArrayList<>();
    private final ArrayList<String> ignoredFiles = new ArrayList<String>();

    public void init(Stage stage){
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new HomePanel());
    }

    public Gson getJSON() {
        return JSON;
    }

    public Data getData() {
        return data;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getFilesList() {
        return filesList;
    }

    public ArrayList<String> getSizeFileList() {
        return sizeFileList;
    }

    public ArrayList<String> getFileNameList() {
        return fileNameList;
    }

    public List<String> getIgnoredFiles() {
        return ignoredFiles;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
