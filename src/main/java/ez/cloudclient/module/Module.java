package ez.cloudclient.module;

import ez.cloudclient.CloudClient;
import ez.cloudclient.setting.ModuleSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static ez.cloudclient.CloudClient.SETTINGS_MANAGER;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    protected Logger LOGGER = CloudClient.log;
    private String name;
    private final String displayName;
    private final Category category;
    private boolean enabled;
    private String description;
    public ModuleSettings settings = new ModuleSettings();

    public Module(String name, Category category, String description) {
        this.name = name.toLowerCase().replaceAll(" ", "_");
        this.displayName = name;
        this.category = category;
        this.description = description;
        enabled = false;
    }

    public void registerSettings() {
        settings.addSetting("enabled", false);
        selfSettings();
        LOGGER.info("Registered settings of " + this.getName());
    }

    public void selfSettings() {}

    public ModuleSettings getSettings() {
        return settings;
    }

    public void setSettings(ModuleSettings newSettings) {
        settings = newSettings;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Category getCategory() {
        return category;
    }

    public String getDesc() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }


    protected abstract void onEnable();

    protected abstract void onDisable();

    public void onTick(TickEvent event) {}

    public void setEnabled(boolean bool){
        if(bool) enable();
        else disable();
        settings.setSetting("enabled", bool);
        SETTINGS_MANAGER.updateSettings();
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        enabled = true;
        settings.setSetting("enabled", true);
        SETTINGS_MANAGER.updateSettings();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        enabled = false;
        settings.setSetting("enabled", false);
        SETTINGS_MANAGER.updateSettings();
    }

    public void toggle() {
        if (enabled) disable();
        else enable();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return name.equals(module.name) &&
                displayName.equals(module.displayName) &&
                category == module.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, category);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", category=" + category +
                ", enabled=" + enabled +
                '}';
    }
    //A - Z Please
    public enum Category {
        COMBAT,
        EXPLOITS,
        PLAYER,
        RENDER,
        MISC,
        MOVEMENT,
        NONE
    }

}
