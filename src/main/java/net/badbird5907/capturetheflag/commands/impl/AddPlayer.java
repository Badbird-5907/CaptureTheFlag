package net.badbird5907.capturetheflag.commands.impl;

import net.badbird5907.capturetheflag.commands.CTFCommand;
import net.badbird5907.capturetheflag.game.Team;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddPlayer extends CTFCommand {
    public AddPlayer(String command, String desc,String perm) {
        super(command, desc,perm);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 2){
            Player p = null;
            try{
                p = Bukkit.getPlayer(args[0]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Couldn't find the player  " + args[0] + "! Make sure they are online!");
                return;
            }
            Team team = null;
            final String a1 = args[1].toLowerCase();
            if(a1.equalsIgnoreCase("red"))
                team = Team.RED;
            else if(a1.equalsIgnoreCase("blue")){
                team = Team.BLUE;
            }
            if(team == null){
                sender.sendMessage(ChatColor.RED + args[1] + " Is not a team!\nUsage: /ctf addplayer <player> [team]");
                return;
            }
            TeamManager.players.put(p.getUniqueId(),team);
            sender.sendMessage(ChatColor.GREEN + "Added " + p.getDisplayName() + "to " + team.getColor() + team + ChatColor.GREEN + " Team.");
            return;
        }
    }
}
