package ez.cloudclient.CloudClient.Module;

import ez.cloudclient.CloudClient.MinecraftInstance;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Mod extends MinecraftInstance {
    private String displayName;
    private String key = "";
    private boolean enabled;

    public Mod(String displayName) {
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

    public void onTick(TickEvent.ClientTickEvent event) {
    }

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
