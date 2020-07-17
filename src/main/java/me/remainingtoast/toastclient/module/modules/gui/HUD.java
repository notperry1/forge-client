package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD extends Module{


    protected final static Minecraft mc = Minecraft.getMinecraft();
    public static boolean panicked = false;

    public HUD() {
        super("HUD", Category.GUI, "HUD");
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (!panicked && this.isEnabled()) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                mc.fontRenderer.drawStringWithShadow(ToastClient.FULLNAME, 5, 5, -1);
                float currY = mc.fontRenderer.FONT_HEIGHT + 5;
                for (Module m : ModuleManager.modules) {
                    if(m.getCategory() == Category.GUI) return;
                    if (m.isEnabled() && m.isDrawn()) {
                        mc.fontRenderer.drawStringWithShadow(m.getDisplayName(), 5, currY + 1, -1);
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
        }
    }
}
