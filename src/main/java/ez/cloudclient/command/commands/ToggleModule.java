package ez.cloudclient.command.commands;

import ez.cloudclient.command.Command;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;

public class ToggleModule extends Command {

    public ToggleModule() {
        super("Module Toggler", "Toggles modules on and off.", "t", "toggle");
    }

    @Override
    public void call(String[] args) {
        if (args[0] != null) {
            for (Module module : ModuleManager.modules) {
                String moduleName = args[0].replaceAll(" ", "").toLowerCase();
                if (module.displayName.equals(moduleName) || module.name.equals(moduleName)) {
                    module.toggle();
                    return;
                }
            }
        }
    }
}

