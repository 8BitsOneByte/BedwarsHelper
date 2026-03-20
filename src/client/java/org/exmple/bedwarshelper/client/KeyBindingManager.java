package org.exmple.bedwarshelper.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.exmple.bedwarshelper.config.ModConfig;

public class KeyBindingManager {

    public static final KeyBinding.Category CATEGORY = new KeyBinding.Category(
        Identifier.of("bedwarshelper", "custom_category")
    );

    public static final KeyBinding TOGGLE_ESP_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.bedwarshelper.toggle_esp",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            CATEGORY
        )
    );

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_ESP_KEY.wasPressed()) {
                ModConfig.ESP_ENABLED = !ModConfig.ESP_ENABLED;
                if (client.player != null) {
                    String status = ModConfig.ESP_ENABLED ? "§cESP: OFF" : "§aESP: ON";
                    client.player.sendMessage(
                        net.minecraft.text.Text.of(status),
                        true
                    );
                }
            }
        });

    }
}

