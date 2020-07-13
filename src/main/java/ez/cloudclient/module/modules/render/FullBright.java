package ez.cloudclient.module.modules.render;

import ez.cloudclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullBright extends Module {

    private float startGamma;

    public FullBright() {
        super("FullBright", Category.RENDER, "Increase client brightness");
    }

    @Override
    protected void onEnable() {
        if(mc.player != null) {
            startGamma = mc.gameSettings.gammaSetting;
        }
    }

    @Override
    protected void onDisable() {
        if(mc.player != null) {
            mc.gameSettings.gammaSetting = startGamma;
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if (mc.gameSettings.gammaSetting < 16) {
            mc.gameSettings.gammaSetting++;
        }
    }
}
