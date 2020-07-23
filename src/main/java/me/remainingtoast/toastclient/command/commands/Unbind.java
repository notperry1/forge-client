package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Unbind", description = "Unbind module", usage = "unbind <module> | unbind all")
public class Unbind extends Command {

    @Override
    public void onRun(String[] args) {
        if (args.length >= 1) {
            Module mod = ToastClient.moduleManager.getModuleByName(args[0]);
            if (args[0].equalsIgnoreCase("all")) {
                for (Module module : ToastClient.moduleManager.getModules()) {
                    if(module.getKey() == -1){MessageUtil.sendMessage(module.getName() + " doesn't have a bind to remove!", MessageUtil.Color.RED); return; }
                    module.setNewKey(-1);
                    MessageUtil.sendMessage(module.getName() + " keybind set to NONE", MessageUtil.Color.GREEN);
                }
            } else if(mod != null){
                if(mod.getKey() == -1){MessageUtil.sendMessage(mod.getName() + " doesn't have a bind to remove!", MessageUtil.Color.RED); }
                mod.setNewKey(-1);
                MessageUtil.sendMessage(mod.getName() + " keybind set to NONE", MessageUtil.Color.GREEN);
            }
        }
    }
}
