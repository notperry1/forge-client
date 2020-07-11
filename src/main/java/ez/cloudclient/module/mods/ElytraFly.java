package ez.cloudclient.module.mods;

import ez.cloudclient.module.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ElytraFly extends Mod {
    public ElytraFly() {
        super("ElytraFly", Category.MOVEMENT);
        setEnabled(true);
    }

    @Override
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null)
            return;

        if (mc.player.isElytraFlying()) {
            mc.player.motionX = -1;
            mc.player.motionZ = 0;
            mc.player.motionY = mc.player.ticksExisted % 2 == 0 ? 1 : -1;
        }
    }
}
