package net.badbird5907.capturetheflag.listeners;

import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.game.Team;
import net.badbird5907.capturetheflag.game.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(!CTFGame.started)
            return;
        if (TeamManager.isPlayerPlaying(event.getPlayer())){
            Team team = TeamManager.players.get(event.getPlayer().getUniqueId());
            //player is in thier team side and they have the flag
            if(team == CTFGame.inWhosTeamSide(event.getPlayer())){
                if(event.getPlayer().getInventory().contains(TeamManager.getTeamBanner(TeamManager.getOtherTeam(team)))){
                    event.getPlayer().getInventory().remove(TeamManager.getTeamBanner(TeamManager.getOtherTeam(team)));
                    CTFGame.endGame(team);
                }
            }
        }
    }
}
