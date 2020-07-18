package me.remainingtoast.toastclient.module.modules.movement;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.MathUtil;

@ModuleManifest(label = "ElytraFlight", category = Module.Category.MOVEMENT, description = "Fly using Elytra", aliases = {}, hidden = false)
public class ElytraFlight extends Module {

    public float speed = 1;

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            mc.player.capabilities.isFlying = true;
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.capabilities.isFlying = false;
        }
    }

    @Override
    public void onTick() {
        if (mc.player.isElytraFlying()) {
            final double[] directionSpeed = MathUtil.directionSpeed(this.speed);
            mc.player.motionY = 0;
            mc.player.motionX = 0;
            mc.player.motionZ = 0;
            if (mc.player.movementInput.jump) {
                mc.player.motionY = this.speed / 2;
            } else if (mc.player.movementInput.sneak) {
                mc.player.motionY = -this.speed / 2;
            }
            if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0) {
                mc.player.motionX = directionSpeed[0];
                mc.player.motionZ = directionSpeed[1];
            }
        }
    }
}
