package ez.cloudclient.Module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Mod {
    private String displayName;
    private String key = "";
    private String category;
    private boolean enabled;
    protected final static Minecraft mc = Minecraft.getMinecraft();


    public enum Category {
        COMBAT,
        RENDER,
        MOVEMENT,
        CHAT
    }

    public Mod(String displayName, Enum category) {
        this.category = category.name();
        this.displayName = displayName;
    }

    public void onEnable() { }
    public void onDisable() { }

    public void toggle() {
        this.enabled = !this.enabled;

        if (this.enabled)
            onEnable();
        else
            onDisable();
    }

    public void onRender(RenderWorldLastEvent event) { }

    public void onTick(TickEvent.ClientTickEvent event)  { }

    public void onKeyInput(String key) {
        if (this.key.equals(key))
            this.toggle();
    }

    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
