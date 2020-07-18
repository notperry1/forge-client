package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.gui.click.ToastClientGUI;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "ClickGUI", category = Module.Category.GUI)
public class ClickGui extends Module {

    public static ToastClientGUI clickGui = new ToastClientGUI();

    public void onEnable(){
        if (mc.currentScreen != clickGui) {
            mc.displayGuiScreen(clickGui);
        }
        this.disable();
    }
}
