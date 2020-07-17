package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;

public class Reload extends Command {
    public Reload() {
        super("Reload", "Reload config", "reload");
    }

    @Override
    protected void call(String[] args) {
        if(args.length == 0){
            ToastClient.SETTINGS_MANAGER.updateSettings();
            MessageUtil.sendMessage("Config Reloaded", MessageUtil.Color.GREEN);
        } else {
            MessageUtil.sendMessage("No Arguments Needed!", MessageUtil.Color.RED);
        }
    }
}
