package me.remainingtoast.toastclient.module.modules.render;

import me.kix.lotus.property.annotations.Clamp;
import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.NametagUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

@ModuleManifest(label = "NameTags", category = Module.Category.RENDER, description = "Show better nameplates for players", aliases = {"nameplates"})
public class NameTags extends Module {

    @Property("Range")
    @Clamp(minimum = "10", maximum = "1000")
    public float range = 200;

    @Property("Scale")
    @Clamp(minimum = "2f", maximum = "10f")
    public float scale = 5f;

    @Property("Health")
    public boolean health = true;

    private static FontRenderer font = mc.fontRenderer;

    @SubscribeEvent
    public void worldRender(RenderWorldLastEvent event){
        for (EntityPlayer p : mc.world.playerEntities) {
            if ((p != mc.getRenderViewEntity()) && (p.isEntityAlive())) {
                if (!p.getName().startsWith("Body #")) {
                    drawNametag(p);
                }
            }
        }
    }

    private void drawNametag(EntityPlayer entity){
        GlStateManager.pushMatrix();
        Vec3d vec3d = NametagUtil.getInterpolatedRenderPos(entity, mc.getRenderPartialTicks());
        float yAdd = entity.height + 0.5f - (entity.isSneaking() ? 0.25f : 0);
        double x = vec3d.x;
        double y = vec3d.y + yAdd;
        double z = vec3d.z;

        float viewerYaw = mc.getRenderManager().playerViewY;
        float viewerPitch = mc.getRenderManager().playerViewX;
        boolean isFacingThirdPerson = mc.getRenderManager().options.thirdPersonView == 2;
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(-viewerYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((float) (isFacingThirdPerson ? -1 : 1) * viewerPitch, 1.0F, 0.0F, 0.0F);

        float dist = mc.player.getDistance(entity);
        float math = (dist / 8f) * (float) (Math.pow(1.258254f, this.scale));
        GlStateManager.scale(math, math, math);
        GlStateManager.scale(-0.025F, -0.025F, 0.025F);
        NetworkPlayerInfo playerInfo = mc.getConnection().getPlayerInfo(entity.getUniqueID());
        if(playerInfo == null || entity == null) return; // IntelliJ says this is always false, but crashes without it.
        String ping = " " + playerInfo.getResponseTime() + "ms";
        String name = entity.getName() + ping + (this.health ? " " + healthColoured(entity, Math.round(entity.getHealth() + entity.getAbsorptionAmount())) : "");
        int length = font.getStringWidth(name) / 2;
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        GlStateManager.disableDepth();
        glTranslatef(0, -20, 0);
        bufferBuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(-length - 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
        bufferBuilder.pos(-length - 1, 19, 0.0D).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
        bufferBuilder.pos(length + 1, 19, 0.0D).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
        bufferBuilder.pos(length + 1, 8, 0.0D).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
        tessellator.draw();

        bufferBuilder.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(-length - 1, 8, 0.0D).color(.1f, .1f, .1f, .1f).endVertex();
        bufferBuilder.pos(-length - 1, 19, 0.0D).color(.1f, .1f, .1f, .1f).endVertex();
        bufferBuilder.pos(length + 1, 19, 0.0D).color(.1f, .1f, .1f, .1f).endVertex();
        bufferBuilder.pos(length + 1, 8, 0.0D).color(.1f, .1f, .1f, .1f).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);

        if (!entity.isSneaking()) font.drawString(name, -length, 10, 0xffffff);
        else font.drawString(name, -length, 10, 0x00FF00);

        int xOffset = 0;
        for (ItemStack armourStack : entity.getArmorInventoryList()) {
            if (armourStack != null) {
                xOffset -= 8;
            }
        }

        Object renderStack;
        if (entity.getHeldItemMainhand() != null) {
            xOffset -= 8;
            renderStack = entity.getHeldItemMainhand().copy();
            renderItem((ItemStack) renderStack, xOffset, -10);
            xOffset += 16;
        }
        for (int index = 3; index >= 0; --index) {
            ItemStack armourStack = entity.inventory.armorInventory.get(index);
            if (armourStack != null) {
                ItemStack renderStack1 = armourStack.copy();
                renderItem(renderStack1, xOffset, -10);
                xOffset += 16;
            }
        }

        ItemStack renderOffhand;
        if (entity.getHeldItemOffhand() != null) {
            xOffset -= 0;
            renderOffhand = entity.getHeldItemOffhand().copy();

            renderItem(renderOffhand, xOffset, -10);
            xOffset += 16;
        }

        GlStateManager.glNormal3f(0.0F, 0.0F, 0.0F);
        glTranslatef(0, 20, 0);

        GlStateManager.scale(-40, -40, 40);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    private void renderItem(ItemStack stack, int x, int y) {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);

        GlStateManager.disableDepth();
        GlStateManager.enableDepth();

        RenderHelper.enableStandardItemLighting();
        mc.getRenderItem().zLevel = -100.0F;
        GlStateManager.scale(1, 1, 0.01f);
        mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, (y / 2) - 12);
        mc.getRenderItem().renderItemOverlays(mc.fontRenderer, stack, x, (y / 2) - 12);
        mc.getRenderItem().zLevel = 0.0F;
        GlStateManager.scale(1, 1, 1);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.disableDepth();
        renderEnchantText(stack, x, y - 18);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    private void renderEnchantText(ItemStack stack, int x, int y) {
        int encY = y - 24;
        int yCount = encY - -5;
        if (stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemSword
                || stack.getItem() instanceof ItemTool) {
            font.drawStringWithShadow(stack.getMaxDamage() - stack.getItemDamage() + "\u00A74", x * 2 + 8, y + 26,
                    -1);
        }
        NBTTagList enchants = stack.getEnchantmentTagList();
        for (int index = 0; index < enchants.tagCount(); ++index) {
            short id = enchants.getCompoundTagAt(index).getShort("id");
            short level = enchants.getCompoundTagAt(index).getShort("lvl");
            Enchantment enc = Enchantment.getEnchantmentByID(id);
            if (enc != null) {
                String encName = enc.isCurse()
                        ? TextFormatting.RED
                        + enc.getTranslatedName(level).substring(11).substring(0, 1).toLowerCase()
                        : enc.getTranslatedName(level).substring(0, 1).toLowerCase();
                encName = encName + level;
                GL11.glPushMatrix();
                GL11.glScalef(0.9f, 0.9f, 0);
                font.drawStringWithShadow(encName, x * 2 + 13, yCount, -1);
                GL11.glScalef(1f, 1f, 1);
                GL11.glPopMatrix();
                encY += 8;
                yCount -= 10;
            }
        }
    }

    private String healthColoured(Entity entity, int health){
        float max = ((EntityLivingBase) entity).getMaxHealth();
        int result = Math.round((health / max) * 100);
        if (result > 75) {
            return TextFormatting.GREEN + "" + health;
        } else if (result > 50) {
            return TextFormatting.YELLOW + "" + health;
        } else if (result > 25) {
            return TextFormatting.RED + "" + health;
        } else {
            return TextFormatting.DARK_RED + "" + health;
        }
    }
}
