package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "ListModule", description = "Lists all the modules", aliases = {"list"}, usage = "list <category>")
public class ListModule extends Command {

    StringBuilder sb = new StringBuilder();
    Integer i = 0;

    @Override
    public void onRun(final String[] args) {
        if (mc.player == null) return;
        if (args.length == 0) {
            MessageUtil.sendMessage("List of categories:", MessageUtil.Color.GRAY);
            MessageUtil.sendMessage("  ALL", MessageUtil.Color.GRAY);
            for (Module.Category category : Module.Category.values()) {
                MessageUtil.sendMessage("  " + category.name(), MessageUtil.Color.GRAY);
            }
        } else {
            for (Module.Category category : Module.Category.values()) {
                if (category.name().equalsIgnoreCase(args[0])) {
                    MessageUtil.sendMessage("Modules in " + args[0] + ":", MessageUtil.Color.GRAY);
                    for (Module module : ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.valueOf(args[0].toUpperCase()))) {
                        MessageUtil.sendMessage("  " + module.getName() + ": " + module.getDesc(), MessageUtil.Color.GRAY);
                    }
                    return;
                } else if (args[0].equalsIgnoreCase("ALL")) {
                    sb.replace(0, sb.capacity(), "");
                    sb.append("Modules (" + ToastClient.MODULE_MANAGER.modulesSet.size() + "): ");
                    for (Module module : ToastClient.MODULE_MANAGER.modulesSet) {
                        i++;
                        sb.append(module.getName()).append(", ");
                        if (ToastClient.MODULE_MANAGER.modulesSet.size() == i) {
                            i = 0;
                            break;
                        }
                    }
                    sb.replace(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1, "");
                    MessageUtil.sendMessage(sb.toString(), MessageUtil.Color.GRAY);
                    sb.replace(0, sb.capacity(), "");
                    return;
                }
            }
            MessageUtil.sendMessage(args[0] + " is not a valid category.", MessageUtil.Color.RED);
        }
    }
}
