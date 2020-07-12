package ez.cloudclient.gui;

import ez.cloudclient.CloudClientMain;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD {
    protected final static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            mc.fontRenderer.drawStringWithShadow(CloudClientMain.FULLNAME, 5, 1,-1);
            float currY = mc.fontRenderer.FONT_HEIGHT + 5;
            for (Module m : ModuleManager.modules) {
                if(m.isEnabled()){
                    mc.fontRenderer.drawStringWithShadow(m.displayName, 5, currY, -1);
                    currY += mc.fontRenderer.FONT_HEIGHT;
                }
            }
        }
    }
}
