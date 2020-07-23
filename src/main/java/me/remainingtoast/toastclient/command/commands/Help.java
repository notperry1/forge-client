package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Help", description = "Shows all commands", aliases = {"help"}, usage = "")
public class Help extends Command {

    StringBuilder sb = new StringBuilder();

    @Override
    public void onRun(final String[] args) {
        if (mc.player == null) return;
        if (args.length < 1) {
            sb.replace(0, sb.capacity(), "");
            sb.append("Commands (" + CommandManager.commandsSet.size() + "): ");
            for (Command command : CommandManager.commandsSet) {
                if (command.getName().equalsIgnoreCase("Help")) continue;
                sb.append(command.getName()).append(", ");
            }
            sb.replace(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1, "");
            MessageUtil.sendMessage(sb.toString(), MessageUtil.Color.GRAY);
        } else {
            for (Command command : CommandManager.commandsSet) {
                if (command.getName().equalsIgnoreCase(args[0])){
                    MessageUtil.sendMessage(command.toString(), MessageUtil.Color.GRAY);
                    return;
                }
            }
            MessageUtil.sendMessage("Unknown Command", MessageUtil.Color.RED);
        }
    }
}
