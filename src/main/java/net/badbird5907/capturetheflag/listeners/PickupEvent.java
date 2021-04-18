package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.game.Team;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupEvent implements Listener {
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        if(CTFGame.started){
            Team pickupTeam = TeamManager.whatTeamsBanner(event.getItem().getItemStack());
            Team playerTeam = TeamManager.players.get(event.getPlayer().getUniqueId());
            if(pickupTeam == null){
                return;
            }
            if(!(pickupTeam == playerTeam)){
                event.setCancelled(true);
                return;
            }
            event.getPlayer().setGlowing(true);
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.BLUE + event.getPlayer() + " has stolen " + TeamManager.getOtherTeam(playerTeam).getColor() + TeamManager.getOtherTeamToString(playerTeam) + ChatColor.BLUE + "'s flag!" );
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(event.getItemDrop().getItemStack() == CTFGame.redBanner || event.getItemDrop().getItemStack() == CTFGame.blueBanner) {
            System.out.println("Debug: banner dropped");
            event.getItemDrop().setGlowing(true);
        }
        if(CTFGame.started){
            if(TeamManager.isPlayerPlaying(event.getPlayer())){
                event.setCancelled(true);
            }
        }
    }
}
