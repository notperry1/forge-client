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
    private final String name;
    private final Category category;
    private final String description;
    public ModuleSettings settings = new ModuleSettings();
    protected Logger LOGGER = CloudClient.log;
    private String displayName;

    public Module(String name, Category category, String description) {
        this.name = name.toLowerCase().replaceAll(" ", "_");
        this.displayName = name;
        this.category = category;
        this.description = description;
        settings.addBoolean("enabled", false);
    }

    public void registerSettings() {
        selfSettings();
        LOGGER.info("Registered settings of " + this.getName());
    }

    public void selfSettings() {
    }

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

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDesc() {
        return description;
    }

    public boolean isEnabled() {
        return settings.getBoolean("enabled");
    }

    public void setEnabled(boolean bool) {
        if (bool) enable();
        else disable();
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public void onTick(TickEvent event) {
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        settings.setBoolean("enabled", true);
        SETTINGS_MANAGER.updateSettings();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        settings.setBoolean("enabled", false);
        SETTINGS_MANAGER.updateSettings();
    }

    public void toggle() {
        if (settings.getBoolean("enabled")) disable();
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
        return Objects.hash(name, description, category);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", category=" + category +
                ", enabled=" + isEnabled() +
                '}';
    }

    //A - Z Please
    public enum Category {
        COMBAT,
        EXPLOITS,
        MISC,
        MOVEMENT,
        NONE,
        PLAYER,
        RENDER
    }

}
