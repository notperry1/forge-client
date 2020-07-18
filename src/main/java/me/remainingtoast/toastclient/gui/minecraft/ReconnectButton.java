package me.remainingtoast.toastclient.gui.minecraft;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.module.modules.player.AutoReconnect;
import me.remainingtoast.toastclient.util.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.multiplayer.GuiConnecting;

import java.util.concurrent.TimeUnit;

public class ReconnectButton extends GuiButton {
    public ReconnectButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        timer.reset();
        mod = ToastClient.MODULE_MANAGER.getModuleByClass(AutoReconnect.class);
        ReconnectTimer = mod.Delay.getValue() * 1000f;
    }

    private AutoReconnect mod;
    private TimerUtil timer;
    private float ReconnectTimer;


    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        if(visible){
            if(mod.isEnabled() && !timer.passed(ReconnectTimer)){
                this.displayString = "AutoReconnect (" + TimeUnit.MILLISECONDS.toSeconds(Math.abs(timer.getTime()+(long)ReconnectTimer) - System.currentTimeMillis()) + ")";
            } else if (!mod.isEnabled()){
                this.displayString = "AutoReconnect";
            }
            if(timer.passed(ReconnectTimer) && mod.isEnabled()){
                mc.displayGuiScreen(new GuiConnecting(null, mc, "play.cb2k.xyz", 25565));
            }
        }
    }
}
