package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;

@CommandManifest(label = "ClearChat", description = "Clears all messages in chat", aliases = {"cchat"}, usage = "clearchat")
public class ClearChat extends Command {

    @Override
    public void onRun(final String[] args) {
        if (mc.player == null) return;
        for (int i = 0; i < mc.ingameGUI.getChatGUI().getLineCount(); i++) {
            mc.ingameGUI.getChatGUI().deleteChatLine(i);
        }
        MessageUtil.sendMessage("Cleared chat", MessageUtil.Color.GREEN);
    }
}
