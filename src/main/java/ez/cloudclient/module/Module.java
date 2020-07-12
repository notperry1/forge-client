package ez.cloudclient.module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    public final String name;
    public final String displayName;
    public final Category category;
    private boolean enabled = false;

    public Module(String name, Category category) {
        this.name = name.toLowerCase().replaceAll(" ", "_");
        this.displayName = name;
        this.category = category;
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public void onTick(TickEvent event) {}

    public void setEnabled(boolean bool){
        if(bool) enable();
        else disable();
    }

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
