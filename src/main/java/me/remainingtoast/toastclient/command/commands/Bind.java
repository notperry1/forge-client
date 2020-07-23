package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

@CommandManifest(label = "Bind", description = "Bind module to key", usage = "bind <module>")
public class Bind extends Command {

    private int key;
    private boolean listening;

    @SubscribeEvent
    public void inputEvent(InputEvent.KeyInputEvent event) {
        if(listening) key = Keyboard.getEventKey();
    }

    @Override
    public void onRun(String[] args) {
        if(args.length >= 1){
            final String finalName = args[0].toLowerCase();
            ToastClient.moduleManager.modulesSet.stream().filter(module -> module.getName().equalsIgnoreCase(finalName) || Arrays.asList(module.getAlias()).contains(finalName)).forEach(module -> {
                MessageUtil.sendMessage("Press the key you want to bind " + module.getName() + " to.", MessageUtil.Color.GREEN);
                listening = true;
                try{
                    module.setNewKey(key);
//                    MessageUtil.sendMessage("Bound " + module.getName() + " to " + Keyboard.getKeyName(key), MessageUtil.Color.GREEN);
                    MessageUtil.sendMessage("Bound " + module.getName(), MessageUtil.Color.GREEN);
                    listening = false;
                } catch (Exception e) {
                    MessageUtil.sendMessage("Failed to bind", MessageUtil.Color.RED);
                    listening = false;
                    e.printStackTrace();
                }
                listening = false;
            });
        }
    }
}

