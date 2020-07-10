package ez.cloudclient.CloudClient;

import ez.cloudclient.CloudClient.Module.Mod;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventProcessor extends MinecraftInstance {
    private static ResourceLocation texture = new ResourceLocation("wurstblue:wurst-logo.png");

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        ModuleManager.INSTANCE.onRender(event);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            mc.fontRenderer.drawStringWithShadow(CloudClientMain.NAME, 35, 1, -1);

            /*GL11.glColor4f(1, 1, 1, 1);
            GL11.glEnable(GL11.GL_BLEND);
            mc.getTextureManager().bindTexture(texture);
            Gui.drawModalRectWithCustomSizedTexture(0, 3, 0, 0, 72, 18, 72, 18);*/

            float currY = mc.fontRenderer.FONT_HEIGHT + 10;

            for (Mod m : ModuleManager.INSTANCE.getMods()) {
                mc.fontRenderer.drawStringWithShadow(m.getDisplayName(), 1, currY, -1);
                currY += mc.fontRenderer.FONT_HEIGHT;
            }

            ModuleManager.INSTANCE.onRenderGameOverlay(event);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ModuleManager.INSTANCE.onTick(event);

        if (mc.currentScreen instanceof GuiMultiplayer) {
            throw new RuntimeException("Fuck you, this is a singleplayer client ONLY!");
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            final String key = Keyboard.getKeyName(Keyboard.getEventKey());

            if (key != null && !key.isEmpty() && !key.equals("NONE"))
                ModuleManager.INSTANCE.onKeyInput(key);
        }
    }
}
