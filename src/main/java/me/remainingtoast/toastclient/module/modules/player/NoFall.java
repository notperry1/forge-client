package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.network.play.client.CPacketPlayer;

@ModuleManifest(label = "NoFall", category = Module.Category.PLAYER, description = "Prevent you from taking fall damage", aliases = {}, hidden = false)
public class NoFall extends Module {

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            mc.player.fallDistance = 0;
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.fallDistance = 0;
        }
    }

    @Override
    public void onTick() {
        if (mc.player.fallDistance != 0) {
            mc.player.connection.sendPacket(new CPacketPlayer(true));
        }
    }
}
