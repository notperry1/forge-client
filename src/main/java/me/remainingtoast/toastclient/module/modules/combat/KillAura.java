package me.remainingtoast.toastclient.module.modules.combat;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.setting.settings.BooleanSetting;
import net.minecraft.item.ItemSword;

@ModuleManifest(label = "KillAura", category = Module.Category.COMBAT, description = "Automatically Attack Enemies", aliases = {"Aura"}, hidden = false)
public class KillAura extends Module {
    int l_Slot = -1;

    @Override
    public void selfSettings() {
        settings.addSetting("Auto Switch", new BooleanSetting(true));
    }

    @Override
    public void onTick() {
        if (settings.getSetting("Auto Switch", BooleanSetting.class).getValue()) {
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