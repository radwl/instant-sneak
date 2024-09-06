package net.radwl.instantsneak.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.radwl.instantsneak.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "getStandingEyeHeight", at = @At("RETURN"), cancellable = true)
    private void getStandingEyeHeight(CallbackInfoReturnable<Float> cir) {
        Entity entity = (Entity)(Object)this;
        if (entity instanceof AbstractClientPlayerEntity
                && entity.getPose() == EntityPose.CROUCHING
                && MinecraftClient.getInstance().options.getPerspective().isFirstPerson()) {
            cir.setReturnValue((float)(1.27 + ModConfigs.SNEAK_HEIGHT * (1.42 - 1.27)));
        }
    }

}
