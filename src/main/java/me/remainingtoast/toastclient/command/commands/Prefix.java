package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Preifx", description = "Change command prefix", aliases = {"p"}, usage = "")
public class Prefix extends Command {

    @Override
    public void onRun(final String[] args) {
        if (!(ToastClient.PREFIX == null)) {
            if (args.length >= 1) {
                ToastClient.PREFIX = args[0];
                MessageUtil.sendMessage("Command Prefix now set to " + ToastClient.PREFIX, MessageUtil.Color.GREEN);
            }
        }
    }
}
