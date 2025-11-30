package net.radwl.instantsneak.mixin;

import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.radwl.instantsneak.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.objectweb.asm.Opcodes;

@Mixin(Camera.class)
public abstract class CameraClientMixin {

    @Unique
    private boolean instantsneak$wasSneaking = false;

    @Shadow
    private float eyeHeight;
    @Shadow private Entity entity;

    @Shadow private float eyeHeightOld;

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Camera;eyeHeight:F", opcode = Opcodes.PUTFIELD))
    public void instantSneak(Camera obj, float value) {
        if (entity instanceof Player && ModConfig.INSTANT) {
            if (entity.getPose() == Pose.CROUCHING) {
                instantsneak$wasSneaking = true;
                eyeHeightOld = eyeHeight = entity.getEyeHeight();
                return;
            } else if (instantsneak$wasSneaking) {
                instantsneak$wasSneaking = false;
                eyeHeightOld = eyeHeight = entity.getEyeHeight();
                return;
            }
        }
        eyeHeight += (this.entity.getEyeHeight() - this.eyeHeight) * (float)(ModConfig.ANIMATION_SPEED / 2.0);
    }

}