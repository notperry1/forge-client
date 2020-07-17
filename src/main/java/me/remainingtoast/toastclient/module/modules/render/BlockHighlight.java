package me.remainingtoast.toastclient.module.modules.render;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MathUtil;
import me.remainingtoast.toastclient.util.RenderUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class BlockHighlight extends Module {

    public static Color color = new Color(0, 255,0);
    public static float width = 1.5f;

    public BlockHighlight() {
        super("BlockHighlight", Category.RENDER, "Highlight the block you are looking at");
    }

    @SubscribeEvent
    public void render(RenderWorldLastEvent event){
        final RayTraceResult ray = mc.objectMouseOver;
        if(ray.typeOfHit == RayTraceResult.Type.BLOCK){
            final BlockPos blockPos = ray.getBlockPos();
            final IBlockState blockState = mc.world.getBlockState(blockPos);
            if(blockState != Material.AIR && mc.world.getWorldBorder().contains(blockPos)){
                final Vec3d interp = MathUtil.interpolateEntity(mc.player, mc.getRenderPartialTicks());
                RenderUtil.drawBoundingBox(blockState.getSelectedBoundingBox(mc.world, blockPos).grow(0.0020000000949949026D).offset(-interp.x, -interp.y, -interp.z), width, color.getRGB());
            }
        }
    }
}
