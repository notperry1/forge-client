package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.module.Module;

import java.util.ArrayList;
import java.util.List;

import static me.remainingtoast.toastclient.ToastClient.MODULE_MANAGER;

public class Panic extends Command {

    private static boolean isPanicking = false;
    private final List<Module> wasEnabled = new ArrayList<>();

    public Panic() {
        super("Panic", "Shutdowns the client", "shutdown", "panic");
    }

    @Override
    protected void call(String[] args) {
        if (mc.currentScreen != null) return;
        isPanicking = true;
        for (Module module : MODULE_MANAGER.getModules()) {
            if (module.isEnabled()) {
                module.disable();
                wasEnabled.add(module);
            }
        }
    }
}
