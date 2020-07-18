package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

import java.awt.*;

@CommandManifest(label = "BlockHighlight", description = "", aliases = {"bh", "bhighlight", "blockh"}, usage = "blockhighlight <red> <green> <blue> <width>")
public class BlockHighlight extends Command {

    private Color color;
    private Float width;

    @Override
    public void onRun(final String[] args) {
        if(args.length == 0){
            MessageUtil.sendMessage("Invalid Arguments, Usage: " + this.getUsage(), MessageUtil.Color.RED);
        }
        /*
        else if(args[1] != null && args[2] != null && args[3] != null && args[4] != null || args[1] != null && args[2] != null && args[3] != null) {
            if(NumberUtil.isNumeric(args[1]) && NumberUtil.isNumeric(args[2]) && NumberUtil.isNumeric(args[3])){
                color = new Color(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                MessageUtil.sendMessage("BlockHighlight Color set to Red: " + Integer.parseInt(args[1]) + "Green: " + Integer.parseInt(args[1]) + "Blue: " + Integer.parseInt(args[1]), MessageUtil.Color.GREEN);
                me.remainingtoast.toastclient.module.modules.render.BlockHighlight.color = color;
                return;
            }
            if(NumberUtil.isNumeric(args[1]) && NumberUtil.isNumeric(args[2]) && NumberUtil.isNumeric(args[3]) && NumberUtil.isNumeric(args[4])){
                color = new Color(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                width = Float.parseFloat(args[4]);
                me.remainingtoast.toastclient.module.modules.render.BlockHighlight.color = color;
                me.remainingtoast.toastclient.module.modules.render.BlockHighlight.width = width;
                MessageUtil.sendMessage("BlockHighlight Color set to Red: " + Integer.parseInt(args[1]) + "Green: " + Integer.parseInt(args[1]) + "Blue: " + Integer.parseInt(args[1]) + "Width: " + width, MessageUtil.Color.GREEN);
            }
        }else{
            MessageUtil.sendMessage("Invalid Arguments, Usage: " + CommandManager.commandPrefix + "blockhighlight <red> <green> <blue> <width>", MessageUtil.Color.RED);
        }
         */
    }
}
