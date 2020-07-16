package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.util.MessageUtil;

public class Drawn extends Command {

    public Drawn() {
        super("Drawn", "Shows module in arraylist", "draw", "drawn");
    }

    @Override
    public void call(String[] args) {
        if (args.length >= 1) {
            if (args[0] != null) {
                for (Module module : ModuleManager.modules) {
                    String moduleName = args[0].replaceAll(" ", "");
                    Module mod = ModuleManager.getModuleByName(moduleName.toLowerCase());
                    if (mod != null) {
                        mod.toggleDrawn();
                        if (mod.isDrawn()) {
                            MessageUtil.sendMessage(mod.getDisplayName() + " is now being drawn.", MessageUtil.Color.GREEN);
                        } else {
                            MessageUtil.sendMessage(mod.getDisplayName() + " is now not being drawn.", MessageUtil.Color.RED);
                        }
                    } else {
                        MessageUtil.sendMessage(moduleName + " is not a module!", MessageUtil.Color.RED);
                    }
                    break;
                }
            } else {
                MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
            }
        } else {
            MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
        }
    }
}
