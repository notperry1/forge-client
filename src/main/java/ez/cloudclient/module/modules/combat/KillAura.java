package ez.cloudclient.module.modules.combat;

import ez.cloudclient.module.Module;
import net.minecraft.item.ItemSword;

public class KillAura extends Module {

    int l_Slot = -1;

    public KillAura() {
        super("KillAura", Category.COMBAT, "Automatically attacks enemies in range.");
    }

    @Override
    public void selfSettings() {
        settings.addBoolean("Auto Switch", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void onTick() {
        if (settings.getBoolean("Auto Switch")) {
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