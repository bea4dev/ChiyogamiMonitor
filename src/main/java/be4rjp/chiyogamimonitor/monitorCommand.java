package be4rjp.chiyogamimonitor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class monitorCommand implements CommandExecutor, TabExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args == null) return false;
        if (args.length == 0) return false;
    
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be executed from the console.");
            return true;
        }
        
        switch (args[0]){
            case "show":{
                ChiyogamiMonitor.monitor.setMonitor((Player) sender);
                break;
            }
            
            case "hide":{
                ((Player) sender).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                break;
            }
        }
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    
        List<String> list = new ArrayList<>();
        
        if (args.length == 1) {
            list.add("show");
            list.add("hide");
            
            return list;
        }
        
        return null;
    }
}
