package fr.arinonia.arijsoncreator.file;

import java.util.List;

public class ConfigJson {

    private final String name;
    private final String url;
    private final String path;
    private final List<String> ignored;

    public ConfigJson(String name, String url, String path, List<String> ignored) {
        this.name = name;
        this.url = url;
        this.path = path;
        this.ignored = ignored;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public List<String> getIgnored() {
        return ignored;
    }
}
