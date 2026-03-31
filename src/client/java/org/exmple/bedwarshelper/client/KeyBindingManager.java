package org.exmple.bedwarshelper.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.exmple.bedwarshelper.config.ModConfig;
import org.exmple.bedwarshelper.ui.ModScreen;

public class KeyBindingManager {

    private static final String CATEGORY = "key.category.bedwarshelper.custom_category";

    public static final KeyBinding TOGGLE_ESP_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.bedwarshelper.toggle_esp",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            CATEGORY
        )
    );

    public static final KeyBinding OPEN_SETTINGS_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.bedwarshelper.open_settings",
            InputUtil.Type.KEYSYM,
            InputUtil.UNKNOWN_KEY.getCode(),
            CATEGORY
        )
    );

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_ESP_KEY.wasPressed()) {
                ModConfig.ESP_ENABLED = !ModConfig.ESP_ENABLED;
                ModConfig.save();
                if (client.player != null) {
                    Text status = Text.literal("ESP: ").append(
                        Text.literal(ModConfig.ESP_ENABLED ? "ON" : "OFF")
                            .formatted(ModConfig.ESP_ENABLED ? Formatting.GREEN : Formatting.RED)
                    );
                    client.player.sendMessage(status, true);
                }
            }
            while (OPEN_SETTINGS_KEY.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ModScreen());
                }
            }
        });

    }
}

