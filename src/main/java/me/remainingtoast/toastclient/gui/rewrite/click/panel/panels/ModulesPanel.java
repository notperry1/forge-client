package me.remainingtoast.toastclient.gui.rewrite.click.panel.panels;

import me.remainingtoast.toastclient.gui.rewrite.click.panel.Panel;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame.CategoryFrame;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame.ConsoleFrame;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame.Frame;
import me.remainingtoast.toastclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;

public class ModulesPanel extends Panel {

    public static ArrayList<Frame> frames;

    public ModulesPanel() {
        super("Modules");
        frames = new ArrayList<>();
        int x = 2;
        int y = 20;
        frames.add(new ConsoleFrame(x, y, 120, 18));
        for (Module.Category moduleCategory : Module.Category.values()) {
            if(moduleCategory.equals(Module.Category.NONE)) return;
            frames.add(new CategoryFrame(moduleCategory, x, y, 100, 10));
            if (x + 225 >= new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth()) {
                x = 2;
                y += 20;
            } else x += 125;
        }
        frames.forEach(Frame::init);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        frames.forEach(frame -> frame.drawScreen(mouseX, mouseY, partialTicks));
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        frames.forEach(frame -> frame.mouseClicked(mouseX, mouseY, mouseButton));

    }

    @Override
    public void keyTyped(final char typedChar, final int keyCode) {
        frames.forEach(frame -> frame.keyTyped(typedChar, keyCode));

    }

    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        frames.forEach(frame -> frame.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    public void onGuiClosed() {
        frames.forEach(frame -> {
            frame.onGuiClosed();
            frame.setDragging(false);
        });
    }
}
