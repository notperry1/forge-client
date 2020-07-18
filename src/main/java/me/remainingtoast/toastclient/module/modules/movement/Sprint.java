package me.remainingtoast.toastclient.module.modules.movement;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "Sprint", category = Module.Category.MOVEMENT, description = "Sprint Automatically", aliases = {}, hidden = false)
public class Sprint extends Module {

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setSprinting(false);
        }
    }

    @Override
    public void onTick() {
        try {
            mc.player.setSprinting(mc.gameSettings.keyBindForward.isKeyDown());
        } catch (Exception ignored) {
        }
    }
}