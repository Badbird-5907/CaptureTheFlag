package net.badbird5907.capturetheflag.commands;

import net.badbird5907.capturetheflag.CaptureTheFlag;
import net.badbird5907.capturetheflag.commands.impl.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {
    private static CTFCommand[] c = new CTFCommand[]{
            new AddPlayer("addplayer","Assign a player to a team","capturetheflag.command.addplayer")
            ,new StartCommand("start","Start the game","capturetheflag.command.start")
            ,new EndGame("end","End the game forcefully","capturetheflag.command.endgame")
            ,new ClearTeamsCommand("clearteams","Clear all teams","capturetheflag.command.clearteams")
            ,new GiveBanner("givebanner","Give banners","capturetheflag.command.givebanner")
    };
    private static ArrayList<CTFCommand> commands = new ArrayList<>();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            sendUsage(sender);
        }
        else{
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (String arg : args) {
                i++;
                if(i == 1){
                    continue;
                }
                if(i == 2){
                    sb.append(arg);
                    continue;
                }
                sb.append(" " + arg);
            }
            String[] fArgs = sb.toString().split(" ");
            System.out.println("Args: " + Arrays.toString(fArgs));
            executeCommand(args[0],sender,fArgs);
        }
        return true;
    }
    public static void enable(){
        for (CTFCommand ctfCommand : c) {
            commands.add(ctfCommand);
        }
    }
    public static void sendUsage(CommandSender sender){
        sender.sendMessage(ChatColor.GREEN + "Capture The Flag");
        sender.sendMessage(ChatColor.GREEN + "V." + CaptureTheFlag.getInstance().getDescription().getVersion());
        sender.sendMessage(ChatColor.GREEN + "By: Badbird5907");
        sender.sendMessage(ChatColor.BLUE + "============ Commands ==========");
        sender.sendMessage(ChatColor.AQUA + "Format: <Command> | <Description>");
        commands.forEach(ctf -> sender.sendMessage(ChatColor.GOLD + ctf.getCommand() + ChatColor.BLUE + " | " + ChatColor.YELLOW + ctf.getDesc()));
        sender.sendMessage(ChatColor.BLUE + "================================");
    }
    public static void executeCommand(String command,CommandSender sender, String[] args){
        boolean sendUsage = true;
        for (CTFCommand c1 : commands) {
            if(command.equalsIgnoreCase(c1.getCommand())){
                if(c1.isPlayerOnly()){
                    if(!(sender instanceof Player)){
                        sendUsage = false;
                        sender.sendMessage(ChatColor.RED + "This is player Only!");
                        break;
                    }
                }
                if(!(c1.getPermission() == "")){
                    if(!sender.hasPermission(c1.getPermission())){
                        sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                        return;
                    }
                }
                c1.execute(sender, args);
                sendUsage = false;
                break;
            }
        }
        if(sendUsage)
            sendUsage(sender);
        return;
    }
}
