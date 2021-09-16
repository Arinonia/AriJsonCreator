package fr.arinonia.arijsoncreator.file;

import fr.arinonia.arijsoncreator.AriJsonCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class Config {

    private final String name;
    private final String url;
    private final String path;
    private final List<String> ignored;
    private final AriJsonCreator ariJsonCreator;

    public Config(String name, String url, String path, List<String> ignored, AriJsonCreator ariJsonCreator) {
        this.name = name;
        this.url = url;
        this.path = path;
        this.ignored = ignored;
        this.ariJsonCreator = ariJsonCreator;
    }


    public Config(AriJsonCreator ariJsonCreator) {
        this("", "", "", Collections.emptyList(), ariJsonCreator);
    }

    public ConfigJson loadConfig(String theFile) {
        File file = new File(ariJsonCreator.getFileManager().getConfigurationFolder().getFolder(), theFile + ".jsonconfig");

        if (file.exists()) {
            try {
                List<String> content = Files.readAllLines(file.toPath());
                StringBuilder builder = new StringBuilder();

                for (String str : content) {
                    builder.append(str);
                }
                return this.ariJsonCreator.getJson().fromJson(builder.toString(), ConfigJson.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ariJsonCreator.getLogger().logError("Can't load file");
            return null;
        }
    }

    public void createFile() {
        if (!fileExist()) {
            try {
                ConfigJson configJson = new ConfigJson(this.name, this.url, this.path, this.ignored);
                File file = new File(ariJsonCreator.getFileManager().getConfigurationFolder().getFolder(), this.name + ".jsonconfig");
                file.createNewFile();
                this.write(file, this.ariJsonCreator.getJson().toJson(configJson));
            } catch (IOException e) {
                ariJsonCreator.getLogger().logError(e.getMessage());
            }
        }
    }

    public void updateConfig() {
        ConfigJson configJson = new ConfigJson(this.name, this.url, this.path, this.ignored);

        File file = new File(ariJsonCreator.getFileManager().getConfigurationFolder().getFolder(), this.name + ".jsonconfig");
        this.write(file, "");
        this.write(file, this.ariJsonCreator.getJson().toJson(configJson));
    }

    private void write(File file, String json){
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileExist() {
        return new File(ariJsonCreator.getFileManager().getConfigurationFolder().getFolder(), this.name + ".jsonconfig").exists();
    }

}
