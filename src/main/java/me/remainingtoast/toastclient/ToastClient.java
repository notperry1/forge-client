package me.remainingtoast.toastclient;

import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.setting.SettingsManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.lang.reflect.InvocationTargetException;

@Mod(modid = ToastClient.MODID, name = ToastClient.NAME, version = ToastClient.VERSION)

public class ToastClient {

    public static final String MODID = "toastclient";
    public static final String NAME = "Toast Client";
    public static final String VERSION = "1"; //putting a v before this looks weird in the mod menu
    public static final String APP_ID = "";
    public static final String FULLNAME = NAME + " b" + VERSION;
    public static final String CLOUDCLIENT_CONFIGFILE = "ToastClientConfig.json";
    public static final Logger log = LogManager.getLogger("Toast Client");
    public static final CommandManager COMMAND_MANAGER = new CommandManager();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final SettingsManager SETTINGS_MANAGER = new SettingsManager();
    public static String PREFIX = ".";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle(FULLNAME);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // new AuthUtil().auth();
        ModuleManager.init();
        COMMAND_MANAGER.init();
        MinecraftForge.EVENT_BUS.register(MODULE_MANAGER);
        MinecraftForge.EVENT_BUS.register(COMMAND_MANAGER);
        // ASCII.printFancyConsoleMSG();
        System.out.println(FULLNAME + " loaded.");
    }
}
