package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "HudEditor", category = Module.Category.GUI, description = "Hud editor", hidden = true, key = 41)
public class HudEditor extends Module {

    private me.remainingtoast.toastclient.gui.hud.HudEditor hudEditor;

    public void onEnable(){
        if(mc.world == null || mc.player == null) return;
        if(hudEditor == null){
            hudEditor = new me.remainingtoast.toastclient.gui.hud.HudEditor();
            hudEditor.initGui();
        }
        mc.displayGuiScreen(hudEditor);
    }

}
