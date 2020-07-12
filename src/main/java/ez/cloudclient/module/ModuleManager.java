package ez.cloudclient.module;

import ez.cloudclient.module.modules.exploits.AntiHunger;
import ez.cloudclient.module.modules.movement.Flight;
import ez.cloudclient.module.modules.movement.Speed;
import ez.cloudclient.module.modules.player.NoFall;
import ez.cloudclient.module.modules.render.FullBright;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

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
        modules.add(new Speed());
    }

    public static <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module current : modules) {
            if (current.getClass() == clazz) return (T) current;
        }
        return null;
    }

    public static Module getModuleByName(String name) {
        for (Module current : modules) {
            if (current.name.equals(name) || current.displayName.equals(name)) return current;
        }
        return null;
    }



    public static HashSet<Module> getModulesInCat(Module.Category category) {
        HashSet<Module> modulesInCategory = new HashSet<>();
        for (Module current : modules) {
            if (current.category == category) modulesInCategory.add(current);
        }
        return modulesInCategory;
    }
}
