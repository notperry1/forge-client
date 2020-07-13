package ez.cloudclient.module.modules.player;

import ez.cloudclient.module.Module;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoFall extends Module {

    /*
        Added by Zilleyy 11/07/20
        Modified by RemainingToast 12/07/20
     */

    public NoFall() {
        super("NoFall", Category.PLAYER, "Prevent Taking Fall Damage");
    }

    @Override
    protected void onEnable() {

        if (mc.player != null) {
            mc.player.fallDistance = 0;
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.fallDistance = 0;
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if (mc.player != null) {
            if (mc.player.fallDistance != 0) {
                mc.player.connection.sendPacket(new CPacketPlayer(true));
                super.onTick(event);
            }
        }
    }
}
