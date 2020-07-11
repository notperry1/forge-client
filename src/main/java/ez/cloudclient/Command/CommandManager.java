package ez.cloudclient.Command;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<Command> commands;

    public CommandManager() {
        commands = new ArrayList<>();
        
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }


}
