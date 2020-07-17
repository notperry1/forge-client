package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;

public class SignBook extends Command {
    public SignBook() {
        super("SignBook", "Change the author of the book your holding (creative only)", "sbook", "signbook");
    }

    @Override
    protected void call(String[] args) {

    }
}
