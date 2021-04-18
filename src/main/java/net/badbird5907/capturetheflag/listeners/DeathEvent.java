package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(!CTFGame.started){
            return;
        }
        if(TeamManager.isPlayerPlaying(event.getEntity())){
            String deathMessage = event.getDeathMessage();
            event.setCancelled(true);
            Bukkit.broadcastMessage(deathMessage);
            event.getEntity().setHealth(20.0);
            if (event.getEntity().getInventory().contains(CTFGame.redBanner)){
                CTFGame.dropRedBanner(event.getEntity(),event.getEntity().getLocation());
                event.getEntity().getInventory().remove(CTFGame.redBanner);
            }
            if (event.getEntity().getInventory().contains(CTFGame.blueBanner)){
                CTFGame.dropBlueBanner(event.getEntity(),event.getEntity().getLocation());
                event.getEntity().getInventory().remove(CTFGame.blueBanner);
            }
            CTFGame.jail(event.getEntity());
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(CTFGame.started){
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
            }
        }
    }
}
