package me.remainingtoast.toastclient.module.modules.gui;

import me.remainingtoast.toastclient.gui.rewrite.click.panel.modules.frame.ConsoleFrame;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "Console", category = Module.Category.GUI, description = "Open console to enter commands")
public class Console extends Module {

    @Override
    public void toggle() {
        ConsoleFrame.visible = !ConsoleFrame.visible;
    }
}
