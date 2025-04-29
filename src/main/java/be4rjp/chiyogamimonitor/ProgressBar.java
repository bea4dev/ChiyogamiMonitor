package be4rjp.chiyogamimonitor;

import org.bukkit.ChatColor;

/**
 * プログレスバー
 * 以下のような形で出力される
 * [|||||||||||||||]
 */
public class ProgressBar {

    //プログレスバーの長さ
    private final int maxLength;
    //進捗率(%)
    private double percent = 0;

    /**
     * プログレスバーの長さ
     *
     * @param maxLength
     */
    public ProgressBar(int maxLength) {
        this.maxLength = maxLength;
    }

    private int getProgressValue() {
        double rate = percent / 100.0;
        int value = (int) ((double) maxLength * rate);
        return rate > 0 && value < 1 ? 1 : value;
    }


    /**
     * 進捗率(%)を設定します
     *
     * @param percent 進捗率(%)
     * @return ProgressBar
     */
    public ProgressBar setProgressPercent(double percent) {
        this.percent = percent;
        return this;
    }


    /**
     * 進捗率を設定します
     *
     * @param rate 進捗率
     * @return ProgressBar
     */
    public ProgressBar setProgress(double rate) {
        return setProgressPercent(rate * 100);
    }


    /**
     * 文字列として出力します
     *
     * @param barColor
     * @return String
     */
    public String toString(String barColor) {
        int value = getProgressValue();

        String m = "|";
        StringBuilder ms = new StringBuilder();
        ms.append("§r§7[");
        ms.append(barColor);
        for (int i = 0; i < value; i++) {
            ms.append(m);
        }
        ms.append("§7");
        int rem = maxLength - value;
        for (int i1 = 0; i1 < rem; i1++) {
            ms.append(m);
        }
        ms.append("]§r");
        return ms.toString();
    }


    /**
     * 文字列として出力します
     * バーの色を進捗率によって赤、黄、緑に変化させます
     *
     * @return String
     */
    public String toString() {
        if (percent < 33.3) {
            return toString(ChatColor.GREEN.toString());
        } else if (percent < 66.6) {
            return toString(ChatColor.YELLOW.toString());
        } else {
            return toString(ChatColor.RED.toString());
        }
    }

    /**
     * 文字列として出力します
     * バーの色を進捗率によって赤、黄、緑に変化させます
     *
     * @return String
     */
    public String toStringColorReversed() {
        if (percent < 33.3) {
            return toString(ChatColor.RED.toString());
        } else if (percent < 66.6) {
            return toString(ChatColor.YELLOW.toString());
        } else {
            return toString(ChatColor.GREEN.toString());
        }
    }
}
