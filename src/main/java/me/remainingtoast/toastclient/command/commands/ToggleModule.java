package me.remainingtoast.toastclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "ToggleModule", description = "Toggles module on and off", aliases = {"t"}, usage = "toggle <module>")
public class ToggleModule extends Command {

    @Override
    public void onRun(final String[] args) {
        boolean found = false;
        if(args.length == 0){
            MessageUtil.sendMessage("Invalid Arguments", MessageUtil.Color.RED);
            return;
        }
        final Module mod = ToastClient.MODULE_MANAGER.getAlias(args[0]);
        if(mod != null){
            found = true;
            MessageUtil.sendMessage("Toggled " + mod.getName() + (mod.isEnabled() ? ChatFormatting.RED + " OFF" : ChatFormatting.GREEN + " ON"), MessageUtil.Color.GRAY);
            mod.toggle();
        }
        if (!found) {
            MessageUtil.sendMessage("Module \"" + args[0] + "\" not found.", MessageUtil.Color.RED);
        }
    }
}

