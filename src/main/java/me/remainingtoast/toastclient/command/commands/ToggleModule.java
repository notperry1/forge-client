package me.remainingtoast.toastclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "ToggleModule", description = "Toggles module on and off", aliases = {"t"}, usage = "toggle <module>")
public class ToggleModule extends Command {

    @Override
    public void onRun(final String[] args) {
        if(args.length == 0){
            MessageUtil.sendMessage("Invalid Arguments", MessageUtil.Color.RED);
            return;
        }
        for(Module module : ToastClient.MODULE_MANAGER.getModules()){
            for(String s : module.getAlias()){
                if(args[0].equalsIgnoreCase(s)){
                    MessageUtil.sendMessage("Toggled " + module.getName() + (module.isEnabled() ? ChatFormatting.RED + " OFF" : ChatFormatting.GREEN + " ON"), MessageUtil.Color.GRAY);
                    module.toggle();
                }
                break;
            }
            if(args[0].equalsIgnoreCase(module.getName())){
                MessageUtil.sendMessage("Toggled " + module.getName() + (module.isEnabled() ? ChatFormatting.RED + " OFF" : ChatFormatting.GREEN + " ON"), MessageUtil.Color.GRAY);
                module.toggle();
            }
            break;
        }
        if(ToastClient.MODULE_MANAGER.getModuleByName(args[0]) == null){
            MessageUtil.sendMessage("Unrecognised Module \"" + args[0] +"\"", MessageUtil.Color.RED);
        }
    }
}

