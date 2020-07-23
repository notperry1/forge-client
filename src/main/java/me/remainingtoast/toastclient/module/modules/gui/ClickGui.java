package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.gui.click.NewToastGui;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "ClickGUI", category = Module.Category.GUI, description = "Click GUI", key = 54, hidden = true)
public class ClickGui extends Module {

    public static NewToastGui clickGui = new NewToastGui();

    public void onEnable(){
        //TODO: Figure out how to disable clickGui inside GUI
        if(clickGui == null){
            clickGui = new NewToastGui();
            clickGui.init();
        }
        mc.displayGuiScreen(clickGui);
    }
}
