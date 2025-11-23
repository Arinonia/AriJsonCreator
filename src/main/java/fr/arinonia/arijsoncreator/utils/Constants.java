package fr.arinonia.arijsoncreator.utils;

public final class Constants {

    private Constants() {
        // Utility class
    }

    // Application
    public static final String APP_NAME = "AriJsonCreator";
    public static final String APP_VERSION = "2.0.0 - BETA";

    // File extensions
    public static final String CONFIG_EXTENSION = ".jsonconfig";

    // Colors (CSS format)
    public static final String PURPLE = "#884fb0;";
    public static final String BACKGROUND_COLOR = "#1B1A1C;";
    public static final String BACKGROUND_INPUT_FIELD = "#393939;";

    // Font path
    public static final String FONT_PATH = "/fonts/Roboto-Medium.ttf";

    // Network timeouts (ms)
    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 5000;

    // Buffer size for file operations
    public static final int BUFFER_SIZE = 8192;
}
