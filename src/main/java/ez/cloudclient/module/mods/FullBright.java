package ez.cloudclient.module.mods;

import ez.cloudclient.module.Mod;

import ez.cloudclient.module.Value;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullBright extends Mod {

    public final Value<Mode> mode = new Value<Mode>("Mode", new String[]
            { "Mode", "M" }, "The brightness mode to use.", Mode.Gamma);

    public FullBright() {
        super("Fullbright", Category.RENDER);
        setEnabled(true);
    }

    private enum Mode
    {
        Gamma,
        Potion,
        Table
    }

    private float lastGamma;

    @Override
    public void onEnable()
    {
        super.onEnable();
        if (this.mode.getValue() == Mode.Gamma)
        {
            this.lastGamma = mc.gameSettings.gammaSetting;
        }
    }

    @Override
    public void onDisable()
    {
        super.onDisable();

        if (this.mode.getValue() == Mode.Gamma)
        {
            mc.gameSettings.gammaSetting = this.lastGamma;
        }

        if (this.mode.getValue() == Mode.Potion && mc.player != null)
        {
            mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }

        if (this.mode.getValue() == Mode.Table)
        {
            if (mc.world != null)
            {
                float f = 0.0F;

                for (int i = 0; i <= 15; ++i)
                {
                    float f1 = 1.0F - (float) i / 15.0F;
                    mc.world.provider.getLightBrightnessTable()[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 1.0F + 0.0F;
                }
            }
        }
    }

    @Override
    public void onTick(TickEvent.ClientTickEvent event) {
//        mc.gameSettings.gammaSetting = -10;
        if (mc.player != null) {
            if (!mc.player.isPotionActive(MobEffects.NIGHT_VISION) || mc.player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() < 20) {
                mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20000));
            }
        }
    }
}
