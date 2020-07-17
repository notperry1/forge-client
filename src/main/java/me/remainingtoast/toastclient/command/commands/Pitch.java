package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;

public class Pitch extends Command {
    public Pitch() {
        super("Pitch", "Set your players pitch", "pitch");
    }

    @Override
    protected void call(String[] args) {
        if(args.length > 0){
            if(NumberUtil.isNumeric(args[0])){
                final float num = Float.parseFloat(args[0]);
                if(mc.player.getRidingEntity() != null){
                    mc.player.getRidingEntity().rotationPitch = num;
                } else {
                    mc.player.rotationPitch = num;
                }
                MessageUtil.sendMessage("Set pitch to " + num, MessageUtil.Color.GREEN);
            } else {
                MessageUtil.sendMessage("Unknown Number : " + args[0], MessageUtil.Color.RED);
            }
        }
    }
}
