package me.remainingtoast.toastclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "ToggleModule", description = "Toggles module on and off", aliases = {"t"}, usage = "toggle <module>")
public class ToggleModule extends Command {

    @Override
    public void onRun(final String[] args) {
        if (args.length >= 1) {
            if (args[0] != null) {
                try{
                    String moduleName = args[0].replaceAll(" ", "");
                    Module mod = ToastClient.moduleManager.getModuleByName(moduleName.toLowerCase());
                    if (mod != null) {
                        MessageUtil.sendMessage("Toggled " + mod.getName() + (mod.isEnabled() ? ChatFormatting.RED + " OFF" : ChatFormatting.GREEN + " ON"), MessageUtil.Color.GRAY);
                        mod.toggle();
                    } else {
                        MessageUtil.sendMessage(moduleName + " is not a module!", MessageUtil.Color.RED);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
            }
        } else {
            MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
        }
    }
}

