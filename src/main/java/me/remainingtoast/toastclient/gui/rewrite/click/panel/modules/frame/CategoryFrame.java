package me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.Component;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.components.ModuleComponent;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.RenderUtil;

import java.awt.*;

public class CategoryFrame extends Frame {
    private final Module.Category moduleCategory;

    public CategoryFrame(Module.Category moduleCategory, float x, float y, float w, float h) {
        super(moduleCategory.getLabel(), x, y, w, h);
        this.moduleCategory = moduleCategory;
    }

    @Override
    public void init() {
        super.init();
        float offsetY = getH() - 2f;
        for (Module mod : ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory)) {
            getComponents().add(new ModuleComponent(mod, getX(), getY(), 2, offsetY, getW() - 4, 18));
            offsetY += 15;
        }
    }

    @Override
    public void moved(float x, float y) {
        super.moved(x, y);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        RenderUtil.drawRect2(getX(), getY(), getW(), getH() + (isExtended() ? getHeight() : 0), 0xff050505);
        RenderUtil.drawBorderedRect2(getX() + .5f, getY() + .5f, getW() - 1f, getH() - 1f + (isExtended() ? getHeight() : 0), 0.5f, 0xff282828, 0xff282828);
        RenderUtil.drawBorderedRect2(getX() + 1.5f, getY() + 1.5f, getW() - 3f, getH() - 3f + (isExtended() ? getHeight() : 0), 0.5f, 0xff111111, 0xff3C3C3C);
        for (float i = 2.5f; i < getW() - 2.5f; i++) {
            int color = Color.getHSBColor(i / 115, 0.9f, 1).getRGB();
            RenderUtil.drawRect2(getX() + i, getY() + 2.5f, 1, 0.5f, color);
        }
//        RenderUtil.drawRect2(getX() - 1, getY() - 2, getW() + 3, getH(), new Color(45, 45, 45).getRGB());
//        NewToastGui.font.drawStringWithShadow(getLabel(), getX() + getW() / 2 - NewToastGui.font.getStringWidth(getLabel()) / 2, (getY() - 2) + getH() / 2 - NewToastGui.font.getHeight() / 2, -1);
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
            component.moved(getX(),getY());
            if (component instanceof  ModuleComponent) {
                if (((ModuleComponent) component).isExtended()) {
                    for (Component component1 : component.getSubComponents()) {
                        offsetY += component1.getH();
                    }
                }
            }
            offsetY += component.getH();
        }
    }
    private float getHeight() {
        float offsetY = 0;
        for (Component component : getComponents()) {
            if (component instanceof  ModuleComponent) {
                if (((ModuleComponent) component).isExtended()) {
                    for (Component component1 : component.getSubComponents()) {
                        offsetY += component1.getH();
                    }
                }
            }
            offsetY += component.getH();
        }
        return offsetY;
    }
}
