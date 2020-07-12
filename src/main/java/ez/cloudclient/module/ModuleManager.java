package ez.cloudclient.module;

import ez.cloudclient.module.modules.DiscordRPC;
import ez.cloudclient.module.modules.exploits.AntiHunger;
import ez.cloudclient.module.modules.movement.ElytraFlight;
import ez.cloudclient.module.modules.movement.Flight;
import ez.cloudclient.module.modules.player.NoFall;
import ez.cloudclient.module.modules.render.FullBright;
import ez.cloudclient.setting.ModuleSettings;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import static ez.cloudclient.CloudClient.SETTINGS_MANAGER;

public class ModuleManager {
    public static final HashSet<Module> modules = new HashSet<>();

    public static void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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

        SETTINGS_MANAGER.loadSettings();
        for (Module module : ModuleManager.modules) {
            if ((boolean) module.getSettings().getSetting("enabled")) {
                module.enable();
            }
            if ((boolean) module.getSettings().getSetting("drawn")) {
                module.enableDrawn();
            }
        }
    }

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

}
