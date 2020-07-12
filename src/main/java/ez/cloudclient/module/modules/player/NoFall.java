package ez.cloudclient.module.modules.player;

import ez.cloudclient.module.Module;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall", Category.PLAYER);
    }

    @Override
    protected void onEnable() { }

    @Override
    protected void onDisable() { }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        if(mc.player != null){
            if(mc.player.fallDistance != 0){
                mc.player.connection.sendPacket(new CPacketPlayer(true));
                mc.player.fall(0,0);
                super.onTick(event);
            }
        }
    }
}
