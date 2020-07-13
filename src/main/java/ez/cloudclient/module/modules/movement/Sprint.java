package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, "Automatically Sprints for you");
    }

    /*
     * Created by ollie on 12/07/20
     */

    @Override
    protected void onEnable() {
        if (mc.player != null) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.setSprinting(false);
        }
    }

    @Override
    public void onTick() {
        try {
            mc.player.setSprinting(mc.gameSettings.keyBindForward.isKeyDown());
        } catch (Exception ignored) {
        }
    }
}