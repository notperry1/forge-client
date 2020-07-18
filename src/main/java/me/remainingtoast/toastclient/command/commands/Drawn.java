package me.remainingtoast.toastclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "Drawn", description = "Toggle modules display in arraylist", aliases = {"draw"}, usage = "")
public class Drawn extends Command {

    @Override
    public void onRun(final String[] args) {
        if(args.length == 0){
            MessageUtil.sendMessage("Invalid Arguments", MessageUtil.Color.RED);
            return;
        }
        for(Module module : ToastClient.MODULE_MANAGER.getModules()){
            for(String s : module.getAlias()){
                if(args[0].equalsIgnoreCase(s) || args[0].equalsIgnoreCase(module.getName())){
                    MessageUtil.sendMessage(module.getName() + " now being"  + (module.isDrawn() ? ChatFormatting.RED + " HIDDEN" : ChatFormatting.GREEN + " DRAWN"), MessageUtil.Color.GRAY);
                    module.toggleDrawn();
                }else{
                    MessageUtil.sendMessage("Failed to find Module \"" + args[0] + "\" ", MessageUtil.Color.RED);
                    break;
                }
                break;
            }
        }
    }
}
