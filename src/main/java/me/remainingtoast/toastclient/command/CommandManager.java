package me.remainingtoast.toastclient.command;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.commands.*;
import me.remainingtoast.toastclient.managers.HashMapManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager extends HashMapManager<String, Command> {

    private static boolean isPanicking = false;
    public static HashSet<Command> commandsSet = new HashSet<>();
    public static String commandPrefix;
    private HashMap<String, Command> aliasMap = new HashMap<>();

    public static String getCommandPrefix() {
        return commandPrefix;
    }

    @Override
    public void load() {
        super.load();
        init();
    }

    public void init() {
        aliasMap.clear();
        commandsSet.clear();
        register(new BlockHighlight(), new ClearChat(), new Coords(), new Drawn(), new Fov(), new HClip(), new Help(),
                new ListModule(), new Peek(), new Pitch(), new Prefix(), new Reload(), new Say(), new SignBook(), new ToggleModule(),
                new VClip(), new Yaw(), new GuiReset());
        MinecraftForge.EVENT_BUS.register(this);
        commandPrefix = ToastClient.PREFIX;
    }

    public void register(Command... commands) {
        for (Command command : commands) {
            commandsSet.add(command);
            include(command.getLabel().toLowerCase(), command);
            if (command.getAlias().length > 0) {
                for (String com : command.getAlias()) {
                    aliasMap.put(com.toLowerCase(), command);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(ClientChatEvent event) {
        if (event.getMessage().startsWith(commandPrefix)) {
            if(isPanicking) return;
            if (event.getMessage().length() > 1) {
                String firstArg = event.getMessage().replaceFirst(commandPrefix, "").split(" ")[0];
                HashSet<String> matchList = new HashSet<>();
                // from https://stackoverflow.com/a/366532
                Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
                Matcher regexMatcher = regex.matcher(event.getMessage().replaceFirst(commandPrefix + firstArg, ""));
                while (regexMatcher.find()) {
                    matchList.add(regexMatcher.group());
                }
                String[] argsList = matchList.toArray(new String[0]);
                for (Command command : commandsSet) {
                    if(firstArg.equalsIgnoreCase(command.getLabel())){
                        command.onRun(argsList);
                        break;
                    } else {
                        for (String alias : command.getAlias()) {
                            if (alias.equals(firstArg)) {
                                command.onRun(argsList);
                                break;
                            }
                        }
                    }
                }
            }
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            event.setCanceled(true);
        }
    }

    public void setPanicking(Boolean bool) {
        isPanicking = bool;
    }

    public boolean getPanicked() {
        return getPanicked();
    }
}

