package org.exmple.bedwarshelper.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    public static boolean ESP_ENABLED = false;

    private static final Logger LOGGER = LoggerFactory.getLogger("bedwarshelper");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("bedwarshelper.json");

    private static class ConfigData {
        boolean espEnabled = false;
    }

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    ESP_ENABLED = data.espEnabled;
                }
            } catch (IOException e) {
                LOGGER.error("Failed to load BedwarsHelper config", e);
            }
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.espEnabled = ESP_ENABLED;
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to save BedwarsHelper config", e);
        }
    }
}
