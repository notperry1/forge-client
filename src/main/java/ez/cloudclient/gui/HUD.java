package ez.cloudclient.gui;

import ez.cloudclient.CloudClient;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static ez.cloudclient.util.RgbUtil.GetRainbowColor;

public class HUD {

    /*
     * Added by RemainingToast 12/07/20
     */

    protected final static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) throws InterruptedException {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            int number = 0;
            int Min = 0;
            int Max = 255;
            number = Min + (int)(Math.random() * ((Max - Min) + 1));
            mc.fontRenderer.drawStringWithShadow(CloudClient.FULLNAME, 5, 5, -1);
            float currY = mc.fontRenderer.FONT_HEIGHT + 5;
            for (Module m : ModuleManager.modules) {
                if(m.isEnabled()){
                    mc.fontRenderer.drawStringWithShadow(m.getDisplayName(), 5, currY + 1, GetRainbowColor(number, 90.0f, 50.0f, 1.0f).getRGB());
                    currY += mc.fontRenderer.FONT_HEIGHT;
                }
            }
        }
    }
}
