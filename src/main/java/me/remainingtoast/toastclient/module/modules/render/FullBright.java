package me.remainingtoast.toastclient.module.modules.render;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "FullBright", category = Module.Category.RENDER, description = "Increase clients brightness", aliases = {"Brightness"}, hidden = false)
public class FullBright extends Module {

    private float startGamma;

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            startGamma = mc.gameSettings.gammaSetting;
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.gameSettings.gammaSetting = startGamma;
        }
    }

    @Override
    public void onTick() {
        if (mc.gameSettings.gammaSetting < 16) {
            mc.gameSettings.gammaSetting++;
        }
    }
}
