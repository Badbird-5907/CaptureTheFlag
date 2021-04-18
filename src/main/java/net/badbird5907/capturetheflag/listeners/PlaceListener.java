package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(CTFGame.started){
            Material material = event.getBlock().getType();
            if(material == Material.RED_BANNER || material == Material.BLUE_BANNER){
                event.setCancelled(true);
            }
        }
    }
}
