package be4rjp.chiyogamimonitor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import world.chiyogami.chiyogamilib.monitor.PerformanceMonitor;
import world.chiyogami.chiyogamilib.monitor.WorkMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Monitor extends BukkitRunnable {

    private final Scoreboard scoreboard;
    private Objective objective;

    public Monitor() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        this.scoreboard = scoreboardManager.getNewScoreboard();
    }


    @Override
    public void run() {
        if (objective != null) {
            objective.unregister();
        }

        objective = scoreboard.registerNewObjective("pm", "pm", "§6§lTick Performance Monitor");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> lines = new ArrayList<>();

        lines.add("");

        double tps = ((double) Math.round(ChiyogamiMonitor.getTpsMonitor().getTPS() * 10.0)) / 10.0;
        lines.add("TPS: " + new ProgressBar(15).setProgress(Math.min(tps / 20.0, 1.0)).toStringColorReversed() + " " + String.format("%.1f", tps));
        lines.add(" ");

        long fullTime = PerformanceMonitor.getFullServerTickNanoTime();
        lines.add("FULL_TICK: " + new ProgressBar(8).setProgress(Math.min((fullTime / 1000000.0) / 50.0, 1.0)) + " " + String.format("%.1f", fullTime / 1000000.0) + "ms");

        long worldTime = PerformanceMonitor.getAllWorldTickNanoTime();
        lines.add("WORLD_TICK: " + new ProgressBar(8).setProgress(Math.min((worldTime / 1000000.0) / 50.0, 1.0)) + " " + String.format("%.1f", worldTime / 1000000.0) + "ms");

        lines.add("  ");

        for (Map.Entry<String, Long> entry : PerformanceMonitor.getWorldTickNanoTimeMap().entrySet()) {
            String worldName = entry.getKey();
            long time = entry.getValue();

            String name = worldName.length() > 12 ? worldName.substring(0, 12) : worldName;

            lines.add(name + ": " + new ProgressBar(8).setProgress(Math.min((time / 1000000.0) / 50.0, 1.0)) + " " + String.format("%.1f", time / 1000000.0) + "ms");
        }

        lines.add("   ");
        lines.add("MULTI_THREAD_TICK:" + (WorkMode.MULTI_THREAD_TICK ? "§a true" : "§c false"));

        ObjectiveUtil.setLine(objective, lines);
    }

    public void setMonitor(Player player) {
        player.setScoreboard(scoreboard);
    }
}
