package ez.cloudclient.command.cmds;

import ez.cloudclient.command.Command;

public class HelpCmd extends Command {
    public HelpCmd() {
        super("Help", "Shows Commands", "commands", "cmds");
    }

    @Override
    public void call(String[] args) {

    }
}
