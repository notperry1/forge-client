package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Prefix", description = "Change command prefix", aliases = {"p"}, usage = "prefix <value>")
public class Prefix extends Command {

    @Override
    public void onRun(final String[] args) {
        if (!(CommandManager.getCommandPrefix() == null)) {
            if (args.length >= 1) {
                CommandManager.setCommandPrefix(args[0]);
                MessageUtil.sendMessage("Command Prefix now set to " + CommandManager.getCommandPrefix(), MessageUtil.Color.GREEN);
            }
        }
    }
}
