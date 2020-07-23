package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.client.gui.GuiGameOver;

@ModuleManifest(label = "AutoRespawn", category = Module.Category.PLAYER, description = "Automatically Respawn", aliases = {}, hidden = false)
public class AutoRespawn extends Module {

    @Override
    public void onTick() {
        if (mc.player.isDead && mc.currentScreen instanceof GuiGameOver) {
            mc.player.respawnPlayer();
        }
    }
}