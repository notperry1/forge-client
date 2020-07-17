package me.remainingtoast.toastclient.module;

import me.remainingtoast.toastclient.module.modules.DiscordRPC;
import me.remainingtoast.toastclient.module.modules.combat.AutoTotem;
import me.remainingtoast.toastclient.module.modules.combat.CrystalAura;
import me.remainingtoast.toastclient.module.modules.combat.KillAura;
import me.remainingtoast.toastclient.module.modules.exploits.AntiHunger;
import me.remainingtoast.toastclient.module.modules.misc.CoordinateLogger;
import me.remainingtoast.toastclient.module.modules.movement.ElytraFlight;
import me.remainingtoast.toastclient.module.modules.movement.FastStop;
import me.remainingtoast.toastclient.module.modules.movement.Flight;
import me.remainingtoast.toastclient.module.modules.movement.Sprint;
import me.remainingtoast.toastclient.module.modules.player.AutoReconnect;
import me.remainingtoast.toastclient.module.modules.player.AutoRespawn;
import me.remainingtoast.toastclient.module.modules.player.NoFall;
import me.remainingtoast.toastclient.module.modules.render.FullBright;
import me.remainingtoast.toastclient.module.modules.render.HUD;
import me.remainingtoast.toastclient.module.modules.render.PlayerESP;
import me.remainingtoast.toastclient.setting.settings.BooleanSetting;
import me.remainingtoast.toastclient.ToastClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

public class ModuleManager {

    public static final HashSet<Module> modules = new HashSet<>();
    private final HashSet<Integer> unReleased = new HashSet<>();

    public static void init() {
        modules.clear();
        modules.add(new NoFall());
        modules.add(new HUD());
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
        modules.add(new AutoReconnect());
        modules.add(new CoordinateLogger());

        ToastClient.SETTINGS_MANAGER.loadSettings();
        for (Module module : ModuleManager.modules) {
            if (module.getSettings().getSetting("Enabled", BooleanSetting.class).getValue()) {
                module.enable();
            }
            if (module.getSettings().getSetting("Drawn", BooleanSetting.class).getValue()) {
                module.enableDrawn();
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

    public static HashSet<Module> getModules() {
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
