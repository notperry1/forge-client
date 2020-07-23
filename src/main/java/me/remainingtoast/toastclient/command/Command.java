package me.remainingtoast.toastclient.command;


import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;

public abstract class Command {

    private String label;
    private String description;
    private String usage;
    private String[] alias;
    protected Minecraft mc = Minecraft.getMinecraft();

    public Command() {
        if (getClass().isAnnotationPresent(CommandManifest.class)) {
            CommandManifest moduleManifest = getClass().getAnnotation(CommandManifest.class);
            this.label = moduleManifest.label();
            this.alias = moduleManifest.aliases();
            this.description = moduleManifest.description();
            this.usage = moduleManifest.usage();
        }
    }

    public String getName() {
        return label.replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return "" + TextFormatting.GRAY + "Command: "+label+"\n" + TextFormatting.GRAY +
                "           Description: \""+description+"\"\n"+ TextFormatting.GRAY +
                "           Aliases: " + Arrays.toString(alias) + "\n"+ TextFormatting.GRAY +
                "           Usage: \"" + usage + TextFormatting.GRAY + "\"";
    }

//    protected abstract void call(final String[] args);

    public void onRun(String[] args) {
    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAlias() {
        return this.alias;
    }

    public String getUsage() {
        return this.usage;
    }
}

