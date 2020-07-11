package ez.cloudclient;


import ez.cloudclient.discord.DiscordRPC;
import ez.cloudclient.events.EventProcessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = CloudClientMain.MODID, name = CloudClientMain.NAME, version = CloudClientMain.VERSION)
public class CloudClientMain {
    public static final String MODID = "cloudclient";
    public static final String NAME = "Cloud Client";
    public static final String VERSION = "1.0";
    public static final String APP_ID = "731393813104558122";
    public static final String FULLNAME = "Cloud Client " + VERSION;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle(NAME + " " + VERSION);
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
        ez.cloudclient.ModuleManager.INSTANCE.init();
        System.out.println(NAME + " " + VERSION + " on Top.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DiscordRPC.start();
    }

}
