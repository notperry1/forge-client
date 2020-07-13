package ez.cloudclient.module.modules.combat;

import akka.io.TcpExt;
import ez.cloudclient.module.Module;
import ez.cloudclient.setting.Setting;
import ez.cloudclient.util.MessageUtil;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import java.util.Comparator;

public class CrystalAura extends Module{
    /*
     *  Created by LittleDraily on 13/07/20
     */
    private static double yaw;
    private static double pitch;
    public CrystalAura() {
        super("CrystalAura", Category.COMBAT, "Automatically places crystals.");
    }
    public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me) {
        double dirx = me.posX - px;
        double diry = me.posY - py;
        double dirz = me.posZ - pz;

        double len = Math.sqrt(dirx*dirx + diry*diry + dirz*dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        //to degree
        pitch = pitch * 180.0d / Math.PI;
        yaw = yaw * 180.0d / Math.PI;

        yaw += 90f;

        return new double[]{yaw,pitch};
    }
    private void lookAtPacket(final double px, final double py, final double pz, final EntityPlayer me) {
        final double[] v = calculateLookAt(px, py, pz, me);
        setYawAndPitch((float)v[0], (float)v[1]);
    }
    private static void setYawAndPitch(final float yaw1, final float pitch1) {
        yaw = yaw1;
        pitch = pitch1;
    }
    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void onTick() {
        final EntityEnderCrystal crystal = (EntityEnderCrystal) mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).map(entity -> entity).min(Comparator.comparing(c -> mc.player.getDistance(c))).orElse(null);
        if(crystal != null){
            BlockPos breakTarget = new BlockPos(crystal.posX, crystal.posY, crystal.posZ);
            if (mc.player.getDistance((Entity) crystal) < 4){
                if(Math.round(mc.player.lastTickPosY) > crystal.lastTickPosY-1) return;
                this.lookAtPacket(crystal.posX, crystal.posY, crystal.posZ, (EntityPlayer) mc.player);
                mc.playerController.attackEntity((EntityPlayer) mc.player, (Entity) crystal);
                mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
}
