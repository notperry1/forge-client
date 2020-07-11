package ez.cloudclient.CloudClient.Module.Mods;

import ez.cloudclient.CloudClient.Module.Mod;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullBright extends Mod {
    public FullBright() {
        super("Fullbright", Category.RENDER);
        setEnabled(true);
    }

    @Override
    public void onTick(TickEvent.ClientTickEvent event) throws InterruptedException {
//        mc.gameSettings.gammaSetting = -10;
        if (mc.player != null) {
            if (!mc.player.isPotionActive(MobEffects.NIGHT_VISION) || mc.player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() < 20) {
                mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20000));
                wait(20000);
            }
        }
    }
}
