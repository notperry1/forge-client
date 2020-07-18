package me.remainingtoast.toastclient.module.modules.render;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "HoleESP", category = Module.Category.RENDER, description = "Render safe holes", aliases = {"Holes"}, hidden = false)
public class HoleESP extends Module {

    @Override
    public void onTick() {
        if(mc.player == null) return;

    }
}
