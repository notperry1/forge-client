package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;

@CommandManifest(label = "VClip", description = "Vertical clipping", aliases = {""}, usage = "vclip <distance>")
public class VClip extends Command {

    @Override
    public void onRun(final String[] args) {
        if(args.length > 0){
            if(NumberUtil.isNumeric(args[0])){
                final double num = Double.parseDouble(args[0]);
                if(mc.player.getRidingEntity() != null){
                    mc.player.getRidingEntity().setPosition(mc.player.getRidingEntity().posX, mc.player.getRidingEntity().posY+num, mc.player.getRidingEntity().posZ);
                } else {
                    mc.player.setPosition(mc.player.posX, mc.player.posY+num, mc.player.posZ);
                }
                MessageUtil.sendMessage("Teleported you " + ((num > 0) ? "up" : "down") + " " + num, MessageUtil.Color.GREEN);
            } else {
                MessageUtil.sendMessage("Unknown Number : " + args[0], MessageUtil.Color.RED);
            }
        }
    }
}
