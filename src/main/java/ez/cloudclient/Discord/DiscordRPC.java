package ez.cloudclient.Discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import ez.cloudclient.CloudClientMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class DiscordRPC {
    public static DiscordRichPresence presence;
    private static boolean connected;
    private static final club.minnced.discord.rpc.DiscordRPC rpc;
    private static String details;
    private static String state;
    private static String gamemode;

    public static void start() {
        if (DiscordRPC.connected) return;
        DiscordRPC.connected = true;

        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRPC.rpc.Discord_Initialize(CloudClientMain.APP_ID, handlers, true, "");
        DiscordRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;

        /* update rpc normally */
        setRpcFromSettings();

        /* update rpc while thread isn't interrupted  */
        new Thread(DiscordRPC::setRpcFromSettingsNonInt, "Discord-RPC-Callback-Handler").start();
    }

    public static void end() {
        DiscordRPC.connected = false;
        DiscordRPC.rpc.Discord_Shutdown();
    }

    private static void setRpcFromSettingsNonInt() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DiscordRPC.rpc.Discord_RunCallbacks();
                String separator = " | ";
                //First Line of RPC
                details = "Loading Minecraft";
                if(Minecraft.getMinecraft().isSingleplayer()){ gamemode = "Singleplayer"; }
                if(!Minecraft.getMinecraft().isSingleplayer()){ gamemode = "Multiplayer"; }


                if(Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && gamemode != null) {
                    details = Minecraft.getMinecraft().getSession().getUsername() + separator + gamemode;
                }
                else if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
                    details = Minecraft.getMinecraft().getSession().getUsername() + separator + "In Main Menu";
                }

                //Second Line of RPC
                if(Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
                    String coords = "x " + Minecraft.getMinecraft().player.posX + "y " + Minecraft.getMinecraft().player.posX + "z " + Minecraft.getMinecraft().player.posZ;
                    if(Minecraft.getMinecraft().isSingleplayer()){state = "Chilling at: " + coords; }
                    if(!Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().getCurrentServerData() != null){
                        state = "Playing on: " + Minecraft.getMinecraft().getCurrentServerData().serverIP;
                    }
                }


                DiscordRPC.presence.details = details;
                DiscordRPC.presence.state = state;
                DiscordRPC.rpc.Discord_UpdatePresence(DiscordRPC.presence);
            }
            catch (Exception e2) { e2.printStackTrace(); }
            try { Thread.sleep(4000L); }
            catch (InterruptedException e3) { e3.printStackTrace(); }
        }
    }

    private static void setRpcFromSettings() {
        DiscordRPC.presence.details = details;
        DiscordRPC.presence.state = state;
        DiscordRPC.presence.largeImageKey = "cloud";
        DiscordRPC.presence.largeImageText = "cloud client";
    }

    static {
        rpc = club.minnced.discord.rpc.DiscordRPC.INSTANCE;
        DiscordRPC.presence = new DiscordRichPresence();
        DiscordRPC.connected = false;
    }
}
