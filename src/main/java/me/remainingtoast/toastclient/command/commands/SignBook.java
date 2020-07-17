package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;

public class SignBook extends Command {
    public SignBook() {
        super("SignBook", "Change the author of the book your holding (creative only)", "sbook", "signbook");
    }

    @Override
    protected void call(String[] args) {
        if(args.length > 0){
            if(mc.player.isCreative()){
                final ItemStack itemStack = mc.player.inventory.getCurrentItem();
                if(itemStack.getItem() instanceof ItemWrittenBook){
                    final NBTTagCompound tagCompound = (itemStack.hasTagCompound()) ? itemStack.getTagCompound() : new NBTTagCompound();
                    tagCompound.setTag("author", new NBTTagString(args[0]));
                    mc.player.connection.sendPacket(new CPacketCreativeInventoryAction(36 + mc.player.inventory.currentItem, itemStack));
                    MessageUtil.sendMessage("Book now signed by "+args[0], MessageUtil.Color.GREEN);
                } else { MessageUtil.sendMessage("Must be holding signed book", MessageUtil.Color.RED);}
            } else {
                MessageUtil.sendMessage("Must be in creative", MessageUtil.Color.RED);
            }
        }
    }
}
