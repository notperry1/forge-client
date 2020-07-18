package me.remainingtoast.toastclient.module.modules.misc;

import me.remainingtoast.toastclient.DiscordPresence;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "DiscordRPC", category = Module.Category.MISC, description = "Discord Presence", aliases = {"Discord"}, hidden = true)
public class DiscordRPC extends Module {

    @Override
    protected void onEnable() {
        DiscordPresence.start();
    }

    @Override
    protected void onDisable() {
        DiscordPresence.end();
    }
}
