package fr.arinonia.arijsoncreator.service;

import fr.arinonia.arijsoncreator.json.DataFile;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for scanning directories and creating DataFile objects
 * with computed checksums.
 */
public class FileScanner {

    private final Logger logger;

    public FileScanner(Logger logger) {
        this.logger = logger;
    }

    /**
     * Scans a directory recursively and returns a list of DataFile objects.
     *
     * @param rootDirectory The root directory to scan
     * @param baseUrl       The base URL to prepend to file paths
     * @return List of DataFile objects with computed hashes
     */
    public List<DataFile> scanDirectory(File rootDirectory, String baseUrl) {
        List<DataFile> dataFiles = new ArrayList<>();
        scanRecursively(rootDirectory, rootDirectory, baseUrl, dataFiles);
        return dataFiles;
    }

    private void scanRecursively(File rootDirectory, File currentFile, String baseUrl, List<DataFile> result) {
        File[] files = currentFile.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                scanRecursively(rootDirectory, file, baseUrl, result);
            } else {
                try {
                    String relativePath = getRelativePath(rootDirectory, file);
                    String hash = computeChecksum(file);
                    String url = buildUrl(baseUrl, relativePath);
                    String size = Long.toString(file.length());

                    result.add(new DataFile(relativePath, hash, url, size));
                } catch (Exception e) {
                    logger.logError("Failed to process file: " + file.getAbsolutePath() + " - " + e.getMessage());
                }
            }
        }
    }

    private String getRelativePath(File rootDirectory, File file) {
        String rootPath = rootDirectory.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        return filePath.substring(rootPath.length() + 1).replace("\\", "/");
    }

    private String buildUrl(String baseUrl, String relativePath) {
        if (baseUrl.endsWith("/")) {
            return baseUrl + relativePath;
        }
        return baseUrl + "/" + relativePath;
    }

    /**
     * Computes MD5 checksum for a file.
     *
     * @param file The file to compute checksum for
     * @return The MD5 hash as a hex string
     * @throws Exception if checksum computation fails
     */
    public String computeChecksum(File file) throws Exception {
        byte[] buffer = new byte[Constants.BUFFER_SIZE];
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        try (InputStream is = new FileInputStream(file)) {
            int numRead;
            while ((numRead = is.read(buffer)) != -1) {
                if (numRead > 0) {
                    messageDigest.update(buffer, 0, numRead);
                }
            }
        }

        return bytesToHex(messageDigest.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }
}
