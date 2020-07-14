package ez.cloudclient.module;

import ez.cloudclient.module.modules.DiscordRPC;
import ez.cloudclient.module.modules.combat.*;
import ez.cloudclient.module.modules.exploits.*;
import ez.cloudclient.module.modules.misc.*;
import ez.cloudclient.module.modules.movement.*;
import ez.cloudclient.module.modules.player.*;
import ez.cloudclient.module.modules.render.*;
import ez.cloudclient.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

import static ez.cloudclient.CloudClient.SETTINGS_MANAGER;
import static ez.cloudclient.util.MessageUtil.sendRawMessage;

public class ModuleManager {

    public static final HashSet<Module> modules = new HashSet<>();

    public static void init() {
        modules.clear();
        modules.add(new NoFall());
        modules.add(new FullBright());
        modules.add(new Flight());
        modules.add(new AntiHunger());
        modules.add(new ElytraFlight());
        modules.add(new DiscordRPC());
        modules.add(new KillAura());
        modules.add(new Sprint());
        modules.add(new AutoTotem());
        modules.add(new FastStop());
        modules.add(new PlayerESP());
        modules.add(new CrystalAura());
        modules.add(new AutoRespawn());
        modules.add(new CoordinateLogger());

        SETTINGS_MANAGER.loadSettings();
        for (Module module : ModuleManager.modules) {
            if (module.getSettings().getSetting("Enabled", BooleanSetting.class).getValue()) {
                module.enable();
            }
            if (module.getSettings().getSetting("Drawn", BooleanSetting.class).getValue()) {
                module.enableDrawn();
            }
        }
    }

    private HashSet<Integer> unReleased = new HashSet<>();
    @SubscribeEvent
    public void inputEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (unReleased.contains(key)) {
            unReleased.remove(key);
            return;
        }
        for (Module module : modules) {
            if (module.getKey() == key) {
                module.toggle();
                unReleased.add(key);
            }
        }
    }

    // there is a check in the method for whether or not it is the correct class
    @SuppressWarnings("unchecked")
    public static <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module current : modules) {
            if (current.getClass() == clazz) return (T) current;
        }
        return null;
    }

    public static Module getModuleByName(String name) {
        for (Module current : modules) {
            if (current.getName().equals(name) || current.getDisplayName().equals(name)) return current;
        }
        return null;
    }

    public static HashSet<Module> getModulesInCat(Module.Category category) {
        HashSet<Module> modulesInCategory = new HashSet<>();
        for (Module current : modules) {
            if (current.getCategory() == category) modulesInCategory.add(current);
        }
        return modulesInCategory;
    }

    public HashSet<Module> getModules() {
        return modules;
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (Minecraft.getMinecraft().player != null) {
            for (Module module : modules) {
                if (module.isEnabled()) module.onTick();
            }
        }
    }
}
