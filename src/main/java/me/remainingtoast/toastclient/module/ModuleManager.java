package me.remainingtoast.toastclient.module;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.managers.HashMapManager;
import me.remainingtoast.toastclient.module.modules.combat.AutoTotem;
import me.remainingtoast.toastclient.module.modules.combat.CrystalAura;
import me.remainingtoast.toastclient.module.modules.combat.KillAura;
import me.remainingtoast.toastclient.module.modules.exploits.AntiHunger;
import me.remainingtoast.toastclient.module.modules.gui.ClickGui;
import me.remainingtoast.toastclient.module.modules.gui.HUD;
import me.remainingtoast.toastclient.module.modules.gui.HudEditor;
import me.remainingtoast.toastclient.module.modules.misc.CoordinateLogger;
import me.remainingtoast.toastclient.module.modules.misc.DiscordRPC;
import me.remainingtoast.toastclient.module.modules.movement.ElytraFlight;
import me.remainingtoast.toastclient.module.modules.movement.Flight;
import me.remainingtoast.toastclient.module.modules.movement.Sprint;
import me.remainingtoast.toastclient.module.modules.player.AutoReconnect;
import me.remainingtoast.toastclient.module.modules.player.AutoRespawn;
import me.remainingtoast.toastclient.module.modules.player.NoFall;
import me.remainingtoast.toastclient.module.modules.render.BlockHighlight;
import me.remainingtoast.toastclient.module.modules.render.FullBright;
import me.remainingtoast.toastclient.module.modules.render.PlayerESP;
import me.remainingtoast.toastclient.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

public class ModuleManager extends HashMapManager<String, Module> {

    public final HashSet<Module> modulesSet = new HashSet<>();
    private final HashSet<Integer> unReleasedKeys = new HashSet<>();

    @Override
    public void load() {
        super.load();
        modulesSet.clear();
        register(new ClickGui(), new NoFall(), new HUD(), new FullBright(), new Flight(), new AntiHunger(), new ElytraFlight(), new DiscordRPC(), new KillAura(),
                new Sprint(), new AutoTotem(), new PlayerESP(), new CrystalAura(), new AutoRespawn(), new AutoReconnect(), new CoordinateLogger(),
                new BlockHighlight(), new HudEditor());
        ToastClient.SETTINGS_MANAGER.loadSettings();
        for (Module module : ToastClient.MODULE_MANAGER.modulesSet) {
            if (module.getSettings().getSetting("Enabled", BooleanSetting.class).getValue()) {
                module.enable();
            }
            if (module.getSettings().getSetting("Drawn", BooleanSetting.class).getValue()) {
                module.enableDrawn();
            }
        }
    }

    public void register(Module... modules) {
        for (Module cheat : modules) {
            if (cheat.getName() != null)
                include(cheat.getName().toLowerCase(), cheat);
            modulesSet.add(cheat);
        }
    }

    // there is a check in the method for whether or not it is the correct class
    @SuppressWarnings("unchecked")
    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module current : modulesSet) {
            if (current.getClass() == clazz) return (T) current;
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module current : modulesSet) {
            if (current.getName().equals(name)) return current;
        }
        return null;
    }

    public HashSet<Module> getModulesInCat(Module.Category category) {
        HashSet<Module> modulesInCategory = new HashSet<>();
        for (Module current : modulesSet) {
            if (current.getCategory() == category) modulesInCategory.add(current);
        }
        return modulesInCategory;
    }

    @SubscribeEvent
    public void inputEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (unReleasedKeys.contains(key)) {
            unReleasedKeys.remove(key);
            return;
        }
        for (Module module : modulesSet) {
            if (module.getKey() == key) {
                module.toggle();
                unReleasedKeys.add(key);
            }
        }
    }

    public HashSet<Module> getModules() {
        return modulesSet;
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (Minecraft.getMinecraft().player != null) {
            for (Module module : modulesSet) {
                if (module.isEnabled()) module.onTick();
            }
        }
    }
}
