package ez.cloudclient.command;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<ez.cloudclient.command.Command> commands;

    public CommandManager() {
        commands = new ArrayList<>();

    }

    public ArrayList<ez.cloudclient.command.Command> getCommands() {
        return commands;
    }


}
