package me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.Component;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.components.ModuleComponent;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class CategoryFrame extends Frame {
    private final Module.Category moduleCategory;

    private final int BORDER = 2;
    private final int TEXT_GAP = 1;

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
        final Minecraft mc = Minecraft.getMinecraft();
        int offsetY = 10;


//        RenderUtil.drawRect2(getX(), getY(), getW(), getH() + (isExtended() ? getHeight() : 70), 0xff050505);
//        RenderUtil.drawBorderedRect2(getX() + .5f, getY() + .5f, getW() - 1f, getH() - 1f + (isExtended() ? getHeight() : 70), 0.5f, 0xff282828, 0xff282828);
//        RenderUtil.drawBorderedRect2(getX() + 1.5f, getY() + 1.5f, getW() - 3f, getH() - 3f + (isExtended() ? getHeight() : 70), 0.5f, 0xff111111, 0xff3C3C3C);
        RenderUtil.drawRect2(getX(), getY(), getW(), getH() + ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory).size() * 10 + 5, 0xff050505);
        RenderUtil.drawBorderedRect2(getX() + .5f, getY() + .5f, getW() - 1f, getH() - 1f + ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory).size() * 10 + 5, 0.5f, 0xff282828, 0xff282828);
        RenderUtil.drawBorderedRect2(getX() + 1.5f, getY() + 1.5f, getW() - 3f, getH() - 3f + ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory).size() * 10 + 5, 0.5f, 0xff111111, 0xff3C3C3C);
//        ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory).size()
        for (float i = 2.5f; i < getW() - 2.5f; i++) {
            int color = Color.getHSBColor(i / 115, 0.9f, 1).getRGB();
            RenderUtil.drawRect2(getX() + i, getY() + 2.5f, 1, 0.5f, color);
        }
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(getLabel(), getX() + 5, getY() + 5, -1);
        for (Module module : ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory)) {
            mc.fontRenderer.drawStringWithShadow(module.getName(), this.getX() + 5, this.getY() + offsetY + BORDER + TEXT_GAP + 1, module.isEnabled() ? 0xFFC255FF : 0xFF7A6E80);
            offsetY += mc.fontRenderer.FONT_HEIGHT + TEXT_GAP;
        }
        final boolean hovered = this.mouseOver(this.getX(), this.getX() + this.getW(), getY(), getY() + getH());
        final boolean inside = x >= this.getX() && x <= this.getX() + this.getW() && y >= this.getY() && x <= this.getY() + this.getH();

        if (hovered) {
            int height = BORDER;
            for (Module module : ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory)) {
                final boolean insideComponent = x >= (this.getX() + BORDER) && x <= (this.getX() + this.getW() - BORDER) && y >= (this.getY() + BORDER + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 1 + height) && y <= (this.getY() + BORDER + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) + 1 + height);
                if (insideComponent) {
                    final int tooltipWidth = mc.fontRenderer.getStringWidth(module.getDesc());
                    final int tooltipHeight = mc.fontRenderer.FONT_HEIGHT;

                    GlStateManager.translate(x - tooltipWidth / 2, y - tooltipHeight, 0);
                    // Tooltip background
                    RenderUtil.drawRect(-2, -6, tooltipWidth + 2, 5, 0x80101010);
                    RenderUtil.drawRect(-1, -5, tooltipWidth + 1, 4, 0xAD101010);

                    // Tooltip
                    mc.fontRenderer.drawStringWithShadow(module.getDesc(), 0, -5, 0xADC255FF);
                    GlStateManager.translate(-(x - tooltipWidth / 2), -(y - tooltipHeight), 0);
                }
                height += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + TEXT_GAP;
            }
        }


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
        final boolean inside = x >= this.getX() && x <= this.getX() + this.getW() && y >= this.getY() && y <= this.getY() + this.getH();
        if (inside && button == 0) {
            int offsetY = BORDER;
            for (Module module : ToastClient.MODULE_MANAGER.getModulesInCat(moduleCategory)) {
                final boolean insideTitlebar = y <= this.getY() + BORDER + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 1;
                final boolean insideComponent = x >= (this.getX() + BORDER) && x <= (this.getX() + this.getW() - BORDER) && y >= (this.getY() + BORDER + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 1 + offsetY) && y <= (this.getY() + BORDER + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) + 1 + offsetY);
                if (!insideTitlebar && insideComponent) {
                    module.toggle();
                    this.setDragging(false);
                }
                offsetY += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + TEXT_GAP;
            }
        }
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
    private float   getHeight() {
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
