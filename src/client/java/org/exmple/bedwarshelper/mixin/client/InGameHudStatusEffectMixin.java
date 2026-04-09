package org.exmple.bedwarshelper.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.exmple.bedwarshelper.ui.StatusEffectTimerRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = InGameHud.class, priority = 500)
public abstract class InGameHudStatusEffectMixin {

    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "renderStatusEffectOverlay",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIII)V",
                    shift = At.Shift.AFTER))
    private void appendOverlayDrawing(DrawContext context,
                                      RenderTickCounter tickCounter,
                                      CallbackInfo ci,
                                      @Local StatusEffectInstance statusEffectInstance,
                                      @Local(ordinal = 2) int x,
                                      @Local(ordinal = 3) int y) {
        StatusEffectTimerRenderer.drawStatusEffectOverlay(client, context, statusEffectInstance, x, y);
    }
}
