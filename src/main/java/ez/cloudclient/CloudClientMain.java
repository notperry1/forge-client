package ez.cloudclient;

import ez.cloudclient.module.ModuleManager;
import ez.cloudclient.module.modules.render.FullBright;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.lang.reflect.InvocationTargetException;

@Mod(modid = CloudClientMain.MODID, name = CloudClientMain.NAME, version = CloudClientMain.VERSION)
public class CloudClientMain {
    public static final String MODID = "cloudclient";
    public static final String NAME = "Cloud Client";
    public static final String VERSION = "1.0.0";
    public static final String APP_ID = "731393813104558122";
    public static final String FULLNAME = "Cloud Client " + VERSION;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            ModuleManager.init();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        Display.setTitle(NAME + " " + VERSION);
        System.out.println(NAME + " version " + VERSION + " loaded.");
        FullBright fullBright = ModuleManager.getModuleByClass(FullBright.class);
        if (fullBright != null) {
            fullBright.enable();
        }
    }
}
