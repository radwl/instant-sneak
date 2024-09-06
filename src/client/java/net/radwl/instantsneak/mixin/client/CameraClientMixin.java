package net.radwl.instantsneak.mixin.client;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.radwl.instantsneak.config.ModConfigs;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraClientMixin {

    @Unique
    private boolean wasSneaking = false;

    @Shadow private float cameraY;
    @Shadow private Entity focusedEntity;

    @Shadow private float lastCameraY;

    @Redirect(method = "updateEyeHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/Camera;cameraY:F", opcode = Opcodes.PUTFIELD))
    public void instantSneak(Camera obj, float value) {
        if (focusedEntity instanceof PlayerEntity && !ModConfigs.ANIMATION_ENABLED) {
            if (focusedEntity.getPose() == EntityPose.CROUCHING) {
                wasSneaking = true;
                lastCameraY = cameraY = focusedEntity.getStandingEyeHeight();
                return;
            } else if (wasSneaking) {
                wasSneaking = false;
                lastCameraY = cameraY = focusedEntity.getStandingEyeHeight();
                return;
            }
        }
        cameraY = this.cameraY + (this.focusedEntity.getStandingEyeHeight() - this.cameraY) * (float)ModConfigs.ANIMATION_SPEED;
    }

}
