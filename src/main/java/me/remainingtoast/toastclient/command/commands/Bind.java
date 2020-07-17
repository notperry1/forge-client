package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.setting.SettingsManager;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;


import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import org.lwjgl.input.Keyboard;

public class Bind extends Command {

    public Bind() {
        super("Bind", "Bind module to key", "bind", "b");
    }

    @Override
    protected void call(String[] args) {
        if (args.length > 0){
            if(args[0].equalsIgnoreCase("all")) {
                if(args[1].equalsIgnoreCase("none")) {
                    for (Module module : ModuleManager.getModules()) {
                        module.setKey(-1);
                        MessageUtil.sendMessage(module.getDisplayName() + " keybind set to NONE", MessageUtil.Color.GREEN);
                    }
                }
            } else {
                if(args.length > 1) {
                    for (Module module : ModuleManager.getModules()) {
                        System.out.println(args[0]);
                        if (args[0].equalsIgnoreCase(module.getName())) {
                            if (NumberUtil.isNumeric(args[1])) {
                                System.out.println(args[1]);
                                try {
                                    module.setKey(Integer.parseInt(args[1]));
                                    System.out.println(Integer.parseInt(args[1]));
                                    MessageUtil.sendMessage(module.getDisplayName() + " keybind set to " + Keyboard.getKeyName(Integer.parseInt(args[1])), MessageUtil.Color.GREEN);
                                    ToastClient.SETTINGS_MANAGER.updateSettings();
                                    break;
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Failed");
                                    break;
                                }
                            } else {
                                MessageUtil.sendMessage("Invalid Arguments, Please use Keycode from: http://legacy.lwjgl.org/javadoc/constant-values.html#org.lwjgl.input.Keyboard", MessageUtil.Color.RED);
                                break;
                            }
                        } else {
                            MessageUtil.sendMessage("Invalid Module \"" + args[0] + "\"", MessageUtil.Color.RED);
                            break;
                        }
                    }
                    if(args[0].equalsIgnoreCase("help")){
                        MessageUtil.sendMessage("Find Keycodes: http://legacy.lwjgl.org/javadoc/constant-values.html#org.lwjgl.input.Keyboard", MessageUtil.Color.GOLD);
                    }
                }
            }
        }
    }
}
