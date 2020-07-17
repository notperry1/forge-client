package me.remainingtoast.toastclient.command;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.commands.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {

    public static HashSet<Command> commands = new HashSet<>();
    public static String commandPrefix;

    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commands.clear();
        for (Class<? extends Command> aClass : new Reflections("ez.cloudclient.command.commands").getSubTypesOf(
                Command.class
        )) {
            Command command = aClass.getConstructor().newInstance();
            commands.add(command);
        }
        MinecraftForge.EVENT_BUS.register(this);
//        commands.add(new ClearChat());
//        commands.add(new Drawn());
//        commands.add(new Fov());
//        commands.add(new Help());
//        commands.add(new ListModule());
//        commands.add(new Panic());
//        commands.add(new Prefix());
//        commands.add(new ToggleModule());
//        commands.add(new Bind());
//        commands.add(new Say());
//        commands.add(new Coords());
        commandPrefix = ToastClient.PREFIX;
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(ClientChatEvent event) {
        if (event.getMessage().startsWith(commandPrefix)) {
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
                for (Command command : commands) {
                    for (String alias : command.aliases) {
                        if (alias.equals(firstArg)) {
                            command.call(argsList);
                            break;
                        }
                    }
                }
            }
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            event.setCanceled(true);
        }
    }
}

