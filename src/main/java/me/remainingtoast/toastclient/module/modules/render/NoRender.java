package me.remainingtoast.toastclient.module.modules.render;

import me.kix.lotus.property.annotations.Property;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;

@ModuleManifest(label = "NoRender", category = Module.Category.RENDER)
public class NoRender extends Module {

    @Property("Totem")
    public boolean totem = true;

    @Property("Fog")
    public boolean fog = true;

    @Property("Mob")
    public boolean mob = true;

    @Property("Sand")
    public boolean sand = true;

    @Property("Items")
    public boolean items = true;

    @Property("XP")
    public boolean xp = true;

    


}
