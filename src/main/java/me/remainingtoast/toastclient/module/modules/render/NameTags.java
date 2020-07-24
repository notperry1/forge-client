package me.remainingtoast.toastclient.module.modules.render;

import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.NametagUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Comparator;

import static org.lwjgl.opengl.GL11.*;

@ModuleManifest(label = "NameTags", category = Module.Category.RENDER, description = "Show better nameplates for players", aliases = {"nameplates"})
public class NameTags extends Module {

    @Property("Range")
    public float range = 200;

    @Property("Scale")
    public float scale = 2.5f;

    @Property("Health")
    public boolean health = true;

    @SubscribeEvent
    public void worldRender(RenderWorldLastEvent event){
        if(mc.getRenderManager().options == null) return;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        mc.world.loadedEntityList.stream()
                .filter(Entity::isEntityAlive)
                .filter(entity -> (entity instanceof EntityPlayer))
                .filter(entity -> mc.player.getDistance(entity) < range)
                .sorted(Comparator.comparing(entity -> -mc.player.getDistance(entity)))
                .forEach(this::drawNametag);
        GlStateManager.disableTexture2D();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }

    private void drawNametag(Entity entity){
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

        FontRenderer font = mc.fontRenderer;
        GlStateManager.scale(-0.025F, -0.025F, 0.025F);

        String name = entity.getName() + (health ? " " + healthColoured(entity, Math.round(((EntityLivingBase) entity).getHealth() + (entity instanceof EntityPlayer ? ((EntityPlayer) entity).getAbsorptionAmount() : 0))) : "");
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
        font.drawString(name, -length, 10, 0x00FF00);
        GlStateManager.glNormal3f(0.0F, 0.0F, 0.0F);
        glTranslatef(0, 20, 0);

        GlStateManager.scale(-40, -40, 40);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
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
