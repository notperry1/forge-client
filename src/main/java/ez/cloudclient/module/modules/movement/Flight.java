package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;

public class Flight extends Module {

    public Flight() {
        super("Flight", Category.MOVEMENT, "Creative Flight");
    }

    /*
     * Added by Zilleyy 12/07/20
     * Updated by ollie on 13/07/20
     */

    @Override
    protected void onEnable() {
        if(this.isEnabled()) {
            if (mc.player != null) {
                mc.player.capabilities.isFlying = true;
            }
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.capabilities.isFlying = false;
        }
    }

    @Override
    public void onTick() {
        mc.player.capabilities.isFlying = true;
    }
}
