package me.remainingtoast.toastclient.gui.click;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ToastClientGUI extends GuiScreen {

    public static List<MutableTriple<Module, Integer, TextWindow>> textWins = new ArrayList<>();
    public List<ModuleWindow> tabs = new ArrayList<>();

    public void initWindows(){
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.COMBAT), "Combat", 70, 30, 35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.EXPLOITS), "Exploits", 70, 105, 35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.GUI), "GUI",70, 180,35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.MISC), "MISC", 70, 255, 35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.MOVEMENT), "Movement", 70, 300, 35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.PLAYER), "Player", 70, 350, 35));
        this.tabs.add(new ModuleWindowDark(ToastClient.MODULE_MANAGER.getModulesInCat(Module.Category.RENDER), "Render", 70, 420,35));

        for(Module m : ToastClient.MODULE_MANAGER.getModules()){
            int i = 0;
            for(Iterator windows = m.getWindows().iterator(); windows.hasNext(); i++){
                TextWindow t = (TextWindow) windows.next();
                textWins.add(new MutableTriple<>(m, i, t));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.fontRenderer.drawStringWithShadow("Toast Client", 2.0f, 2.0f,  3166352);

        Iterator tabsIter = this.tabs.iterator();

        while (tabsIter.hasNext()){
            ModuleWindow w = (ModuleWindow) tabsIter.next();
            w.draw(mouseX, mouseY, 70);
        }

        tabsIter = textWins.iterator();

        while (tabsIter.hasNext()){
            MutableTriple<Module, Integer, TextWindow> e = (MutableTriple<Module, Integer, TextWindow>) tabsIter.next();
            Objects.requireNonNull(ToastClient.MODULE_MANAGER.getModuleByName(e.left.getName())).getWindows().set(e.middle, e.right);
            if (Objects.requireNonNull(ToastClient.MODULE_MANAGER.getModuleByName(e.left.getName())).isEnabled())
            {
                e.right.draw(mouseX, mouseY);
            }
        }
    }

    protected boolean mouseOver(int minX, int minY, int maxX, int maxY, int mX, int mY)
    {
        return mX > minX && mX < maxX && mY > minY && mY < maxY;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        Iterator windowsIter;

        ModuleWindow w;

        if (mouseButton == 0)
        {
            windowsIter = this.tabs.iterator();

            while (windowsIter.hasNext())
            {
                w = (ModuleWindow) windowsIter.next();
                w.onLmPressed();
            }

            windowsIter = textWins.iterator();

            while (windowsIter.hasNext())
            {
                MutableTriple<Module, Integer, TextWindow> e = (MutableTriple<Module, Integer, TextWindow>) windowsIter.next();
                if (Objects.requireNonNull(ToastClient.MODULE_MANAGER.getModuleByName(e.left.getName())).isEnabled())
                {
                    e.right.onLmPressed();
                }
            }
        } else if (mouseButton == 1)
        {
            windowsIter = this.tabs.iterator();

            while (windowsIter.hasNext())
            {
                w = (ModuleWindow) windowsIter.next();
                w.onRmPressed();
            }
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state == 0)
        {
            Iterator fernflowerMoment = this.tabs.iterator();

            while (fernflowerMoment.hasNext())
            {
                ModuleWindow w = (ModuleWindow) fernflowerMoment.next();
                w.onLmReleased();
            }

            fernflowerMoment = textWins.iterator();

            while (fernflowerMoment.hasNext())
            {
                MutableTriple<Module, Integer, TextWindow> e = (MutableTriple<Module, Integer, TextWindow>) fernflowerMoment.next();

                if (Objects.requireNonNull(ToastClient.MODULE_MANAGER.getModuleByName(e.left.getName())).isEnabled())
                {
                    e.right.onLmReleased();
                }
            }
        }

        super.mouseReleased(mouseX, mouseY, state);
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
        for (ModuleWindow w : this.tabs)
        {
            w.onKeyPressed(keyCode);
        }

        super.keyTyped(typedChar, keyCode);
    }

    public boolean doesGuiPauseGame()
    {
        return mc.isSingleplayer();
    }
}
