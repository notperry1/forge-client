package ez.cloudclient.module;

import ez.cloudclient.module.modules.DiscordRPC;
import ez.cloudclient.module.modules.combat.AutoTotem;
import ez.cloudclient.module.modules.combat.KillAura;
import ez.cloudclient.module.modules.exploits.AntiHunger;
import ez.cloudclient.module.modules.movement.ElytraFlight;
import ez.cloudclient.module.modules.movement.Flight;
import ez.cloudclient.module.modules.movement.Sprint;
import ez.cloudclient.module.modules.player.NoFall;
import ez.cloudclient.module.modules.render.FullBright;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashSet;

import static ez.cloudclient.CloudClient.SETTINGS_MANAGER;

public class ModuleManager {

    public static final HashSet<Module> modules = new HashSet<>();

    public static void init() { //throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        modules.clear();
        /* TODO: Make work
        for (Class<? extends Module> aClass : new Reflections("ez.cloudclient.module.modules").getSubTypesOf(
                Module.class
        )) {
            Module module = aClass.getConstructor().newInstance();
            modules.add(module);
        }
         */
        modules.add(new NoFall());
        modules.add(new FullBright());
        modules.add(new Flight());
        modules.add(new AntiHunger());
        modules.add(new ElytraFlight());
        modules.add(new DiscordRPC());
        modules.add(new KillAura());
        modules.add(new Sprint());
        modules.add(new AutoTotem());

        SETTINGS_MANAGER.loadSettings();
        for (Module module : ModuleManager.modules) {
            if (module.getSettings().getBoolean("Enabled")) {
                module.enable();
            }
            if (module.getSettings().getBoolean("Drawn")) {
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
