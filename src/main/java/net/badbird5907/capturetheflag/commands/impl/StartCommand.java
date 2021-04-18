package net.badbird5907.capturetheflag.commands.impl;

import net.badbird5907.capturetheflag.commands.CTFCommand;
import net.badbird5907.capturetheflag.game.CTFGame;
import org.bukkit.command.CommandSender;

public class StartCommand extends CTFCommand {
    public StartCommand(String command, String desc,String perm) {
        super(command, desc,perm);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        System.out.println("Starting CTF");
        CTFGame.startGame(sender);
    }
}
