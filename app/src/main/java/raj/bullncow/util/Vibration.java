package raj.bullncow.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Vibrator;

import raj.bullncow.App;

/**
 * Created by Vishal Raj on
 * 12/14/2015
 * 12:39 PM
 */
public class Vibration {
    public static void vibrate(Context c, long ms) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (App.isVibration())
            v1.vibrate(ms);
    }

    public static void vibrate(Context c, long ms, AudioAttributes aa) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (App.isVibration())
            v1.vibrate(ms, aa);
    }

    public static void vibrate(Context c, long[] pattern, int repeat) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (App.isVibration())
            v1.vibrate(pattern, repeat);
    }

    public static void vibrate(Context c, long[] pattern, int repeat, AudioAttributes aa) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (App.isVibration())
            v1.vibrate(pattern, repeat, aa);
    }

    public static void vibrate(Context c, long ms, boolean really) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (really)
            v1.vibrate(ms);
    }

    public static void vibrate(Context c, long ms, AudioAttributes aa, boolean really) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (really)
            v1.vibrate(ms, aa);
    }

    public static void vibrate(Context c, long[] pattern, int repeat, boolean really) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (really)
            v1.vibrate(pattern, repeat);
    }

    public static void vibrate(Context c, long[] pattern, int repeat, AudioAttributes aa, boolean really) {
        Vibrator v1 = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
        if (really)
            v1.vibrate(pattern, repeat, aa);
    }
}
