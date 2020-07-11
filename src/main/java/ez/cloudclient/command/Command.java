package ez.cloudclient.command;


import net.minecraft.client.Minecraft;

public abstract class Command {
    protected Minecraft mc = Minecraft.getMinecraft();
    final String name;
    final String displayName;
    final String description;
    final String[] aliases;

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.displayName = name.toLowerCase().replaceAll(" ", "_");
        this.aliases = aliases;
        this.description = description;
    }

    protected abstract void call(String[] args);
}

