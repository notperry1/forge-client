package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.modules.gui.ClickGui;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "GuiReset", description = "Reset Gui Location", aliases = {"fixgui"})
public class GuiReset extends Command {

    @Override
    public void onRun(String[] args) {
        if(args.length == 0){
            ClickGui.clickGui.init();
            ToastClient.INSTANCE.getModuleManager().getModuleByClass(ClickGui.class).disable();
            MessageUtil.sendMessage("Reset Gui Location!", MessageUtil.Color.GREEN);
        }
    }
}
