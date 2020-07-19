package me.remainingtoast.toastclient.gui.hud;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.remainingtoast.toastclient.gui.hud.components.Watermark;
import me.remainingtoast.toastclient.managers.HashMapManager;

import java.io.*;
import java.util.HashSet;

public class ComponentManager extends HashMapManager<String, HudComponent> {

    private final HashSet<HudComponent> componentsSet = new HashSet<>();
    private File directory;

    public ComponentManager(File directory){
        this.directory = directory;
        if(!directory.exists()){
            directory.mkdir();
        }
    }

    @Override
    public void load(){
        super.load();
        register(new Watermark());
        getRegistry().values().forEach(HudComponent::init);
        loadComponents();
    }

    public void register(HudComponent... components) {
        for (HudComponent component : components) {
            if (component.getLabel() != null){
                include(component.getLabel().toLowerCase(), component);
                componentsSet.add(component);
            }
        }
    }

    public void saveComponents() {
        if (getValues().isEmpty()) {
            directory.delete();
        }
        File[] files = directory.listFiles();
        if (!directory.exists()) {
            directory.mkdir();
        } else if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        getValues().forEach(comp -> {
            File file = new File(directory, comp.getLabel() + ".json");
            JsonObject node = new JsonObject();
            comp.save(node);
            if (node.entrySet().isEmpty()) {
                return;
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                return;
            }
            try (Writer writer = new FileWriter(file)) {
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(node));
            } catch (IOException e) {
                file.delete();
            }
        });
        files = directory.listFiles();
        if (files == null || files.length == 0) {
            directory.delete();
        }
    }

    public void loadComponents() {
        getValues().forEach(comp -> {
            final File file = new File(directory, comp.getLabel() + ".json");
            if (!file.exists()) {
                return;
            }
            try (Reader reader = new FileReader(file)) {
                JsonElement node = new JsonParser().parse(reader);
                if (!node.isJsonObject()) {
                    return;
                }
                comp.load(node.getAsJsonObject());
            } catch (IOException e) {
            }
        });
    }

    public HudComponent find(Class<? extends HudComponent> clazz) {
        return getValues().stream().filter(m -> m.getClass() == clazz).findFirst().orElse(null);
    }

    public HudComponent find(String find) {
        HudComponent m = pull(find.replaceAll(" ", ""));
        if (pull(find.replaceAll(" ", "")) != null)
            m = pull(find.replaceAll(" ", ""));
        return m;
    }
}
