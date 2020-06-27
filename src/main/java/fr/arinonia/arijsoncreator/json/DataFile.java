package fr.arinonia.arijsoncreator.json;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.json
 */
public class DataFile {

    private String path;
    private String hash;
    private String url;
    private String size;

    public DataFile(String path, String hash, String url, String size) {
        this.path = path;
        this.hash = hash;
        this.url = url;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public String getHash() {
        return hash;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
