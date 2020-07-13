package ez.cloudclient.module.modules.movement;

import ez.cloudclient.module.Module;

public class FastStop extends Module {

    public FastStop() {
        super("FastStop",  Category.MOVEMENT,"Brings you to a stop instantly", -1);
        this.settings.addBoolean("Air Stop", false);
    }

    @Override
    protected void onEnable() { }

    @Override
    protected void onDisable() { }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (!mc.gameSettings.keyBindForward.isPressed() && !mc.gameSettings.keyBindBack.isPressed() && !mc.gameSettings.keyBindLeft.isPressed() && !mc.gameSettings.keyBindRight.isPressed()) {
            if (mc.player.onGround) {
                mc.player.setVelocity(0, mc.player.motionY, 0);
            } else if (this.settings.getBoolean("Air Stop") && !mc.player.onGround) {
                mc.player.setVelocity(0, mc.player.motionY, 0);
            }
        }
    }
}
