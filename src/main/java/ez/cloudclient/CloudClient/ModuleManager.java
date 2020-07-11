package ez.cloudclient.CloudClient;

import ez.cloudclient.CloudClient.Module.Mod;
import ez.cloudclient.CloudClient.Module.Mods.AutoTotem;
import ez.cloudclient.CloudClient.Module.Mods.ElytraFly;
import ez.cloudclient.CloudClient.Module.Mods.FullBright;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static ModuleManager INSTANCE = new ModuleManager();
    private List<Mod> mods = new ArrayList<>();

    public ModuleManager() {
    }

    public void init() {
        mods.add(new AutoTotem());
        mods.add(new FullBright());
        mods.add(new ElytraFly());
    }

    public void onRender(RenderWorldLastEvent event) {
        mods.stream().filter(m -> m.isEnabled()).collect(Collectors.toList()).forEach(m -> m.onRender(event));
    }

    public void onUpdate(MinecartUpdateEvent event) {
        mods.stream().filter(m -> m.isEnabled()).collect(Collectors.toList()).forEach(m -> m.onUpdate(event));
    }

    public void onTick(TickEvent.ClientTickEvent event) {
        mods.stream().filter(m -> m.isEnabled()).collect(Collectors.toList()).forEach(m -> {
            try {
                m.onTick(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void onKeyInput(String key) {
        mods.stream().filter(m -> m.isEnabled()).collect(Collectors.toList()).forEach(m -> m.onKeyInput(key));
    }

    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        mods.stream().filter(m -> m.isEnabled()).collect(Collectors.toList()).forEach(m -> m.onRenderGameOverlay(event));
    }

    public List<Mod> getMods() {
        return this.mods;
    }
}
