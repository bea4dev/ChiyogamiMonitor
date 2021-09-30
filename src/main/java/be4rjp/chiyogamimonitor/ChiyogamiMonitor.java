package be4rjp.chiyogamimonitor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChiyogamiMonitor extends JavaPlugin {
    
    public static Monitor monitor;
    
    private static ChiyogamiMonitor plugin;
    
    private static TPSMonitor tpsMonitor;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
    
        Config.load();
        
        tpsMonitor = new TPSMonitor();
        tpsMonitor.start(this);
        
        monitor = new Monitor();
        monitor.runTaskTimerAsynchronously(this, 0, 20);
        
        Objects.requireNonNull(getCommand("chmonitor")).setExecutor(new monitorCommand());
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static ChiyogamiMonitor getPlugin() {return plugin;}
    
    public static TPSMonitor getTpsMonitor() {return tpsMonitor;}
}
