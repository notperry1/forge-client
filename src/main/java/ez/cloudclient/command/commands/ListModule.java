package ez.cloudclient.command.commands;

import ez.cloudclient.command.Command;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;
import ez.cloudclient.util.MessageUtil;

public class ListModule extends Command {

    StringBuilder sb = new StringBuilder();
    Integer i = 0;

    public ListModule() {
        super("ListModule", "Lists all the modules", "list", "modules");
    }

    @Override
    public void call(String[] args) {
        if (mc.player == null) return;
        if (args.length == 0) {
            sb.replace(0, sb.capacity(), "");
            for (Module module : ModuleManager.modules) {
                i++;
                sb.append(module.getDisplayName()).append(", ");
                if (ModuleManager.modules.size() == i) {
                    i = 0;
                    break;
                }
            }
            sb.replace(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1, "");
//            sb.append("\n" + TextFormatting.GRAY + " Do " + TextFormatting.GRAY + CommandManager.commandPrefix + TextFormatting.GRAY + "list category to get a list of categories");
            MessageUtil.sendMessage(sb.toString(), MessageUtil.Color.GRAY);
        }
    }
}
