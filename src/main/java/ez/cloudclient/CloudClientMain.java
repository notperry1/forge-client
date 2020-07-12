package ez.cloudclient;

import ez.cloudclient.command.CommandManager;
import ez.cloudclient.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;

import java.lang.reflect.InvocationTargetException;

@Mod(modid = CloudClientMain.MODID, name = CloudClientMain.NAME, version = CloudClientMain.VERSION)
public class CloudClientMain {
    public static final String MODID = "cloudclient";
    public static final String NAME = "Cloud Client";
    public static final String VERSION = "1.0.0";
    public static final CommandManager COMMAND_MANAGER = new CommandManager();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            ModuleManager.init();
            COMMAND_MANAGER.init();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        Display.setTitle(NAME + " " + VERSION);
        System.out.println(NAME + " version " + VERSION + " loaded.");
    }
}
