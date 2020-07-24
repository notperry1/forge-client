package me.remainingtoast.toastclient;

import me.kix.lotus.property.manage.PropertyManager;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.module.modules.gui.ClickGui;
import me.remainingtoast.toastclient.util.ASCII;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.File;


@Mod(modid = ToastClient.MODID, name = ToastClient.NAME, version = ToastClient.VERSION)
public class ToastClient {

    public static ToastClient INSTANCE = new ToastClient();
    public static final String MODID = "toastclient";
    public static final String NAME = "Toast Client";
    public static final String VERSION = "1"; //putting letters before this looks weird in the mod menu
    public static final String FULLVERSION = "b1";
    public static final String APP_ID = "669916916290420736"; //For Discord Presence
    public static final String FULLNAME = NAME +" "+ FULLVERSION;
    public static final String CONFIGFILE = "ToastClientConfig.json";
    public static final Logger log = LogManager.getLogger("Toast Client");

    private static File directory = new File(Minecraft.getMinecraft().mcDataDir, NAME.replaceAll(" ", ""));

    public static final ModuleManager moduleManager = new ModuleManager(new File(directory, "modules"));
    private CommandManager commandManager = new CommandManager();
    private PropertyManager PROPERTY_MANAGER = new PropertyManager();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle(FULLNAME);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (!directory.exists())
            directory.mkdir();
        moduleManager.load();
        commandManager.load();
        ClickGui.clickGui.init();
        MinecraftForge.EVENT_BUS.register(moduleManager);
        MinecraftForge.EVENT_BUS.register(commandManager);
        ASCII.printFancyConsoleMSG();
        System.out.println(FULLNAME + " loaded.");

//        LoginUtil.loginCracked("RemainingToast");

        //Keep Last
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void shutdown(){
        moduleManager.unload();
    }

    public PropertyManager getPropertyManager() {
        return this.PROPERTY_MANAGER;
    }

}
