package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Flight extends Module {
    public Flight() {
        super("Flight", Category.MOVEMENT, "Creative Flight");
    }

    /*
    * Added by Zilleyy 12/07/20
    */

    @Override
    protected void onEnable() {
        mc.player.capabilities.isFlying = true;
    }

    @Override
    protected void onDisable() {
        mc.player.capabilities.isFlying = false;
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if(mc.player != null) {
                mc.player.capabilities.isFlying = true;
        }
    }
}