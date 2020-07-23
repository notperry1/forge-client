package me.remainingtoast.toastclient.module.modules.misc;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.TransferableImage;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ScreenshotEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

@ModuleManifest(label = "ClipboardScreenshot", category = Module.Category.MISC, description = "Save screenshots to clipboard")
public class ClipboardScreenshot extends Module implements ClipboardOwner {

    @SubscribeEvent
    public void onScreenshot(ScreenshotEvent event){
        TransferableImage trans = new TransferableImage(event.getImage());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(trans, this);
//        MessageUtil.sendMessage("Copied Screenshot to Clipboard!", MessageUtil.Color.GREEN);
        event.setResultMessage(new TextComponentString(MessageUtil.CHAT_PREFIX + TextFormatting.GREEN + "Copied screenshot to clipboard!"));
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Lost Clipboard Ownership");
    }
}
