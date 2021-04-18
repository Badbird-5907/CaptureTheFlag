package net.badbird5907.capturetheflag.commands.impl;

import net.badbird5907.capturetheflag.commands.CTFCommand;
import net.badbird5907.capturetheflag.game.CTFGame;
import org.bukkit.command.CommandSender;

public class EndGame extends CTFCommand {
    public EndGame(String command, String desc, String permission) {
        super(command, desc, permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        CTFGame.endGameWithoutWinner(sender);
    }
}
