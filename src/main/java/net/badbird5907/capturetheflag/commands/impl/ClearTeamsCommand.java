package net.badbird5907.capturetheflag.commands.impl;

import net.badbird5907.capturetheflag.commands.CTFCommand;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class ClearTeamsCommand extends CTFCommand {
    public ClearTeamsCommand(String command, String desc, String permission) {
        super(command, desc, permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        TeamManager.players = new HashMap<>();
        sender.sendMessage(ChatColor.GREEN + "Cleared all teams!");
    }
}
