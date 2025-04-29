package be4rjp.chiyogamimonitor;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TPSMonitor extends BukkitRunnable {

    private long beforeTime = 0;
    private int tick = 0;
    private double tps = 20.0;

    @Override
    public void run() {
        //TPS測定
        if (tick == 20) {
            long time = System.currentTimeMillis();
            tps = 20.0 / (((double) (time - beforeTime)) / 1000.0);
            beforeTime = time;
            tick = 0;
        }

        tick++;
    }

    /**
     * TPSを取得する
     *
     * @return
     */
    public double getTPS() {
        return tps;
    }

    /**
     * 処理開始
     *
     * @param plugin
     */
    public void start(Plugin plugin) {
        beforeTime = System.currentTimeMillis();
        this.runTaskTimerAsynchronously(plugin, 0, 1);
    }
}
