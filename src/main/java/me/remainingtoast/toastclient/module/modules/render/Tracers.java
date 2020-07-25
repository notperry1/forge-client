package me.remainingtoast.toastclient.module.modules.render;

import me.kix.lotus.property.annotations.Mode;
import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.GLUProjection;
import me.remainingtoast.toastclient.util.MathUtil;
import me.remainingtoast.toastclient.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "Tracers", category = Module.Category.RENDER)
public class Tracers extends Module {

    @Property("Players")
    private boolean players = true;

    @Property("Mobs")
    private boolean mobs = true;

    @Property("Animals")
    private boolean animals = true;

    @Property("Vehicles")
    private boolean vehicles = true;

    @Property("Items")
    private boolean items = true;

    @Property("Mode")
    @Mode({"2D", "3D"})
    private String mode = "3D";

    @SubscribeEvent
    public void worldRender(RenderWorldLastEvent event){
        final Minecraft mc = Minecraft.getMinecraft();
        final ScaledResolution res = new ScaledResolution(mc);
        for (Entity e : mc.world.loadedEntityList) {
            if (e != null) {
                if (this.checkFilter(e)) {
                    switch (mode.toLowerCase()){
                        case "2d":
                            final Vec3d pos = MathUtil.interpolateEntity(e, event.getPartialTicks());
                            final GLUProjection.Projection projection = GLUProjection.getInstance().project(pos.x - mc.getRenderManager().viewerPosX, pos.y - mc.getRenderManager().viewerPosY, pos.z - mc.getRenderManager().viewerPosZ, GLUProjection.ClampMode.NONE, true);
                            RenderUtil.drawLine((float) projection.getX(), (float) projection.getY(), res.getScaledWidth() / 2, res.getScaledHeight() / 2, 0.5f, this.getTracerColor(e));
                            break;
                        case "3d":
                            final Vec3d pos2 = MathUtil.interpolateEntity(e, event.getPartialTicks()).subtract(mc.getRenderManager().viewerPosX, mc.getRenderManager().viewerPosY, mc.getRenderManager().viewerPosZ);
                            final boolean bobbing = mc.gameSettings.viewBobbing;
                            mc.gameSettings.viewBobbing = false;
                            mc.entityRenderer.updateCameraAndRender(event.getPartialTicks(), 0);
                            final Vec3d forward = new Vec3d(0, 0, 1).rotatePitch(-(float) Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float) Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
                            RenderUtil.drawLine3D((float) forward.x, (float) forward.y + mc.player.getEyeHeight(), (float) forward.z, (float) pos2.x, (float) pos2.y, (float) pos2.z, 0.5f, this.getTracerColor(e));
                            mc.gameSettings.viewBobbing = bobbing;
                            mc.entityRenderer.updateCameraAndRender(event.getPartialTicks(), 0);
                            break;
                    }
                }
            }
        }
    }

    private boolean checkFilter(Entity entity) {
        boolean ret = false;
        if (players && entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().player) { ret = true; }
        if (mobs && entity instanceof IMob) { ret = true; }
        if (animals && entity instanceof IAnimals && !(entity instanceof IMob)) { ret = true; }
        if (vehicles && (entity instanceof EntityBoat || entity instanceof EntityMinecart)) { ret = true; }
        if (items && entity instanceof EntityItem) { ret = true; }
        return ret;
    }

    private int getTracerColor(Entity entity) {
        int ret = -1;
        if (entity instanceof IAnimals && !(entity instanceof IMob)) { ret = 0xFF00FF44; }
        if (entity instanceof IMob) { ret = 0xFFFFAA00; }
        if (entity instanceof EntityBoat || entity instanceof EntityMinecart || entity instanceof EntityMinecartContainer) { ret = 0xFF00FFAA; }
        if (entity instanceof EntityItem) { ret = 0xFF00FFAA; }
        if (entity instanceof EntityPlayer) { ret = 0xFFFF4444; }
        return ret;
    }

}
