package me.remainingtoast.toastclient.gui.hud;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;

public class HudEditor extends GuiScreen {
    private ScaledResolution scaledResolution = null;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if(scaledResolution == null) scaledResolution = new ScaledResolution(mc);
        ToastClient.INSTANCE.getHudManager().getValues().forEach(hudComponent ->{
            if (hudComponent.isDragging()) {
                hudComponent.setX(mouseX + hudComponent.getLastX());
                hudComponent.setY(mouseY + hudComponent.getLastY());
            }
            if (hudComponent.isDragging()) {
                hudComponent.setX(mouseX + hudComponent.getLastX());
                hudComponent.setY(mouseY + hudComponent.getLastY());
            }
            if (hudComponent.getX() < 0) {
                hudComponent.setX(0);
            }
            if (hudComponent.getX() + hudComponent.getW() > new ScaledResolution(mc).getScaledWidth()) {
                hudComponent.setX(new ScaledResolution(mc).getScaledWidth() - hudComponent.getW());
            }
            if (hudComponent.getY() < 0) {
                hudComponent.setY(0);
            }
            if (hudComponent.getY() + hudComponent.getH() > new ScaledResolution(mc).getScaledHeight()) {
                hudComponent.setY(new ScaledResolution(mc).getScaledHeight() - hudComponent.getH());
            }
            //RenderUtil.drawRect2(0, scaledResolution.getScaledHeight() / 2 - 0.5f, scaledResolution.getScaledWidth(), 1, 0xff000000);
            //RenderUtil.drawRect2(scaledResolution.getScaledWidth() / 2 - 0.5f, 0, 1, scaledResolution.getScaledHeight(), 0xff000000);
            if (hudComponent.isShown()) hudComponent.onDraw(new ScaledResolution(mc));

            RenderUtil.drawRect(hudComponent.getX(), hudComponent.getY(), hudComponent.getW(), hudComponent.getH(), hudComponent.isDragging() ? 0x95000000 : 0x80000000);

            if (hudComponent.isLabelShown()) mc.fontRenderer.drawStringWithShadow(hudComponent.getLabel(), hudComponent.getX() + hudComponent.getW() / 2 - mc.fontRenderer.getStringWidth(hudComponent.getLabel()) / 2, hudComponent.getY() + hudComponent.getH() / 2 - mc.fontRenderer.FONT_HEIGHT / 2, new Color(255, 255, 255, 83).getRGB());
        });
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        ToastClient.INSTANCE.getHudManager().getValues().forEach(hudComponent -> {
            final boolean hovered = RenderUtil.withinBounds(mouseX, mouseY, hudComponent.getX(), hudComponent.getY(), hudComponent.getW(), hudComponent.getH());
            switch (mouseButton){
                case 0:
                    if(hovered){
                        hudComponent.setDragging(true);
                        hudComponent.setLastX(hudComponent.getX() - mouseX);
                        hudComponent.setLastY(hudComponent.getY() - mouseY);
                    }
                    break;
                case 1:
                    if(hovered){
                        hudComponent.setShown(!hudComponent.isShown());
                    }
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        ToastClient.INSTANCE.getHudManager().getValues().forEach(hudComponent -> {
            if(state == 0){
                if(hudComponent.isDragging()){
                    hudComponent.setDragging(false);
                }
            }
        });
    }

    @Override
    public void onGuiClosed() {
        ToastClient.INSTANCE.getHudManager().getValues().forEach(hudComponent -> {
            if(hudComponent.isDragging()){
                hudComponent.setDragging(false);
            }
        });
    }

    @Override
    public boolean doesGuiPauseGame() {
        return mc.isSingleplayer();
    }
}
