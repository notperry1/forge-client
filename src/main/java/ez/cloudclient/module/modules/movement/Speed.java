package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Speed extends Module {

    public Speed() {
        super("Speed", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        mc.player.capabilities.setPlayerWalkSpeed(10);
    }

    @Override
    protected void onDisable() {
        mc.player.capabilities.setPlayerWalkSpeed(1);
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if(mc.player != null) {
            mc.player.capabilities.setPlayerWalkSpeed(10);
        }
    }
}
