package fr.arinonia.arijsoncreator;

import com.google.gson.Gson;
import fr.arinonia.arijsoncreator.file.FileManager;
import fr.arinonia.arijsoncreator.file.Settings;
import fr.arinonia.arijsoncreator.json.Data;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.panels.RootPanel;
import fr.arinonia.arijsoncreator.utils.I18n;
import fr.arinonia.arijsoncreator.utils.Logger;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;

public class AriJsonCreator {

    private final Gson json = new Gson();
    private final ArrayList<String> filesList = new ArrayList<>();
    private final ArrayList<String> sizeFileList = new ArrayList<>();
    private final ArrayList<String> fileNameList = new ArrayList<>();
    private final Data data = new Data();
    private final Logger logger = new Logger();
    private final FileManager fileManager = new FileManager(this);
    private final UiManager uiManager;

    private Settings settings;
    private String url;

    public AriJsonCreator() {
        this.uiManager = new UiManager(this);
    }

    public void init(final Stage stage) {
        try {
            this.setup(stage);
        } catch (Throwable t) {
            logger.logWarn(t.getMessage());
            t.printStackTrace();
            logger.logError("YAMETE KUDASAI ONI CHAN !!!!!!!!!!!!");
        }
    }


    private void setup(final Stage stage) {
        this.preLoad();
        this.uiManager.createFrame(stage);
        this.uiManager.showPanel(this.uiManager.getLayout(),  new RootPanel(this.uiManager));
    }



    private void preLoad() {
        if (!this.fileManager.getRootFolder().exists()) {
            if (!this.fileManager.getRootFolder().mkdirs()) {
                logger.logError("An error as occurred while creating the root folder");
            }
            if (!this.fileManager.getConfigurationFolder().getFolder().mkdirs()) {
                logger.logError("An error as occurred while creating the config folder");
            }
        } else {
            if (!this.fileManager.getConfigurationFolder().getFolder().exists()) {
                if (!this.fileManager.getConfigurationFolder().getFolder().mkdirs()) {
                    logger.logError("An error as occurred while creating the config folder");
                }
            }
        }
        this.settings = new Settings(this);
        I18n.init();
        I18n.LANG.setSelectedLanguage(Locale.forLanguageTag(this.settings.get("lang").substring(0, 2)));
    }

    public boolean isValidURL(String theUrl) {
        try {
            URL url = new URL(theUrl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            return connect.getResponseCode() == 200 || connect.getResponseCode() == 202;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listAllFile(File file) {
        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    listAllFile(f);
                } else {
                    try {
                        this.getFilesList().add(this.checkSumToString(f.getAbsolutePath()));
                        this.getSizeFileList().add(Long.toString(f.length()));
                        this.getFileNameList().add(f.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private byte[] createChecksum(String file) throws Exception {
        InputStream is = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = is.read(buffer);
            if (numRead > 0) {
                messageDigest.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        is.close();
        return messageDigest.digest();
    }

    private String checkSumToString(String file) throws Exception{
        byte[] bytes = createChecksum(file);
        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }

    public String formatJSON(String json) {
        char[] chars = json.toCharArray();
        String newLine = System.lineSeparator();
        boolean begin_quotes = false;

        StringBuilder ret = new StringBuilder();
        for (int i = 0, indent = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '\"') {
                ret.append(c);
                begin_quotes = !begin_quotes;
                continue;
            }

            if (!begin_quotes) {
                switch (c) {
                    case '{':
                    case '[':
                        ret.append(c).append(newLine).append(String.format("%" + (indent += 2) + "s", ""));
                        continue;
                    case '}':
                    case ']':
                        ret.append(newLine).append((indent -= 2) > 0 ? String.format("%" + indent + "s", "") : "").append(c);
                        continue;
                    case ':':
                        ret.append(c).append(" ");
                        continue;
                    case ',':
                        ret.append(c).append(newLine).append(indent > 0 ? String.format("%" + indent + "s", "") : "");
                        continue;
                    default:
                        if (Character.isWhitespace(c)) continue;
                }
            }
            ret.append(c).append(c == '\\' ? "" + chars[++i] : "");
        }
        return ret.toString();
    }


    public Logger getLogger() {
        return this.logger;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public UiManager getUiManager() {
        return this.uiManager;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Gson getJson() {
        return this.json;
    }

    public Data getData() {
        return this.data;
    }

    public String getUrl() {
        return this.url;
    }

    public ArrayList<String> getFilesList() {
        return this.filesList;
    }

    public ArrayList<String> getSizeFileList() {
        return this.sizeFileList;
    }

    public ArrayList<String> getFileNameList() {
        return this.fileNameList;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
