package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

public class Coords extends Command {
    public Coords() {
        super("Coords", "Copies coordinates to clipboard", "coords", "coordinates", "coord"," coordinate");
    }

    @Override
    protected void call(String[] args) {
        if(args.length < 1){
            final DecimalFormat format = new DecimalFormat("#.#");
            final String x = format.format(mc.player.posX);
            final String y = format.format(mc.player.posY);
            final String z = format.format(mc.player.posZ);
            String coords = x + ", " + y + ", " + z;
            final StringSelection selection = new StringSelection(coords);
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            MessageUtil.sendMessage("Copied coordinates to clipboard", MessageUtil.Color.GREEN);
        }
    }
}
