package net.badbird5907.capturetheflag.game;

import net.badbird5907.capturetheflag.CaptureTheFlag;
import net.badbird5907.capturetheflag.util.ItemStackBuilder;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class CTFGame {
    public static ArrayList<UUID> jailedBlue = new ArrayList<>();
    public static ArrayList<UUID> jailedRed = new ArrayList<>();
    public static ItemStack blueBanner = null;
    public static ItemStack redBanner = null;
    public static Location blueSpawn = null;
    public static Location blueJail = null;
    public static Location redSpawn = null;
    public static Location redJail = null;
    public static Location redReleaseButton = null;
    public static Location blueReleaseButton = null;
    public static boolean started = false;
    public static void startGame(CommandSender sender){
        if(started == false){
            TeamManager.players.forEach(((uuid, team) -> {
                System.out.println(uuid + " | " + team);
                Player p = null;
                try{
                    p = Bukkit.getPlayer(uuid);
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "Couldn't find the player " + uuid + "! Have they logged off?");
                    return;
                }
                p.setPlayerListHeader(team.getColor() + "");
                p.setPlayerListName(team.getColor() + p.getDisplayName());
            }));
            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The Game Has Started! Good Luck!");
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendTitle(ChatColor.GREEN + "Game Started!",ChatColor.GREEN + "" + ChatColor.BOLD + "Good Luck!");
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL,1,1);
            }
            started = true;
        }else{
            sender.sendMessage(ChatColor.RED + "The game has already started!");
        }
    }
    public static void endGame(Team winningTeam){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendTitle(winningTeam.getColor() + "" + winningTeam + " Has Won The Game!",ChatColor.GREEN + "" + ChatColor.BOLD +  "GG");
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL,1,1);
        }
        jailedRed = new ArrayList<>();
        jailedBlue = new ArrayList<>();
        TeamManager.players.forEach((k,v)->{
           Player p = null;
           try{
               p = Bukkit.getPlayer(k);
           } catch (Exception e) {
               return;
           }
           if(v == Team.RED){
               p.teleport(redSpawn);
           }else{
               p.teleport(blueSpawn);
           }
        });
        started = false;
    }
    public static void endGameWithoutWinner(CommandSender sender){
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " has ended the game!");
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendTitle(ChatColor.RED + "Game Over!","");
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL,1,1);
        }
        started = false;
    }
    public static void dropBlueBanner(Player p,Location location){
        Item item =  location.getWorld().dropItem(location,blueBanner);
        item.setGlowing(true);
        p.setGlowing(false);
        Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue's Banner has been dropped!");
    }
    public static void dropRedBanner(Player p,Location location){
        Item item =  location.getWorld().dropItem(location,redBanner);
        item.setGlowing(true);
        p.setGlowing(false);
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Red's Banner has been dropped!");
    }
    public static Team inWhosTeamSide(Player p) {
        if (p.getLocation().getX() >= 0.0) {
            return Team.RED;
        }
        return Team.BLUE;
    }
    public static void jail(Player p){
        if (TeamManager.isPlayerPlaying(p)) {
            Team team = TeamManager.players.get(p.getUniqueId());
            if(team == Team.BLUE){
                p.teleport(redJail);
                jailedBlue.add(p.getUniqueId());
            }
            if(team == Team.RED){
                p.teleport(blueJail);
                jailedRed.add(p.getUniqueId());
            }
            Bukkit.broadcastMessage(team.getColor() + "" + ChatColor.BOLD + p.getDisplayName() + " has been jailed!" );
        }
        System.out.println(p.getName() + " is not playing CTF.");
    }
    public static void unJail(Team team1, Player player){
        Team team = TeamManager.getOtherTeam(team1);
        if(doesPlayerHaveFlag(player)){
            player.sendMessage(ChatColor.RED + "You can't release your team while having the flag!");
        }
        if(team == Team.RED){
            Bukkit.broadcastMessage(ChatColor.RED + "Red Team has been released by " + player.getDisplayName() + "!");
            player.teleport(redSpawn);
            for (UUID uuid : jailedRed) {
                Player p = null;
                try{
                    p = Bukkit.getPlayer(uuid);
                } catch (Exception e) { return; }
                p.teleport(redSpawn);
            }
            jailedRed = new ArrayList<>();
        }
        else{
            Bukkit.broadcastMessage(ChatColor.AQUA + "Blue Team has been released by " + player.getDisplayName() + "!");
            player.teleport(blueSpawn);
            for (UUID uuid : jailedBlue) {
                Player p = null;
                try{
                    p = Bukkit.getPlayer(uuid);
                } catch (Exception e) { return; }
                p.teleport(blueSpawn);
            }
            jailedBlue = new ArrayList<>();
        }
    }
    public static boolean doesPlayerHaveFlag(Player p){
        if(p.getInventory().contains(redBanner))
            return true;
        else if(p.getInventory().contains(blueBanner))
            return true;
        return false;
    }
    public static void enable(){
        blueBanner = new ItemStackBuilder(Material.BLUE_BANNER).name(ChatColor.AQUA + "Blue Team Banner").build();
        redBanner = new ItemStackBuilder(Material.RED_BANNER).name(ChatColor.RED + "Red Team Banner").build();
        blueSpawn = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("spawn.blue.world")),CaptureTheFlag.getConfigFile().getDouble("spawn.blue.x"),CaptureTheFlag.getConfigFile().getDouble("spawn.blue.y"),CaptureTheFlag.getConfigFile().getDouble("spawn.blue.z"));
        blueJail = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("jail.blue.world")),CaptureTheFlag.getConfigFile().getDouble("jail.blue.x"),CaptureTheFlag.getConfigFile().getDouble("jail.blue.y"),CaptureTheFlag.getConfigFile().getDouble("jail.blue.z"));
        redSpawn = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("spawn.red.world")),CaptureTheFlag.getConfigFile().getDouble("spawn.red.x"),CaptureTheFlag.getConfigFile().getDouble("spawn.red.y"),CaptureTheFlag.getConfigFile().getDouble("spawn.red.z"));
        redJail = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("jail.red.world")),CaptureTheFlag.getConfigFile().getDouble("jail.red.x"),CaptureTheFlag.getConfigFile().getDouble("jail.red.y"),CaptureTheFlag.getConfigFile().getDouble("jail.red.z"));
        redReleaseButton = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("jail.red.button.world")),CaptureTheFlag.getConfigFile().getDouble("jail.red.button.x"),CaptureTheFlag.getConfigFile().getDouble("jail.red.button.y"),CaptureTheFlag.getConfigFile().getDouble("jail.red.button.z"));
        System.out.println("Debug: red release button: " + redReleaseButton);
        blueReleaseButton = new Location(Bukkit.getWorld(CaptureTheFlag.getConfigFile().getString("jail.blue.button.world")),CaptureTheFlag.getConfigFile().getDouble("jail.blue.button.x"),CaptureTheFlag.getConfigFile().getDouble("jail.blue.button.y"),CaptureTheFlag.getConfigFile().getDouble("jail.blue.button.z"));
        System.out.println("Debug: blue release button: " + blueReleaseButton);
    }
}
