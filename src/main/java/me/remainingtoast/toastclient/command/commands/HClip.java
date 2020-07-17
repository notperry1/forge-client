package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MathUtil;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;
import net.minecraft.util.math.Vec3d;

public class HClip extends Command {
    public HClip() {
        super("HClip", "horizontal clipping", "hclip");
    }

    @Override
    protected void call(String[] args) {
        if(args.length > 0){
            if(NumberUtil.isNumeric(args[0])){
                final double num = Double.parseDouble(args[0]);
                final Vec3d dir = MathUtil.direction(mc.player.rotationYaw);
                if(mc.player.getRidingEntity() != null){
                    mc.player.getRidingEntity().setPosition(mc.player.getRidingEntity().posX + dir.x * num, mc.player.getRidingEntity().posY, mc.player.getRidingEntity().posZ + dir.z * num);
                } else {
                    mc.player.setPosition(mc.player.posX + dir.x * num, mc.player.posY, mc.player.posZ + dir.z * num);
                }
                MessageUtil.sendMessage("Teleported you " + ((num > 0) ? "forward" : "backward") + " " + num, MessageUtil.Color.GREEN);
            } else {
                MessageUtil.sendMessage("Unknown Number : " + args[0], MessageUtil.Color.RED);
            }
        }
    }
}
