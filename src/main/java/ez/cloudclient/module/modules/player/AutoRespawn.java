package ez.cloudclient.module.modules.player;

import ez.cloudclient.module.Module;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("AutoRespawn", Category.COMBAT, "Automatically respawns you when you die");
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @SubscribeEvent
    public void onUpdate(TickEvent e) {
        if(mc.player != null) {
            if (mc.player.isDead) {
                mc.player.respawnPlayer();
            }
        }
    }
}