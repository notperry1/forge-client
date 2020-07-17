package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;

import java.util.Arrays;

public class Say extends Command {
    public Say() {
        super("Say", "Send direct message", "say");
    }

    @Override
    protected void call(String[] args) {
        if (args.length > 0){
            MessageUtil.sendPublicMessage(Arrays.toString(args));
        }
    }
}
