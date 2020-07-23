package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

@ModuleManifest(label = "AutoReconnect", category = Module.Category.PLAYER, description = "Automatically reconnects to server", aliases = {}, hidden = false)
public class AutoReconnect extends Module {

    final ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();

}
