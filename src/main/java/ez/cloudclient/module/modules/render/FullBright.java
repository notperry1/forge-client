package ez.cloudclient.module.modules.render;

import ez.cloudclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullBright extends Module {
    public FullBright() {
        super("Full Bright", Category.RENDER);
    }

    private float startGamma;

    @Override
    protected void onEnable() {
        startGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    protected void onDisable() {
        mc.gameSettings.gammaSetting = startGamma;
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if (mc.gameSettings.gammaSetting < 16) {
            mc.gameSettings.gammaSetting++;
        }
    }
}
