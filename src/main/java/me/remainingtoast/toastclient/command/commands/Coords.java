package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MathUtil;
import me.remainingtoast.toastclient.util.MessageUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

@CommandManifest(label = "Coords", description = "Copies coordinates to clipboard", aliases = {""}, usage = "")
public class Coords extends Command {

    @Override
    public void onRun(final String[] args) {
        if(args.length < 1){
            final StringSelection selection = new StringSelection(MathUtil.formatPlayerCoords(mc.player));
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            MessageUtil.sendMessage("Copied coordinates to clipboard", MessageUtil.Color.GREEN);
        }
    }
}
