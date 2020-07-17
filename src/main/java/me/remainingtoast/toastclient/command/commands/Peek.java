package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.client.gui.inventory.GuiShulkerBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Peek extends Command {
    public Peek() {
        super("Peek", "Show contents of the shulker in your hand", "p", "peek");
    }

    @Override
    protected void call(String[] args) {
        try{
            MinecraftForge.EVENT_BUS.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event){
        try{
            ItemStack stack = null;
            if(mc.player != null){
                final RayTraceResult ray = mc.objectMouseOver;
                if(ray != null){
                    if(ray.entityHit instanceof EntityItemFrame){
                        final EntityItemFrame itemFrame = (EntityItemFrame) ray.entityHit;
                        if(itemFrame.getDisplayedItem().getItem() instanceof ItemShulkerBox){
                            stack = itemFrame.getDisplayedItem();
                        }else{
                            stack = getHeldShulker(mc.player);
                        }
                    } else {
                        stack = getHeldShulker(mc.player);
                    }
                } else {
                    stack = getHeldShulker(mc.player);
                }
                if(stack != null){
                    final Item item = stack.getItem();
                    if(item instanceof ItemShulkerBox){
                        if(Block.getBlockFromItem(item) instanceof BlockShulkerBox){
                            final BlockShulkerBox shulkerBox = (BlockShulkerBox) Block.getBlockFromItem(item);
                            final NBTTagCompound tag = stack.getTagCompound();
                            if(tag != null && tag.hasKey("BlockEntityTag", 10)){
                                final NBTTagCompound entityTag = tag.getCompoundTag("BlockEntityTag");
                                final TileEntityShulkerBox tileEntity = new TileEntityShulkerBox();
                                tileEntity.setWorld(mc.world);
                                tileEntity.readFromNBT(entityTag);
                                mc.displayGuiScreen(new GuiShulkerBox(mc.player.inventory, tileEntity));
                            }else{
                                MessageUtil.sendMessage("Empty Shulker Box", MessageUtil.Color.RED);
                            }
                        }
                    } else {
                        MessageUtil.sendMessage("Hold a Shulker Box", MessageUtil.Color.RED);
                    }
                } else {
                    MessageUtil.sendMessage("Hold a Shulker Box", MessageUtil.Color.RED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    private ItemStack getHeldShulker(EntityPlayer player){
        if(player.getHeldItemMainhand().getItem() instanceof ItemShulkerBox){
            return player.getHeldItemMainhand();
        }
        if(player.getHeldItemOffhand().getItem() instanceof ItemShulkerBox){
            return player.getHeldItemOffhand();
        }
        return null;
    }
}
