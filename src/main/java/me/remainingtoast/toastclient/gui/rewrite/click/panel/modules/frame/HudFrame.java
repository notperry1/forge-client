package me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.gui.hud.HudComponent;
import me.remainingtoast.toastclient.gui.rewrite.click.NewToastGui;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.Component;
import me.remainingtoast.toastclient.util.RenderUtil;

import java.awt.*;

public class HudFrame extends Frame {
    public HudFrame(float x, float y, float w, float h) {
        super("Hud Components", x, y, w, h);
    }

    @Override
    public void init() {
        float offsetY = getH() - 2f;
        for (HudComponent hudComponent : ToastClient.INSTANCE.getHudManager().getValues()) {
//            getComponents().add(new HudGuiComponent(hudComponent, getX(), getY(), 2, offsetY, getW() - 4, 15));
            offsetY += 15;
        }
        super.init();
    }

    @Override
    public void moved(float x, float y) {
        super.moved(x, y);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        RenderUtil.drawRect2(getX() - 1, getY() - 2, getW() + 3, getH(), new Color(45, 45, 45).getRGB());
        NewToastGui.font.drawStringWithShadow(getLabel(), getX() + getW() / 2 - NewToastGui.font.getStringWidth(getLabel()) / 2, (getY() - 2) + getH() / 2 - NewToastGui.font.getHeight() / 2, -1);
        super.drawScreen(x, y, partialTicks);
        if (isExtended())
            resetHeights();
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

    private void resetHeights() {
        float offsetY = getH() - 2f;
        for (Component component : getComponents()) {
            component.setOffsety(offsetY);
            component.moved(getX(), getY());
            /*
            if (component instanceof HudGuiComponent) {
                if (((HudGuiComponent) component).isExtended()) {
                    for (Component comp : component.getSubComponents()) {
                        offsetY += comp.getH();
                    }
                }
            }
             */
            offsetY += component.getH();
        }
    }

    private float getHeight() {
        float offsetY = 0;
        for (Component component : getComponents()) {
            /*
            if (component instanceof HudGuiComponent) {
                if (((HudGuiComponent) component).isExtended()) {
                    for (Component comp : component.getSubComponents()) {
                        offsetY += comp.getH();
                    }
                }
            }
             */
            offsetY += component.getH();
        }
        return offsetY;
    }
}
