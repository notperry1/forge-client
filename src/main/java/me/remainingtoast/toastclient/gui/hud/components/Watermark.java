package me.remainingtoast.toastclient.gui.hud.components;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.gui.hud.HudComponent;
import me.remainingtoast.toastclient.gui.hud.HudManifest;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

@HudManifest(label = "Watermark", x = 2, y = 2, width = 58, height = 18, showLabel = false)
public class Watermark extends HudComponent {

    @Override
    public void onDraw(ScaledResolution scaledResolution){
        super.onDraw(scaledResolution);
        if(mc.world == null || mc.player == null) return;
        float x = getX();
        float y = getY();
        mc.fontRenderer.drawStringWithShadow(ToastClient.FULLNAME, x, y, Color.WHITE.getRGB());
    }

}
