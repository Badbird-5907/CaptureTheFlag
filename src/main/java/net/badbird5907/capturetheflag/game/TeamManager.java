package net.badbird5907.capturetheflag.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class TeamManager {
    public static HashMap<UUID, Team> players = new HashMap<>();
    public static boolean isPlayerPlaying(Player p){
        return players.containsKey(p.getUniqueId());
    }
    public static boolean isPlayerPlaying(UUID p){
        return players.containsKey(p);
    }
    public static String getOtherTeamToString(Team team){
        if(team == Team.BLUE){
            return "Blue";
        }
        return "Red";
    }
    public static Team getOtherTeam(Team team){
        if(team == Team.BLUE){
            return Team.RED;
        }
        return Team.BLUE;
    }
    public static Location getTeamSpawn(Team team){
        if(team == Team.BLUE)
            return CTFGame.blueSpawn;
        return CTFGame.redSpawn;
    }
    public static Team whatTeamsBanner(ItemStack item){
        if(item == CTFGame.blueBanner)
            return Team.BLUE;
        else if(item == CTFGame.redBanner)
            return Team.RED;
        return null;
    }
    public static ItemStack getTeamBanner(Team team){
        if (team == Team.BLUE)
            return CTFGame.blueBanner;
        else return CTFGame.redBanner;
    }
}
