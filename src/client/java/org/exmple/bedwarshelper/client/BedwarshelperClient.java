package org.exmple.bedwarshelper.client;

import net.fabricmc.api.ClientModInitializer;
import org.exmple.bedwarshelper.config.ModConfig;

public class BedwarshelperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.load();
        KeyBindingManager.init();
    }
}
