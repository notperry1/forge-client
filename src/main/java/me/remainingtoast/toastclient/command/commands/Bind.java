package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;
import org.lwjgl.input.Keyboard;

@CommandManifest(label = "Bind", description = "Bind module to key", aliases = {"b","bind"})
public class Bind extends Command {

    @Override
    public void onRun(final String[] args) {
        boolean found = false;
        if(args.length <= 1){
            MessageUtil.sendMessage("Invalid Arguments", MessageUtil.Color.RED);
            return;
        }
        final Module m = ToastClient.MODULE_MANAGER.getAlias(args[0]);
        if(m != null){
            found = true;
            if(NumberUtil.isNumeric(args[1])){
                int key = Integer.parseInt(args[1]);
                m.setNewKey(key);
                MessageUtil.sendMessage("Bound " + m.getName() + " to " + Keyboard.getKeyName(key), MessageUtil.Color.GREEN);
                return;
            }else{
                try{
                    MessageUtil.sendMessage("Keycode for \""+args[1]+"\" is " + Keyboard.getKeyIndex(args[1].toUpperCase()), MessageUtil.Color.YELLOW);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
        if(!found){
            MessageUtil.sendMessage("Failed to find Module \"" + args[0] + "\" ", MessageUtil.Color.RED);
        }
    }
}
