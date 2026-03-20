package org.exmple.bedwarshelper.mixin.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.exmple.bedwarshelper.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityGlowingMixin {
    @Inject(
        method = "isGlowing",
        at = @At("HEAD"),
        cancellable = true
    )
    private void forcePlayerGlowing(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity) {
            cir.setReturnValue(ModConfig.ESP_ENABLED);
        }
    }
}
