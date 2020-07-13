package ez.cloudclient.module.modules.combat;

import ez.cloudclient.module.Module;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KillAura extends Module {

    public KillAura() {
        super("KillAura", Category.MOVEMENT, "Automatically attacks enemies in range.");
    }

    /*
     * Created by ollie on 13/07/20
     */

    @Override
    public void selfSettings() {
        settings.addSetting("Auto Switch", true);
    }

    int l_Slot = -1;

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void onTick(TickEvent event) {
        if(mc.player != null){
            if((Boolean) settings.getSetting("Auto Switch")) {
                for (int l_I = 0; l_I < 9; ++l_I) {
                    if (mc.player.inventory.getStackInSlot(l_I).getItem() instanceof ItemSword) {
                        l_Slot = l_I;
                        mc.player.inventory.currentItem = l_Slot;
                        mc.playerController.updateController();
                    }
                }
            }
        }
    }
}