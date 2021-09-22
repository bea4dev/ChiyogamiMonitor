package be4rjp.chiyogamimonitor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChiyogamiMonitor extends JavaPlugin {
    
    public static Monitor monitor;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        monitor = new Monitor();
        monitor.runTaskTimerAsynchronously(this, 0, 20);
        
        Objects.requireNonNull(getCommand("chmonitor")).setExecutor(new monitorCommand());
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
