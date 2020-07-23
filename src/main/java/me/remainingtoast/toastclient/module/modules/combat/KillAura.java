package me.remainingtoast.toastclient.module.modules.combat;

import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.item.ItemSword;

@ModuleManifest(label = "KillAura", category = Module.Category.COMBAT, description = "Automatically Attack Enemies", aliases = {"Aura"}, hidden = false)
public class KillAura extends Module {
    int l_Slot = -1;

    @Property("AutoSwitch")
    public static boolean autoSwitch;

    @Override
    public void onTick() {
        if (autoSwitch) {
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