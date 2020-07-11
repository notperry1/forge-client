package ez.cloudclient.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;


public class EventManager<T extends Event> {
    private final HashMap<Map.Entry<Class<T>, Method>, Class> registeredMethods = new HashMap<>();

    public void register(Class<T> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {e
                if (method.getParameterTypes().length == 1) {
                    if (method.getParameterTypes()[0].isAssignableFrom(Event.class)) {
                        registeredMethods.put(new AbstractMap.SimpleEntry<>(clazz, method), method.getParameters()[0].getClass());
                    }
                }
            }
        }
    }

    public void unRegister(Class<T> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                if (method.getParameterTypes().length == 1) {
                    if (method.getParameterTypes()[0].isAssignableFrom(Event.class)) {
                        registeredMethods.remove(new AbstractMap.SimpleEntry<>(clazz, method));
                    }
                }
            }
        }
    }

    public void post(Class<T> event) {
        for (Map.Entry<Map.Entry<Class<T>, Method>, Class> registeredMethod : registeredMethods.entrySet()) {
            if (registeredMethod.getValue().getComponentType() != event.getComponentType()) continue;
            try {
                registeredMethod.getKey().getValue().invoke(registeredMethod.getKey().getKey(), event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
