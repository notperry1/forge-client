package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Reload", description = "Reload config", aliases = {""}, usage = "")
public class Reload extends Command {

    @Override
    public void onRun(final String[] args) {
        if(args.length == 0){
            MessageUtil.sendMessage("Config Reloaded", MessageUtil.Color.GREEN);
        } else {
            MessageUtil.sendMessage("No Arguments Needed!", MessageUtil.Color.RED);
        }
    }
}
