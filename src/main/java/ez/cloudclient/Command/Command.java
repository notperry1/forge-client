package ez.cloudclient.Command;

import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

public abstract class Command {

    protected String label;
    protected String description;
    protected List<String> aliases;

    public final Minecraft mc = Minecraft.getMinecraft();

    public static String commandPrefix = "~";

    public Command(String label, String description, String... aliases) {
        this.label = label;
        this.description = "Descriptionless";
        this.aliases = Arrays.asList(aliases);
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getCommandPrefix() {
        return commandPrefix;
    }

    public String getLabel() {
        return label;
    }

    public String getChatLabel() {
        return "[" + label + "] ";
    }

    public abstract void call(String[] args);

    public List<String> getAliases() {
        return aliases;
    }
}
