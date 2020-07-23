package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "AntiHunger", category = Module.Category.PLAYER, description = "Preventing Hunger Loss", aliases = {"NoHunger"}, hidden = false)
public class AntiHunger extends Module {

    @Override
    public void onTick() {
        mc.player.getFoodStats().setFoodLevel(20);
    }
}
