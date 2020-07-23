package me.remainingtoast.toastclient.gui.click.panel.modules.frame;

import me.remainingtoast.toastclient.util.RenderUtil;

import java.awt.*;

public class ConsoleFrame extends Frame {

    public static boolean visible = false;

    public ConsoleFrame(float x, float y, float w, float h) {
        super("Console", x, y, w, h);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void drawScreen(int mx, int my, float partialTicks) {
        if(!visible) return;
        RenderUtil.drawRect2(getX(), getY(), getW() + 49, getH() + 100, 0xff050505);
        RenderUtil.drawBorderedRect2(getX() + .5f, getY() + .5f, getW() + 50, getH() - 1f + 100, 0.5f, 0xff282828, 0xff282828);
        RenderUtil.drawBorderedRect2(getX() + 1.5f, getY() + 1.5f, getW() + 50, getH() - 3f + 100, 0.5f, 0xff111111, 0xff3C3C3C);
        for (float i = 2.5f; i < getW(); i++) {
            int color = Color.getHSBColor(i / 115, 0.9f, 1).getRGB();
            RenderUtil.drawRect2(getX() + i, getY() + 2.5f, 52, 0.5f, color);
        }
        RenderUtil.drawOutlinedRect(this.getX() + 3, this.getY() + 107, this.getW() + 47, mc.fontRenderer.FONT_HEIGHT, new Color(255, 255, 255).getRGB(), 1);
        mc.fontRenderer.drawStringWithShadow(getLabel(), getX() + 5, getY() + 5, -1);

        super.drawScreen(mx, my, partialTicks);
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
    }


    @Override
    public void mouseReleased(int x, int y, int button) {
        super.mouseReleased(x, y, button);
    }

    @Override
    public void keyTyped(char character, int key) {
        super.keyTyped(character, key);
    }
}
