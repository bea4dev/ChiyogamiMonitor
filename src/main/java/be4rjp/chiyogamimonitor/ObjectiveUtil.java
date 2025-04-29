package be4rjp.chiyogamimonitor;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.List;

public class ObjectiveUtil {
    public static void setLine(Objective objective, List<String> list) {
        int index = list.size();

        for (String line : list) {
            index -= 1;

            line = line.length() > 40 ? line.substring(0, 40) : line;

            Score score = objective.getScore(line);
            score.setScore(index);
        }
    }
}
