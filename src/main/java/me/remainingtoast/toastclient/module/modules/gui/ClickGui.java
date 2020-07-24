package me.remainingtoast.toastclient.module.modules.gui;

import me.kix.lotus.property.annotations.Clamp;
import me.kix.lotus.property.annotations.Mode;
import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.gui.click.NewToastGui;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@ModuleManifest(label = "ClickGUI", category = Module.Category.GUI, description = "Click GUI", key = 54, hidden = true)
public class ClickGui extends Module {

    @Property("Red")
    @Clamp(minimum = "0", maximum = "255")
    private float red = 255;

    @Property("Green")
    @Clamp(minimum = "0", maximum = "255")
    private static float green = 255;

    @Property("Blue")
    @Clamp(minimum = "0", maximum = "255")
    private static float blue = 255;

    @Property("Mode")
    @Mode({"Normal", "Test"})
    private String mode = "Normal";

    public static NewToastGui clickGui = new NewToastGui();

    public void onEnable(){
        //TODO: Figure out how to disable clickGui inside GUI
        switch (mode.toLowerCase()){
            case "normal":
                if(clickGui == null){
                    clickGui = new NewToastGui();
                    clickGui.init();
                }
                mc.displayGuiScreen(clickGui);
                break;
            case "test":
                MessageUtil.sendMessage("Test", MessageUtil.Color.GRAY);
                break;
        }

    }
}
