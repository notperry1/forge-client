package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;


import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;

public class Bind extends Command {

    public Bind() {
        super("Bind", "Bind module to key", "bind", "b");
    }

    @Override
    protected void call(String[] args) {
        if (args.length > 0){
            Module mod = ModuleManager.getModuleByName(args[0]);
            if(args[0].equalsIgnoreCase("all")) {
                if(args[1].equalsIgnoreCase("none")) {
                    for (Module module : ModuleManager.getModules()) {
                        module.setKey(-1);
                        MessageUtil.sendMessage(module.getDisplayName() + " keybind set to NONE", MessageUtil.Color.GREEN);
                    }
                }
            } else if(args[0].equalsIgnoreCase("help")){

            } else if (mod != null){
                if(NumberUtil.isNumeric(args[1])){
                    try {
                        mod.setKey(Integer.parseInt(args[1]));
                        System.out.println(Integer.parseInt(args[1]));
                        MessageUtil.sendMessage(mod.getDisplayName() + " keybind set to " + args[1], MessageUtil.Color.GREEN);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Failed");
                    }
                }
            }
        }
    }
}
