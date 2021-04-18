package net.badbird5907.capturetheflag.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

@Getter
@Setter
public abstract class CTFCommand {
    private String command,desc,permission;
    private boolean playerOnly;
    public CTFCommand(String command,String desc,String permission,boolean playerOnly){
        this.command = command;
        this.desc = desc;
        this.permission = permission;
        this.playerOnly = playerOnly;
    }
    public CTFCommand(String command, String desc,String permission){
        this.command = command;
        this.desc = desc;
        this.permission = permission;
        this.playerOnly = false;
    }
    public abstract void execute(CommandSender sender,String[] args);
}
