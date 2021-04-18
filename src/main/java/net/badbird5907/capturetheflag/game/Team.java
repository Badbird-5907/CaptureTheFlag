package net.badbird5907.capturetheflag.game;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Team {
    RED(ChatColor.RED),BLUE(ChatColor.AQUA);
    Team(ChatColor color){
        this.color = color;
    }
    @Getter
    private ChatColor color;
}
