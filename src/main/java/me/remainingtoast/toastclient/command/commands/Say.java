package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

import java.util.Arrays;

@CommandManifest(label = "Say", description = "Send direct message", aliases = {""}, usage = "")
public class Say extends Command {

    @Override
    public void onRun(final String[] args) {
        if (args.length > 0){
            MessageUtil.sendPublicMessage(Arrays.toString(args));
        }
    }
}
