package me.remainingtoast.toastclient.module.modules.render;

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
        super("HUD", Category.RENDER, "HUD");
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (!panicked && this.isEnabled()) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                int number;
                int Min = 0;
                int Max = 255;
                number = Min + (int) (Math.random() * ((Max - Min) + 1));
                mc.fontRenderer.drawStringWithShadow(ToastClient.FULLNAME, 5, 5, -1);
                float currY = mc.fontRenderer.FONT_HEIGHT + 5;
                for (Module m : ModuleManager.modules) {
                    if (m.isEnabled() && m.isDrawn()) {
                        if(m.getDisplayName().equalsIgnoreCase("hud")) return;
                        mc.fontRenderer.drawStringWithShadow(m.getDisplayName(), 5, currY + 1, -1);
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
        }
    }
}
