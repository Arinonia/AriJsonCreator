package fr.arinonia.arijsoncreator.file;

import fr.arinonia.arijsoncreator.AriJsonCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ConfigurationFolder {

    private final AriJsonCreator ariJsonCreator;

    public ConfigurationFolder(AriJsonCreator ariJsonCreator) {
        this.ariJsonCreator = ariJsonCreator;
    }

    public File getFolder() {
        return new File(ariJsonCreator.getFileManager().getRootFolder(), "configs");
    }

    public ArrayList<File> listAllConfig() {
        ArrayList<File> configFiles = new ArrayList<>();

        for (File file : Objects.requireNonNull(this.getFolder().listFiles())) {
            if (file.getName().endsWith(".jsonconfig")) {
                configFiles.add(file);
            }
        }
        return configFiles;
    }
}
