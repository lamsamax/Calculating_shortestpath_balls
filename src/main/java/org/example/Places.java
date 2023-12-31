package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Places {
    private final String shortCode;
    private final String longCode;

    private static final Map<String, String> ALL_PLACES = new HashMap<>();

    public Places(String shortCode, String longCode) {
        this.shortCode = shortCode;
        this.longCode = longCode;
        ALL_PLACES.put(shortCode, longCode);
    }

    public static void addPlace(String shortCode, String name) {
        ALL_PLACES.put(shortCode, name);
    }

    public static Map<String, String> getAllPlaces() {
        return new HashMap<>(ALL_PLACES);
    }

    public static boolean doesShortCodeExist(String shortCode) {
        return ALL_PLACES.containsKey(shortCode);
    }

    public String getLongCode() {
        return longCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public static void populateShortCodesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String shortCode = data[0].trim();
                String longCode = data[1].trim();

                addPlace(shortCode, longCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNodePopulatedInShortCodes(String shortCode) {
        return doesShortCodeExist(shortCode);
    }
}