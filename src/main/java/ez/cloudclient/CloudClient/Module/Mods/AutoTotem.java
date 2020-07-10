package ez.cloudclient.CloudClient.Module.Mods;

import ez.cloudclient.CloudClient.Module.Mod;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem extends Mod {
    public AutoTotem() {
        super("AutoTotem");
        setEnabled(true);
    }

    @Override
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null)
            return;

        if (mc.player.getHeldItemMainhand().getItem() != Items.TOTEM_OF_UNDYING) {
            mc.player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.TOTEM_OF_UNDYING, 1));
        }
    }
}
