package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Configuration {

    private final Map<String, Integer> configMap = new HashMap<>();

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
