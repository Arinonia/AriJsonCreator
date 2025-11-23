package fr.arinonia.arijsoncreator.file;

import fr.arinonia.arijsoncreator.AriJsonCreator;
import fr.arinonia.arijsoncreator.utils.Constants;

import java.io.File;
import java.util.ArrayList;

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
        File[] files = this.getFolder().listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(Constants.CONFIG_EXTENSION)) {
                    configFiles.add(file);
                }
            }
        }
        return configFiles;
    }
}
