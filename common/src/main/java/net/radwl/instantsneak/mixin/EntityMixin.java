package net.radwl.instantsneak.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.radwl.instantsneak.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "getEyeHeight()F", at = @At("RETURN"), cancellable = true)
    private void getStandingEyeHeight(CallbackInfoReturnable<Float> cir) {
        Entity entity = (Entity)(Object)this;
        if (entity instanceof AbstractClientPlayer
                && entity.getPose() == Pose.CROUCHING
                && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            cir.setReturnValue((float)(1.27 + ModConfig.SNEAK_HEIGHT * (1.42 - 1.27)));
        }
    }

}