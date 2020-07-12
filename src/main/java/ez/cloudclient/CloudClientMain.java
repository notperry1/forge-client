package ez.cloudclient;

import ez.cloudclient.command.CommandManager;
import ez.cloudclient.gui.HUD;
import ez.cloudclient.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;

import java.lang.reflect.InvocationTargetException;

@Mod(modid = CloudClientMain.MODID, name = CloudClientMain.NAME, version = CloudClientMain.VERSION)
public class CloudClientMain {
    public static final String MODID = "cloudclient";
    public static final String NAME = "Cloud Client";
    public static final String VERSION = "v1.1";
    public static final String FULLNAME = NAME + " " + VERSION;
    public static final CommandManager COMMAND_MANAGER = new CommandManager();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            ModuleManager.init();
            COMMAND_MANAGER.init();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        MinecraftForge.EVENT_BUS.register(new HUD());
        Display.setTitle(FULLNAME);
        System.out.println(NAME + " version " + VERSION + " loaded.");
    }
}
