package ez.cloudclient.module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;

public abstract class Module {
    final String name;
    final String displayName;
    final Category category;
    private boolean enabled = false;

    protected static final Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, Category category) {
        this.name = name.toLowerCase().replaceAll(" ", "_");
        this.displayName = name;
        this.category = category;
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        enabled = true;
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        enabled = false;
    }

    public void toggle() {
        if (enabled) disable();
        else enable();
    }

    public boolean isEnabled() {
        return enabled;
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

    public enum Category {
        RENDER
    }
}
