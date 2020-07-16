package me.remainingtoast.toastclient.module.modules;

import me.remainingtoast.toastclient.DiscordPresence;
import me.remainingtoast.toastclient.module.Module;

public class DiscordRPC extends Module {

    public DiscordRPC() {
        super("DiscordRPC", Category.NONE, "DiscordPresence", -1);
    }

    @Override
    protected void onEnable() {
        DiscordPresence.start();
    }

    @Override
    protected void onDisable() {
        DiscordPresence.end();
    }
}
