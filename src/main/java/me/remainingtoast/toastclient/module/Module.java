package me.remainingtoast.toastclient.module;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.gui.click.TextWindow;
import me.remainingtoast.toastclient.setting.ModuleSettings;
import me.remainingtoast.toastclient.setting.settings.BooleanSetting;
import me.remainingtoast.toastclient.setting.settings.KeybindSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

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
    private final List<TextWindow> windows = new ArrayList<>();
    public ModuleSettings settings = new ModuleSettings();
    protected Logger LOGGER = ToastClient.log;

    public Module() {
        if (getClass().isAnnotationPresent(ModuleManifest.class)) {
            ModuleManifest moduleManifest = getClass().getAnnotation(ModuleManifest.class);
            this.label = moduleManifest.label();
            this.category = moduleManifest.category();
            this.alias = moduleManifest.aliases();
            this.hidden = moduleManifest.hidden();
            this.description = moduleManifest.description();
            this.key = moduleManifest.key();
            settings.addSetting("Bind", new KeybindSetting(key));
        }
    }

    public void registerSettings() {
        settings.addSetting("Drawn", new BooleanSetting(!hidden));
        settings.addSetting("Enabled", new BooleanSetting(false));
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
        return label;
    }

    public void setName(String name) {
        this.label = name;
    }

    public Category getCategory() {
        return category;
    }

    public String[] getAlias() { return this.alias; }

    public int getKey() {
        return settings.getSetting("Bind", KeybindSetting.class).getKey();
    }

    public void setNewKey(int newKey) {
        settings.getSetting("Bind", KeybindSetting.class).setKey(newKey);
    }

    public String setKeyName() {
        return settings.getSetting("Bind", KeybindSetting.class).getKeyName();
    }

    public String getDesc() {
        return description;
    }

    public boolean isEnabled() {
        return settings.getSetting("Enabled", BooleanSetting.class).getValue();
    }

    public void setEnabled(boolean bool) {
        if (bool) enable();
        else disable();
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    public boolean isDrawn() {
        return settings.getSetting("Drawn", BooleanSetting.class).getValue();
    }

    public void setDrawn(boolean bool) {
        if (bool) enableDrawn();
        else disableDrawn();
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }

    public void onTick() {
    }

    public List<TextWindow> getWindows()
    {
        return this.windows;
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        settings.getSetting("Enabled", BooleanSetting.class).setValue(true);
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        settings.getSetting("Enabled", BooleanSetting.class).setValue(false);
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    public void enableDrawn() {
        settings.getSetting("Drawn", BooleanSetting.class).setValue(true);
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    public void disableDrawn() {
        settings.getSetting("Drawn", BooleanSetting.class).setValue(false);
        ToastClient.SETTINGS_MANAGER.updateSettings();
    }

    public void toggle() {
        if (settings.getSetting("Enabled", BooleanSetting.class).getValue()) disable();
        else enable();
    }

    public void toggleDrawn() {
        if (settings.getSetting("Drawn", BooleanSetting.class).getValue()) disableDrawn();
        else enableDrawn();
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
        COMBAT,
        EXPLOITS,
        GUI,
        MISC,
        MOVEMENT,
        PLAYER,
        RENDER,
        NONE
    }

}
