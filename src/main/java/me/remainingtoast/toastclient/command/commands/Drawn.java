package me.remainingtoast.toastclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Drawn", description = "Toggle modules display in arraylist", aliases = {"draw"}, usage = "")
public class Drawn extends Command {

    @Override
    public void onRun(final String[] args) {
        if (args.length >= 1) {
            if (args[0] != null) {
                String moduleName = args[0].replaceAll(" ", "");
                Module mod = ToastClient.INSTANCE.getModuleManager().getModuleByName(moduleName.toLowerCase());
                if (mod != null) {
                    MessageUtil.sendMessage(mod.getName() + " now being"  + (mod.isHidden() ? ChatFormatting.RED + " HIDDEN" : ChatFormatting.GREEN + " DRAWN"), MessageUtil.Color.GRAY);
                    mod.toggleHidden();
                } else {
                    MessageUtil.sendMessage(moduleName + " is not a module!", MessageUtil.Color.RED);
                }
            } else {
                MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
            }
        } else {
            MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
        }
    }
}
