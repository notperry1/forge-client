package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MathUtil;
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
            final StringSelection selection = new StringSelection(MathUtil.formatPlayerCoords(mc.player));
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            MessageUtil.sendMessage("Copied coordinates to clipboard", MessageUtil.Color.GREEN);
        }
    }
}
