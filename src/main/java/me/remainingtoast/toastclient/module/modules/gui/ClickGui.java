package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.gui.click.ToastClientGUI;
import me.remainingtoast.toastclient.gui.rewrite.click.NewToastGui;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "ClickGUI", category = Module.Category.GUI, description = "Click GUI", key = 54, hidden = true)
public class ClickGui extends Module {

    public static ToastClientGUI clickGui = new ToastClientGUI();
    private NewToastGui gui;

    public void onEnable(){
        //TODO: Figure out how to disable clickGui inside GUI
        if(gui == null){
            gui = new NewToastGui();
            gui.init();
        }
        mc.displayGuiScreen(gui);
//        if (mc.currentScreen != clickGui) {
//            mc.displayGuiScreen(clickGui);
//            disable();
//        }
//        disable();
    }
}
