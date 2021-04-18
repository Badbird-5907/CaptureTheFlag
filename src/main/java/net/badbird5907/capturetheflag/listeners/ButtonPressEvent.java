package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.game.Team;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ButtonPressEvent implements Listener {
    @EventHandler
    public void onPress(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType().name().toLowerCase().contains("button")){
                if(!TeamManager.isPlayerPlaying(event.getPlayer()))
                    return;
                Team team = TeamManager.players.get(event.getPlayer().getUniqueId());
                Location loc = event.getClickedBlock().getLocation();
                System.out.println("Debug: Button Location: X: " + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ() + "\n " + loc);
                if(event.getClickedBlock().getLocation().equals(CTFGame.blueReleaseButton) || event.getClickedBlock().getLocation().toString() == CTFGame.blueReleaseButton.toString()){
                    System.out.println("Debug: Blue release button");
                    if(team == Team.BLUE)
                        event.getPlayer().sendMessage(ChatColor.RED + "You can't release your enemies!");
                    else CTFGame.unJail(Team.BLUE,event.getPlayer());
                }else if(event.getClickedBlock().getLocation().equals(CTFGame.redReleaseButton) || event.getClickedBlock().getLocation().toString() == CTFGame.redReleaseButton.toString()){
                    System.out.println("Debug: Red release button");
                    if(team == Team.RED)
                        event.getPlayer().sendMessage(ChatColor.RED + "You can't release your enemies!");
                    else CTFGame.unJail(Team.RED,event.getPlayer());
                }
            }
        }
    }
}
