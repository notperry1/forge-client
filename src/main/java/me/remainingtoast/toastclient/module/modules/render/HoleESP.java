package me.remainingtoast.toastclient.module.modules.render;

import me.remainingtoast.toastclient.module.Module;

public class HoleESP extends Module {

    public HoleESP() {
        super("HoleESP", Category.RENDER, "Render Safe Holes");
    }


    @Override
    public void onTick() {
        if(mc.player == null) return;

    }
}
