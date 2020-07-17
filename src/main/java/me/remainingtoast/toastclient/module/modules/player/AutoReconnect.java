package me.remainingtoast.toastclient.module.modules.player;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.setting.settings.ValueSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class AutoReconnect extends Module {

    public final ValueSetting<Float> Delay = new ValueSetting<>(5f);

    public AutoReconnect() {
        super("AutoReconnect", Category.PLAYER, "Automatically reconnect to server");
        final ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
    }
}
