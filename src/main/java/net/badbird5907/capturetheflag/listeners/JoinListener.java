package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.game.Team;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (TeamManager.isPlayerPlaying(event.getPlayer())) {
            if(CTFGame.jailedRed.contains(event.getPlayer().getUniqueId())){
                event.getPlayer().teleport(CTFGame.redJail);
            }
            else if(CTFGame.jailedBlue.contains(event.getPlayer().getUniqueId())) {
                event.getPlayer().teleport(CTFGame.blueJail);
            }else{
                Team team = TeamManager.players.get(event.getPlayer().getUniqueId());
                if(team == Team.BLUE){
                    event.getPlayer().teleport(CTFGame.blueSpawn);
                }else if(team == Team.RED){
                    event.getPlayer().teleport(CTFGame.redSpawn);
                }
            }
        }
    }
}
