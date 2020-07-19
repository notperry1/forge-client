package me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.components;

import me.remainingtoast.toastclient.gui.rewrite.click.NewToastGui;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.Component;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.RenderUtil;
import me.remainingtoast.toastclient.util.TimerUtil;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ModuleComponent extends Component {
    private Module module;
    private boolean extended, binding;
    public TimerUtil descTimer = new TimerUtil();

    public ModuleComponent(Module module, float x, float y, float offsetx, float offsety, float w, float h) {
        super(module.getName(), x, y, offsetx, offsety, w, h);
        this.module = module;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void moved(float x, float y) {
        super.moved(x, y);
    }

    @Override
    public void drawScreen(int mx, int my, float partialTicks) {
        final boolean hovered = RenderUtil.withinBounds(mx, my, getX(), getY(), getW(), getH());
        RenderUtil.drawRect2(getX(), getY(), getW(), getH(), hovered ? new Color(0, 0, 0, 200).getRGB() : (this.module.isEnabled() ? new Color(5, 5, 5, 200).getRGB() : new Color(14, 14, 14, 200).getRGB()));
        if (this.module.isEnabled()) {
            RenderUtil.drawRect2(getX(), getY(), 1, getH(), Color.CYAN.darker().getRGB());
        }
        try {

        }
        catch (Exception ex) {}
        NewToastGui.font.drawStringWithShadow(isBinding() ? "Press a key..." : getLabel(), getX() + getW() / 2 - NewToastGui.font.getStringWidth(isBinding() ? "Press a key..." : getLabel()) / 2, getY() + getH() / 2 - NewToastGui    .font.getHeight() / 2, module.isEnabled() ? -1 : 0xff707070);
        if (hovered) {
            if (!this.module.getDesc().equals("") && descTimer.passed(1000)) {
                NewToastGui.font.drawCenteredStringWithShadow(module.getDesc(), mx + 4, my - 5, -1);
            }
        } else {
            descTimer.reset();
        }
        if (isExtended())
            super.drawScreen(mx, my, partialTicks);
    }

    @Override
    public void mouseClicked(int mx, int my, int button) {
        final boolean hovered = RenderUtil.withinBounds(mx, my, getX(), getY(), getW(), getH());
        switch (button) {
            case 0:
                if (hovered && !isBinding())
                    module.setEnabled(!module.isEnabled());
                break;
            case 1:
                if (hovered && !getSubComponents().isEmpty())
                    setExtended(!isExtended());
                break;
            case 2:
                if (hovered)
                    setBinding(!isBinding());
                break;
            default:
                break;
        }
        if (isExtended())
            super.mouseClicked(mx, my, button);
    }

    @Override
    public void mouseReleased(int x, int y, int button) {
        if (isExtended())
            super.mouseReleased(x, y, button);
    }

    @Override
    public void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        if (isBinding() && module instanceof Module) {
            module.setNewKey(key == Keyboard.KEY_ESCAPE || key == Keyboard.KEY_SPACE || key == Keyboard.KEY_DELETE ? Keyboard.KEY_NONE : key);
            MessageUtil.sendRawMessage("Bound " + getLabel() + " to " + Keyboard.getKeyName(module.getKey()));
            setBinding(false);
        }
    }

    public Module getModule() {
        return module;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isBinding() {
        return binding;
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }
}