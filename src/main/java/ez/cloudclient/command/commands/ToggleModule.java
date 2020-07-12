package ez.cloudclient.command.commands;

import ez.cloudclient.command.Command;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;
import org.lwjgl.util.Color;

public class ToggleModule extends Command {

    public ToggleModule() {
        super("Module Toggle", "Toggles modules on and off.", "t", "toggle");
    }

    @Override
    public void call(String[] args) {
        if (args.length >= 1) {
            if (args[0] != null) {
                for (Module module : ModuleManager.modules) {
                    String moduleName = args[0].replaceAll(" ", "").toLowerCase();
                    if (module.displayName.equals(moduleName) || module.name.equals(moduleName)) {
                        module.toggle();
                        return;
                    } else {
                        mc.player.sendChatMessage(Color.RED + moduleName + " is not a module!");
                    }
                }
            } else {
                mc.player.sendChatMessage(Color.RED + "Invalid Arguments!");
            }
        }
    }
}

