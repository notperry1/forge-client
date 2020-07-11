package ez.cloudclient.module;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class ModuleManager {
    private static final HashSet<Module> modules = new HashSet<>();

    public static void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        modules.clear();
        for (Class<? extends Module> aClass : new Reflections("ez.cloudclient.module.modules").getSubTypesOf(
                Module.class
        )) {
            Module module = aClass.getConstructor().newInstance();
            modules.add(module);
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
