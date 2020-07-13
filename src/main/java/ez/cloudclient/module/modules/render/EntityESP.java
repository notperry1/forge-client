package ez.cloudclient.module.modules.render;

import ez.cloudclient.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityESP extends Module {

    private float startGamma;

    public EntityESP() {
        super("EntityESP", Category.RENDER, "");
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void onTick() {
        for (Entity entity : mc.world.getLoadedEntityList()) {
            if(entity instanceof EntityPlayer){
                entity.setGlowing(true);
            }
        }
    }
}
