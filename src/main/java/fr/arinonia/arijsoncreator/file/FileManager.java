package fr.arinonia.arijsoncreator.file;

import fr.arinonia.arijsoncreator.AriJsonCreator;

import java.io.File;

public class FileManager {

    private final ConfigurationFolder configurationFolder;

    public FileManager(AriJsonCreator ariJsonCreator) {
        this.configurationFolder = new ConfigurationFolder(ariJsonCreator);
    }

    public File getRootFolder() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\" + "Ordinal\\AriJsonCreator");
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home") + "/Library/Application Support/" + "Ordinal/AriJsonCreator");
        else
            return new File(System.getProperty("user.home") + "/" + "Ordinal/AriJsonCreator");
    }

    public ConfigurationFolder getConfigurationFolder() {
        return this.configurationFolder;
    }
}
