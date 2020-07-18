package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.util.text.TextComponentString;

@ModuleManifest(label = "AutoLog", category = Module.Category.PLAYER, description = "Disconnect when you are low health", aliases = {"Log"}, hidden = false)
public class AutoLog extends Module {

    @Override
    public void onTick() {
        if (mc.player.getHealth() < 15) {
            this.logOut("Logged Out With " + mc.player.getHealth() + " Health");
        }
    }

    private void logOut(String reason) {
        this.mc.player.connection.getNetworkManager().closeChannel(new TextComponentString(reason));
    }
}
