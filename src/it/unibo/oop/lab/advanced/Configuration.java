package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines a way to retrieve the settings for the game from the configuration file.
 *
 */
public final class Configuration {

    private final Map<String, Integer> configMap = new HashMap<>();

    /**
     * Using regex-parsing on strings, retrieves the settings
     * from the configuration file and stores them in a {@link java.util.Map}.
     * 
     * @param configFile
     *                  the configuration file
     * @return a {@link java.util.Map} containing the settings for the game
     * @throws IOException if the specified configuration file cannot be read properly
     */
    public Map<String, Integer> getConstants(final String configFile) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(configFile)))) {
            String thisLine;
            while ((thisLine = bf.readLine()) != null) {
                final String[] splitLine = thisLine.split(":");
                configMap.put(splitLine[0], Integer.parseInt(splitLine[1].trim()));
            }
        }
        return configMap;
    }
}
