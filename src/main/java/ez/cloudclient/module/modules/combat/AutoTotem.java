package ez.cloudclient.module.modules.combat;

import ez.cloudclient.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem extends Module {

    /*
     * Added by Zilleyy on 13/07/2020
     */

    public AutoTotem() {
        super("AutoTotem", Category.COMBAT, "Automatically swaps totem of undying to your offhand");
    }


    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @SubscribeEvent
    public void onUpdate(TickEvent e) {
            if(mc.player == null)
               return;
            if(mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() == Items.TOTEM_OF_UNDYING)
               return;

        final int slot = this.getItemSlot(Items.TOTEM_OF_UNDYING);

        if(slot != -1) {
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.updateController();
        }
    }

    private int getItemSlot(Item input) {
        for(int i = 0; i < 36; i++) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == input) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
}
