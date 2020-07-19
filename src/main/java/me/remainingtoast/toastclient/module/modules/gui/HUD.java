package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "HUD", category = Module.Category.GUI, description = "Client HUD Information", aliases = {"HUD"}, hidden = true)
public class HUD extends Module {

    protected final static Minecraft mc = Minecraft.getMinecraft();
    public static boolean panicked = false;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
//        if (this.isEnabled() && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
//            if (mc.gameSettings.showDebugInfo || mc.currentScreen instanceof HudEditor || mc.world == null || mc.player == null)
//                return;
//            final ScaledResolution scaledResolution = new ScaledResolution(mc);
//            ToastClient.INSTANCE.getHudManager().getValues().forEach(hudComponent -> {
//                if (hudComponent.getX() < 0) {
//                    hudComponent.setX(0);
//                }
//                if (hudComponent.getX() + hudComponent.getW() > scaledResolution.getScaledWidth()) {
//                    hudComponent.setX(scaledResolution.getScaledWidth() - hudComponent.getW());
//                }
//                if (hudComponent.getY() < 0) {
//                    hudComponent.setY(0);
//                }
//                if (hudComponent.getY() + hudComponent.getH() > scaledResolution.getScaledHeight()) {
//                    hudComponent.setY(scaledResolution.getScaledHeight() - hudComponent.getH());
//                }
//                if (hudComponent.isShown()) hudComponent.onDraw(scaledResolution);
//            });
        if (!panicked && this.isEnabled()) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                mc.fontRenderer.drawStringWithShadow(ToastClient.FULLNAME, 5, 5, -1);
                float currY = mc.fontRenderer.FONT_HEIGHT + 5;
                for (Module m : ToastClient.MODULE_MANAGER.modulesSet) {
//                    if (m.isEnabled() && m.isDrawn() && !m.getCategory().equals(Category.GUI)) {
                    if (m.isEnabled() && m.isDrawn()) {
                        mc.fontRenderer.drawStringWithShadow(m.getName(), 5, currY + 1, -1);
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
        }
    }
}
