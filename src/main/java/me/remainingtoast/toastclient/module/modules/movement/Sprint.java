package me.remainingtoast.toastclient.module.modules.movement;

import me.remainingtoast.toastclient.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, "Automatically Sprints for you");
    }

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