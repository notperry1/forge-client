package me.remainingtoast.toastclient.module.modules.movement;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "Flight", category = Module.Category.MOVEMENT, description = "Creative Flight", aliases = {"flight", "fly"}, hidden = false)
public class Flight extends Module {

    @Override
    protected void onEnable() {
        if (this.isEnabled()) {
            if (mc.player != null) {
                mc.player.capabilities.isFlying = true;
            }
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
        mc.player.capabilities.isFlying = true;
    }
}
