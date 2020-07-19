package me.remainingtoast.toastclient.module.modules.render;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;

@ModuleManifest(label = "PlayerESP", category = Module.Category.RENDER, description = "ESP for Players", aliases = {}, hidden = false)
public class PlayerESP extends Module {

    @Override
    protected void onDisable() {
        for (Entity e : mc.world.getLoadedEntityList()) {
            if (e instanceof EntityPlayer && e != mc.player) {
                e.setGlowing(false);
            }
        }
    }

    @Override
    public void onTick() {
        if(mc.world == null || mc.player == null) return;
        for (Entity e : mc.world.getLoadedEntityList()) {
            if (e instanceof EntityItem && e != mc.player && !e.isGlowing()) {
                e.setGlowing(true);
            }
        }
    }
}
