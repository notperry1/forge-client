package ez.cloudclient.module.mods;

import ez.cloudclient.module.Mod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem extends Mod {
    public AutoTotem() {
        super("AutoTotem", Category.COMBAT);
        setEnabled(true);
    }



    private enum Mode { NEITHER, REPLACE_OFFHAND, INVENTORY;}
    private enum CustomItem { CRYSTAL, GAPPLE }

    int totems;
    boolean moving = false;
    boolean returnI = false;

    @Override
    public void onTick(TickEvent.ClientTickEvent event) {
        if(mc.player == null) return;
        if ((mc.currentScreen instanceof GuiContainer))
            return; // this stops autototem from running if you're in a chest or something
        if (returnI) {
            int t = -1;
            for (int i = 0; i < 45; i++)
                if (mc.player.inventory.getStackInSlot(i).isEmpty()) {
                    t = i;
                    break;
                }
            if (t == -1) return;
            mc.playerController.windowClick(0, t < 9 ? t + 36 : t, 0, ClickType.PICKUP, mc.player);
            returnI = false;
        }
        totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == settingToItem()).mapToInt(ItemStack::getCount).sum();
        if (mc.player.getHeldItemOffhand().getItem() == settingToItem()) totems++;
        else {
            if (!mc.player.getHeldItemOffhand().isEmpty())
                return;
            if (moving) {
                mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
                moving = false;
                if (!mc.player.inventory.getItemStack().isEmpty()) returnI = true;
                return;
            }
            if (mc.player.inventory.getItemStack().isEmpty()) {
                if (totems == 0) return;
                int t = -1;
                for (int i = 0; i < 45; i++)
                    if (mc.player.inventory.getStackInSlot(i).isEmpty()) {
                        t = i;
                        break;
                    }
                if (t == -1) return;
                mc.playerController.windowClick(0, t < 9 ? t + 36 : t, 0, ClickType.PICKUP, mc.player);
                moving = true;
            }
        }
    }

    private Item settingToItem() {
        if (!passHealthCheck()) return Items.TOTEM_OF_UNDYING;
//        switch (smartItemSetting.getValue()) {
//            case GAPPLE:
//                return Items.GOLDEN_APPLE;
//            case CRYSTAL:
//                return Items.END_CRYSTAL;
//        }
        return null;
    }

    private boolean passHealthCheck() {
//        return mc.player.getHealth() + mc.player.getAbsorptionAmount() <= 20;
        return true;
    }
}
