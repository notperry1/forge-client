package ez.cloudclient.command.commands;

import ez.cloudclient.command.Command;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;
import ez.cloudclient.util.MessageUtil;
import net.minecraft.util.text.TextComponentString;
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
                    String moduleName = args[0].replaceAll(" ", "");
                    Module mod = ModuleManager.getModuleByName(moduleName.toLowerCase());
                    if(mod != null){
                        mod.toggle();
                        MessageUtil.sendMessage(mod.displayName + " has been toggled.", MessageUtil.Color.GREEN);
                    } else {
                        MessageUtil.sendMessage(moduleName + " is not a module!", MessageUtil.Color.RED);
                    }
                    break;
                    /*
                    if (module.displayName.equals(moduleName.toLowerCase()) || module.name.equals(moduleName.toLowerCase())) {
                        module.toggle();
                        mc.player.sendMessage(new TextComponentString(module.displayName + " has been toggled and is now set to: " + module.isEnabled()));
                    } else {
                        mc.player.sendMessage(new TextComponentString(moduleName + " is not a module!"));
                    }
                    break;
                     */
                }
            } else {
                MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
            }
        } else {
            MessageUtil.sendMessage("Invalid Arguments!", MessageUtil.Color.RED);
        }
    }
}

