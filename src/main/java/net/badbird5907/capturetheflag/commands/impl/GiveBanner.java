package net.badbird5907.capturetheflag.commands.impl;

import net.badbird5907.capturetheflag.commands.CTFCommand;
import net.badbird5907.capturetheflag.game.CTFGame;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveBanner extends CTFCommand {
    public GiveBanner(String command, String desc, String permission) {
        super(command, desc, permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(CTFGame.started){
            sender.sendMessage(ChatColor.RED + "You can't do this while the game is running!");
            return;
        }
        Player p = (Player) sender;
        p.getInventory().addItem(CTFGame.blueBanner,CTFGame.redBanner);
        sender.sendMessage(ChatColor.GREEN + "The banners have been added to your inventory!");
    }
}
