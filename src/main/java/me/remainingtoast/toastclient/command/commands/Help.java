package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Help", description = "Shows all commands", aliases = {"help"}, usage = "")
public class Help extends Command {

    StringBuilder sb = new StringBuilder();
    Integer i = 0;

    @Override
    public void onRun(final String[] args) {
        if (mc.player == null) return;
        if (args.length < 1) {
            sb.replace(0, sb.capacity(), "");
            sb.append("Commands (" + CommandManager.commandsSet.size() + "): ");
            for (Command command : CommandManager.commandsSet) {
                i++;
                if (command.getName().equalsIgnoreCase("Help")) continue;
                sb.append(command.getName()).append(", ");
                if (CommandManager.commandsSet.size() == i) {
                    i = 0;
                    break;
                }
            }
            sb.replace(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1, "");
            MessageUtil.sendMessage(sb.toString(), MessageUtil.Color.GRAY);
        }
    }
}
