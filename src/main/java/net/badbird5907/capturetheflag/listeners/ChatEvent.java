package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(TeamManager.players.containsKey(event.getPlayer().getUniqueId())){
            event.setFormat(TeamManager.players.get(event.getPlayer().getUniqueId()).getColor() + event.getPlayer().getDisplayName() + ChatColor.RESET + ": " + event.getFormat());
        }else{
            event.setFormat(ChatColor.GRAY + "" + ChatColor.BOLD + "Spectator " + ChatColor.RESET + "" + ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.RESET + ": " + event.getFormat());
        }
    }
}
