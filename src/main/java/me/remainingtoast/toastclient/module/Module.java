package me.remainingtoast.toastclient.module;

import com.google.gson.JsonObject;
import me.kix.lotus.property.AbstractProperty;
import me.remainingtoast.toastclient.ToastClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Module {

    protected static final Minecraft mc = Minecraft.getMinecraft();
    private String label;
    private String[] alias;
    private String suffix;
    private String description;
    private Category category;
    private int key;
    private boolean hidden;
    private boolean enabled;
    private List<AbstractProperty> properties = new ArrayList<>();

    public Module() {
        if (getClass().isAnnotationPresent(ModuleManifest.class)) {
            ModuleManifest moduleManifest = getClass().getAnnotation(ModuleManifest.class);
            this.label = moduleManifest.label();
            this.category = moduleManifest.category();
            this.alias = moduleManifest.aliases();
            this.hidden = moduleManifest.hidden();
            this.description = moduleManifest.description();
            this.key = moduleManifest.key();
        }
    }

    public void init() {
        ToastClient.INSTANCE.getPropertyManager().scan(this);
    }

    public AbstractProperty find(String term) {
        for (AbstractProperty property : properties) {
            if (property.getLabel().equalsIgnoreCase(term)) {
                return property;
            }
        }
        return null;
    }

    public void save(JsonObject destination) {
        if (ToastClient.INSTANCE.getPropertyManager().getPropertiesFromObject(this) != null) {
            ToastClient.INSTANCE.getPropertyManager().getPropertiesFromObject(this).forEach(property -> destination.addProperty(property.getLabel(), property.getValue().toString()));
        }
        destination.addProperty("Name", getName());
        destination.addProperty("Enabled", isEnabled());
        destination.addProperty("Hidden", isHidden());
        destination.addProperty("Bind", getKey());
    }


    public void load(JsonObject source) {
        source.entrySet().forEach(entry -> {
            if (ToastClient.INSTANCE.getPropertyManager().getPropertiesFromObject(this) != null) {
                source.entrySet().forEach(entri -> ToastClient.INSTANCE.getPropertyManager().getProperty(this, entri.getKey()).ifPresent(property -> property.setValue(entri.getValue().getAsString())));
            }
            switch (entry.getKey()) {
                case "Enabled":
                    if (entry.getValue().getAsBoolean()) {
                        setEnabled(entry.getValue().getAsBoolean());
                    }
                    break;
                case "Hidden":
                    if (entry.getValue().getAsBoolean()) {
                        setHidden(entry.getValue().getAsBoolean());
                    }
                    break;
                case "Bind":
                    setNewKey(entry.getValue().getAsInt());
                    break;
                case "Name":
                    setName(entry.getValue().getAsString());
                    break;
            }
        });
    }

    public List<AbstractProperty> getProperties() {
        return this.properties;
    }

    public String getName() {
        return this.label;
    }

    public void setName(String name) {
        this.label = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public String[] getAlias() { return this.alias; }

    public int getKey() {
        return this.key;
    }

    public void setNewKey(int newKey) {
        this.key = newKey;
    }

    public String getDesc() {
        return this.description;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean bool) {
        this.enabled = bool;
        if(enabled){
            onEnable();
            MinecraftForge.EVENT_BUS.register(this);
        }else{
            onDisable();
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean bool) {
        this.hidden = bool;
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }

    public void onTick() {
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        this.enabled = true;
        onEnable();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        this.enabled = false;
        onDisable();
    }

    public void toggle() {
//        if(!enabled) enable();
//        disable();
        setEnabled(!isEnabled());
    }

    public void toggleHidden() {
        this.hidden = !isHidden();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return label.equals(module.label) && category == module.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, description, category);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + label + '\'' +
                ", category=" + category +
                ", enabled=" + isEnabled() +
                '}';
    }

    //A - Z Please
    public enum Category {
        COMBAT("Combat"),
        EXPLOITS("Exploits"),
        GUI("GUI"),
        MISC("MISC"),
        MOVEMENT("Movement"),
        PLAYER("Player"),
        RENDER("Render"),
        NONE("None");

        String label;

        Category(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

}
