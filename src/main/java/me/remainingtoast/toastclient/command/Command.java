package me.remainingtoast.toastclient.command;


import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraft.client.Minecraft;

import java.util.Arrays;

public abstract class Command {

    final String name;
    final String displayName;
    final String description;
    final String[] aliases;
    protected Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.displayName = name.toLowerCase().replaceAll(" ", "_");
        this.aliases = aliases;
        this.description = description;
    }

    public String getName() {
        return name.replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return "Command{" +
                "name= "+name+"\n," +
                "description= "+description+"\n,"+
                "aliases= " + Arrays.toString(aliases);
    }

    protected abstract void call(String[] args);
}

