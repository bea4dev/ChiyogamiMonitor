package be4rjp.chiyogamimonitor;

import org.bukkit.configuration.file.YamlConfiguration;
import world.chiyogami.chiyogamilib.monitor.WorkMode;

import java.io.File;

public class Config {
    
    public static void load(){
        File file = new File("plugins/ChiyogamiMonitor", "config.yml");
        file.getParentFile().mkdirs();
        
        if(!file.exists()){
            ChiyogamiMonitor.getPlugin().saveResource("config.yml", false);
        }
        
        //ロードと値の保持
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
    
        WorkMode.MULTI_THREAD_TICK = yml.getBoolean("multi-thread");
    }
}
