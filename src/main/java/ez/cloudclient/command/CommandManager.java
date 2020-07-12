package ez.cloudclient.command;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {
    public static HashSet<Command> commands = new HashSet<>();
    public static String commandPrefix = ".";

    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commands.clear();
        for (Class<? extends Command> aClass : new Reflections("ez.cloudclient.command.commands").getSubTypesOf(
                Command.class
        )) {
            Command command = aClass.getConstructor().newInstance();
            commands.add(command);
        }
        MinecraftForge.EVENT_BUS.register(this);
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

