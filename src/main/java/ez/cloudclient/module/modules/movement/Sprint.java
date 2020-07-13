package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, "Automatically Sprints for you");
    }

    /*
     * Created by ollie on 12/07/20
     */

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
        if (mc.world != null) {
            mc.player.setSprinting(false);
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent event) {
        try {
            if ((mc.player !=null) && mc.gameSettings.keyBindForward.isKeyDown())
                mc.player.setSprinting(true);
            else
                mc.player.setSprinting(false);
        } catch (Exception ignored) {
        }
    }
}