package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;

public class Yaw extends Command {
    public Yaw() {
        super("Yaw", "Set yaw", "yaw");
    }

    @Override
    protected void call(String[] args) {
        if(args.length > 0){
            if(NumberUtil.isNumeric(args[1])){
                if(mc.player != null){
                    mc.player.rotationYaw = Float.parseFloat(args[1]);
                    MessageUtil.sendMessage("Yaw set to " + args[1], MessageUtil.Color.GREEN);
                    return;
                }
                MessageUtil.sendMessage("Player is null " + args[1], MessageUtil.Color.RED);
            }
        }
    }
}
