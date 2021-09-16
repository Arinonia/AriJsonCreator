package fr.arinonia.arijsoncreator.json;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private DataMaintenance maintenance;
    private List<DataFile> files = new ArrayList<DataFile>();
    private List<String> ignoredFiles = new ArrayList<String>();



    public DataMaintenance getMaintenance() {
        return maintenance;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public List<String> getIgnoredFiles() {
        return ignoredFiles;
    }

    public void setMaintenance(DataMaintenance maintenance) {
        this.maintenance = maintenance;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    public void setIgnoredFiles(List<String> ignoreFiles) {
        this.ignoredFiles = ignoreFiles;
    }
}
