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
            if(NumberUtil.isNumeric(args[0])){
                final float num = Float.parseFloat(args[0]);
                if(num >= 360f){
                    if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 360f; }
                    else { mc.player.rotationYaw = 360f; }
                    MessageUtil.sendMessage("Yaw set to 360, Facing south", MessageUtil.Color.GREEN);
                } else if(num <= -360f){
                    if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 360f;}
                    else { mc.player.rotationYaw = 360f; }
                    MessageUtil.sendMessage("Yaw set to 0, Facing south", MessageUtil.Color.GREEN);
                } else {
                    if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = num; }
                    else { mc.player.rotationYaw = num; }
                    MessageUtil.sendMessage("Yaw set to " + num, MessageUtil.Color.GREEN);
                }
            } else {
                switch (args[0].toLowerCase()) {
                    case "north":
                    case "n":
                        if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 180f; }
                        else { mc.player.rotationYaw = 180f; }
                        MessageUtil.sendMessage("Now facing north", MessageUtil.Color.GREEN);
                        break;
                    case "east":
                    case "e":
                        if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 270f; }
                        else { mc.player.rotationYaw = 270f; }
                        MessageUtil.sendMessage("Now facing east", MessageUtil.Color.GREEN);
                        break;
                    case "south":
                    case "s":
                        if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 360f; }
                        else { mc.player.rotationYaw = 360f; }
                        MessageUtil.sendMessage("Now facing south", MessageUtil.Color.GREEN);
                        break;
                    case "west":
                    case "w":
                        if(mc.player.getRidingEntity() != null){ mc.player.getRidingEntity().rotationYaw = 90f; }
                        else { mc.player.rotationYaw = 90f; }
                        MessageUtil.sendMessage("Now facing west", MessageUtil.Color.GREEN);
                        break;
                    default:
                        MessageUtil.sendMessage("Invalid arguments, specify a number between 1 and 360 or NORTH, EAST, SOUTH, WEST", MessageUtil.Color.RED);
                        break;
                }
            }
        }
    }
}
