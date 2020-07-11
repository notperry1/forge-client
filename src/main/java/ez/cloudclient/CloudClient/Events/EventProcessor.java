package ez.cloudclient.CloudClient.Events;

import ez.cloudclient.CloudClientMain;
import ez.cloudclient.MinecraftInstance;
import ez.cloudclient.CloudClient.Module.Mod;
import ez.cloudclient.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventProcessor extends MinecraftInstance {
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        ModuleManager.INSTANCE.onRender(event);
    }

    protected final static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            mc.fontRenderer.drawStringWithShadow(CloudClientMain.FULLNAME, 5, 1, -1);
            float currY = mc.fontRenderer.FONT_HEIGHT + 5;
            for (Mod m : ModuleManager.INSTANCE.getMods()) {
                mc.fontRenderer.drawStringWithShadow(m.getDisplayName(), 5, currY, -1);
                currY += mc.fontRenderer.FONT_HEIGHT;
            }
            ModuleManager.INSTANCE.onRenderGameOverlay(event);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ModuleManager.INSTANCE.onTick(event);
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
