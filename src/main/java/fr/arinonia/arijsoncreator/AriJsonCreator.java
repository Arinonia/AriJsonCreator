package fr.arinonia.arijsoncreator;

import com.google.gson.Gson;
import fr.arinonia.arijsoncreator.file.FileManager;
import fr.arinonia.arijsoncreator.file.Settings;
import fr.arinonia.arijsoncreator.json.Data;
import fr.arinonia.arijsoncreator.service.FileScanner;
import fr.arinonia.arijsoncreator.service.JsonFormatter;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.panels.RootPanel;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import fr.arinonia.arijsoncreator.utils.Logger;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AriJsonCreator {

    private final Gson gson = new Gson();
    private final Data data = new Data();
    private final Logger logger = new Logger();
    private final FileManager fileManager = new FileManager(this);
    private final FileScanner fileScanner;
    private final UiManager uiManager;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Settings settings;
    private String baseUrl;

    public AriJsonCreator() {
        this.fileScanner = new FileScanner(logger);
        this.uiManager = new UiManager(this);
    }

    public void init(final Stage stage) {
        try {
            this.setup(stage);
        } catch (Exception e) {
            logger.logError("Failed to initialize application: " + e.getMessage());
            e.printStackTrace();
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
        HttpURLConnection connect = null;
        try {
            URL url = new URL(theUrl);
            connect = (HttpURLConnection) url.openConnection();
            connect.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
            connect.setReadTimeout(Constants.READ_TIMEOUT);
            int responseCode = connect.getResponseCode();
            return responseCode == 200 || responseCode == 202;
        } catch (IOException e) {
            logger.logWarn("URL validation failed: " + e.getMessage());
            return false;
        } finally {
            if (connect != null) {
                connect.disconnect();
            }
        }
    }

    /**
     * Formats a JSON string with proper indentation.
     * @deprecated Use {@link JsonFormatter#format(String)} directly
     */
    @Deprecated
    public String formatJSON(String json) {
        return JsonFormatter.format(json);
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

    public Gson getGson() {
        return this.gson;
    }

    /**
     * @deprecated Use {@link #getGson()} instead
     */
    @Deprecated
    public Gson getJson() {
        return this.gson;
    }

    public Data getData() {
        return this.data;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * @deprecated Use {@link #getBaseUrl()} instead
     */
    @Deprecated
    public String getUrl() {
        return this.baseUrl;
    }

    public FileScanner getFileScanner() {
        return this.fileScanner;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @deprecated Use {@link #setBaseUrl(String)} instead
     */
    @Deprecated
    public void setUrl(String url) {
        this.baseUrl = url;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
