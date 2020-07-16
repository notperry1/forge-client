package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;

public class Prefix extends Command {

    public Prefix() {
        super("Prefix", "Change command prefix", "p", "prefix");
    }

    @Override
    protected void call(String[] args) {
        if (!(ToastClient.PREFIX == null)) {
            if (args.length >= 1) {
                ToastClient.PREFIX = args[0];
                MessageUtil.sendMessage("Command Prefix now set to " + ToastClient.PREFIX, MessageUtil.Color.GREEN);
            }
        }
    }
}
