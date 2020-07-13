package ez.cloudclient.module;

import ez.cloudclient.CloudClient;
import ez.cloudclient.setting.ModuleSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
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
    }

    public void registerSettings() {
        settings.addBoolean("Drawn", true);
        settings.addBoolean("Enabled", false);
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
        return settings.getBoolean("Enabled");
    }

    public void setEnabled(boolean bool) {
        if (bool) enable();
        else disable();
        SETTINGS_MANAGER.updateSettings();
    }

    public boolean isDrawn() {
        return settings.getBoolean("Drawn");
    }

    public void setDrawn(boolean bool) {
        if (bool) enableDrawn();
        else disableDrawn();
        settings.setBoolean("Drawn", bool);
        SETTINGS_MANAGER.updateSettings();
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public void onTick() {
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        settings.setBoolean("Enabled", true);
        SETTINGS_MANAGER.updateSettings();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        settings.setBoolean("Enabled", false);
        SETTINGS_MANAGER.updateSettings();
    }

    public void enableDrawn() {
        MinecraftForge.EVENT_BUS.register(this);
        settings.setBoolean("Drawn", true);
        SETTINGS_MANAGER.updateSettings();
    }

    public void disableDrawn() {
        MinecraftForge.EVENT_BUS.unregister(this);
        settings.setBoolean("Drawn", false);
        SETTINGS_MANAGER.updateSettings();
    }

    public void toggle() {
        if (settings.getBoolean("Enabled")) disable();
        else enable();
    }

    public void toggleDrawn() {
        if (settings.getBoolean("Drawn")) disableDrawn();
        else enableDrawn();
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
