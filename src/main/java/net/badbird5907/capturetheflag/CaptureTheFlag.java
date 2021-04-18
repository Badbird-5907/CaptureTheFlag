package net.badbird5907.capturetheflag;

import lombok.Getter;
import net.badbird5907.capturetheflag.commands.CommandManager;
import net.badbird5907.capturetheflag.game.CTFGame;
import net.badbird5907.capturetheflag.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CaptureTheFlag extends JavaPlugin {
    public static FileConfiguration getConfigFile() {
        return getInstance().getConfig();
    }

    @Getter
    private static CaptureTheFlag instance;
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getCommand("capturetheflag").setExecutor(new CommandManager());
        Bukkit.getPluginManager().registerEvents(new ChatEvent(),this);
        Bukkit.getPluginManager().registerEvents(new PickupEvent(),this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(),this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(),this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlaceListener(),this);
        Bukkit.getPluginManager().registerEvents(new ButtonPressEvent(),this);
        CTFGame.enable();
        CommandManager.enable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
