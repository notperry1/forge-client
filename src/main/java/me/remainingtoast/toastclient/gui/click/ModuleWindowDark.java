package me.remainingtoast.toastclient.gui.click;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.modules.gui.ClickGui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleWindowDark extends ModuleWindow {

    public ModuleWindowDark(HashSet<Module> mods, String name, int len, int posX, int posY)
    {
        super(mods, name, len, posX, posY);
    }

    @Override
    public void draw(int mX, int mY, int leng) {
        this.mouseX = mX;
        this.mouseY = mY;
        this.len = leng;
        this.font = mc.fontRenderer;

        GuiScreen.drawRect(this.posX, this.posY - 10, this.posX + this.len, this.posY, this.mouseOver(this.posX, this.posY - 10, this.posX + this.len, this.posY) ? -1877995504 : -1875890128);
        this.font.drawStringWithShadow(this.name, (float) (this.posX + this.len / 2 - this.font.getStringWidth(this.name) / 2), (float) (this.posY - 9), 7384992);

        if (this.mouseOver(this.posX, this.posY - 10, this.posX + this.len, this.posY) && this.lmDown){ this.dragging = true; }
        if (!this.lmHeld) { this.dragging = false; }
        if (this.dragging) {
            this.posX = this.mouseX - (this.prevmX - this.posX);
            this.posY = this.mouseY - (this.prevmY - this.posY);
        }

        this.prevmX = this.mouseX;
        this.prevmY = this.mouseY;
        int count = 0;

        for (Object mods : (new LinkedHashMap<>(this.mods)).entrySet()) {
            Entry m = (Entry) mods;
            int c2;

            try {
                GuiScreen.drawRect(this.posX, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14, this.mouseOver(this.posX, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14) ? 1882206320 : 1879048192);
                this.font.drawStringWithShadow(this.cutText(((Module) m.getKey()).getName(), this.len), (float) (this.posX + 2), (float) (this.posY + 3 + count * 14), ((Module) m.getKey()).isEnabled() ? 7401440 : 12632256);
                GuiScreen.drawRect((Boolean) m.getValue() ? this.posX + this.len - 2 : this.posX + this.len - 1, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14, (Boolean) m.getValue() ? -1619984400 : 1601241072);

                if (this.mouseOver(this.posX, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14)) {
                    GL11.glTranslated(0.0D, 0.0D, 300.0D);
                    Matcher mat = Pattern.compile("\\b.{1,22}\\b\\W?").matcher(((Module) m.getKey()).getDesc());
                    c2 = 0;

                    int c3 = 0;

                    mat.reset();

                    while (mat.find()) {
                        GuiScreen.drawRect(this.posX + this.len + 3, this.posY - 1 + count * 14 - c2 * 10 + c3 * 10, this.posX + this.len + 6 + this.font.getStringWidth(mat.group().trim()), this.posY + count * 14 - c2 * 10 + c3 * 10 + 9, -1879048144);
                        this.font.drawStringWithShadow(mat.group(), (float) (this.posX + this.len + 5), (float) (this.posY + count * 14 - c2 * 10 + c3 * 10), -1);

                        ++c3;
                    }

                    if (this.lmDown) {
                        if(ToastClient.MODULE_MANAGER.getModuleByClass(ClickGui.class).equals(m.getKey())){
                            ((Module) m.getKey()).disable();
                        }
                        ((Module) m.getKey()).toggle();
                        this.lmDown = false;
                    }

                    if (this.rmDown) {
                        this.mods.replace((Module) m.getKey(), !(Boolean) m.getValue());
                    }

                    GL11.glTranslated(0.0D, 0.0D, -300.0D);
                }
                /*
                if ((Boolean) m.getValue()) {
                    for (Iterator i = ((Module) m.getKey()).getSettings().iterator(); i.hasNext(); GuiScreen.drawRect(this.posX + this.len - 1, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14, -1619984400)) {
                        SettingBase s = (SettingBase) i.next();

                        ++count;

                        if (s instanceof SettingMode) {
                            this.drawModeSetting(s.toMode(), this.posX, this.posY + count * 14);
                        }

                        if (s instanceof SettingToggle) {
                            this.drawToggleSetting(s.toToggle(), this.posX, this.posY + count * 14);
                        }

                        if (s instanceof SettingSlider) {
                            this.drawSliderSetting(s.toSlider(), this.posX, this.posY + count * 14);
                        }
                    }

                    ++count;

                    this.drawBindSetting((Module) m.getKey(), this.keyDown, this.posX, this.posY + count * 14);
                    GuiScreen.drawRect(this.posX + this.len - 1, this.posY + count * 14, this.posX + this.len, this.posY + 14 + count * 14, -1619984400);
                }
                 */
                ++count;
            } catch (Exception e) {
                c2 = 10;

                for (StackTraceElement e69 : e.getStackTrace()) {
                    this.font.drawStringWithShadow(e69.toString(), 10.0F, (float) c2, 16719904);
                    c2 += 10;
                }
            }
        }
    }
}
