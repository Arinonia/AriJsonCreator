package fr.arinonia.arijsoncreator.file;

import fr.arinonia.arijsoncreator.AriJsonCreator;

import java.io.*;
import java.util.Properties;

public class Settings {

    private final AriJsonCreator ariJsonCreator;
    private final Properties properties;

    public Settings(final AriJsonCreator ariJsonCreator) {
        this.ariJsonCreator = ariJsonCreator;
        this.properties = new Properties();

        if (new File(this.ariJsonCreator.getFileManager().getRootFolder(), "settings.properties").exists()) {
            try {
                this.properties.load(new FileInputStream(new File(this.ariJsonCreator.getFileManager().getRootFolder(), "settings.properties")));

            } catch (IOException e) {
                ariJsonCreator.getLogger().logError(e.getMessage());
            }
        } else {
            this.set("lang", "English");
            this.set("theme", "dark");
            this.set("config", "null");
            this.save();
        }
    }

    public void set(final String key, final String value) {
        this.properties.setProperty(key, value);
        this.save();
    }
    public String get(final String key) {
        return this.properties.getProperty(key);
    }

    public void save() {
        try {
            this.properties.store(new BufferedWriter(new FileWriter(new File(this.ariJsonCreator.getFileManager().getRootFolder(), "settings.properties"))), "Generate by AriJsonCreator");
        } catch (IOException e) {
            this.ariJsonCreator.getLogger().logError(e.getMessage());
        }
    }
}
