package org.exmple.bedwarshelper.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.exmple.bedwarshelper.config.ModConfig;

public class ModScreen extends Screen {

    public ModScreen() {
        super(Text.literal("Bedwars Helper"));
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(
            getEspButtonText(),
            button -> {
                ModConfig.ESP_ENABLED = !ModConfig.ESP_ENABLED;
                ModConfig.save();
                button.setMessage(getEspButtonText());
            }
        )
        .dimensions(this.width / 2 - 100, this.height / 2 - 20, 200, 20)
        .build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.translatable("gui.done"),
            button -> this.close()
        )
        .dimensions(this.width / 2 - 100, this.height / 2 + 10, 200, 20)
        .build());
    }

    private Text getEspButtonText() {
        return Text.literal("ESP: ").append(
            Text.literal(ModConfig.ESP_ENABLED ? "ON" : "OFF")
                .formatted(ModConfig.ESP_ENABLED ? Formatting.GREEN : Formatting.RED)
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
    }
}
