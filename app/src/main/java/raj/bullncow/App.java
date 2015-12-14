package raj.bullncow;

import android.app.Application;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Vishal Raj on
 * 10/22/2015
 * 12:52 AM
 */
public class App extends Application {

    public static Typeface REGULAR, BOLD, DASH, BOLD_ITALIC, ITALIC, LIGHT, LIGHT_ITALIC;

    public static int MODE_CLASSICAL = 1, MODE_MOVE = 2, mode;

    static float height, width;
    static int level, difficulty;

    static int[][] difficultyMatrix = {{10, 7, 4}, {12, 9, 5}, {14, 11, 6}, {16, 13, 7}, {18, 15, 8}, {20, 17, 9}};

    public static boolean sound, vibration;

    public static String playerName;


    @Override
    public void onCreate() {
        super.onCreate();
        defineTypeface();

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    private void defineTypeface() {
        BOLD = Typeface.createFromAsset(getAssets(), "fonts/quicksand_bold.otf");
        BOLD_ITALIC = Typeface.createFromAsset(getAssets(), "fonts/quicksand_boldItalic.otf");
        ITALIC = Typeface.createFromAsset(getAssets(), "fonts/quicksand_italic.otf");
        LIGHT = Typeface.createFromAsset(getAssets(), "fonts/quicksand_light.otf");
        REGULAR = Typeface.createFromAsset(getAssets(), "fonts/quicksand.otf");
        LIGHT_ITALIC = Typeface.createFromAsset(getAssets(), "fonts/quicksand_lightItalic.otf");
        DASH = Typeface.createFromAsset(getAssets(), "fonts/quicksand_dash.otf");
    }

    public static Typeface getTypeface(int style) {
        switch (style) {
            case 1:
                return REGULAR;
            case 2:
                return BOLD;
            case 3:
                return LIGHT;
            case 4:
                return BOLD_ITALIC;
            case 5:
                return LIGHT_ITALIC;
            case 6:
                return ITALIC;
            case 7:
                return DASH;
            default:
                return REGULAR;
        }
    }

    public static String getPrimaryColor() {
        return "#5db3ba";
    }

    public static float getHeight() {
        return height;
    }

    public static float getWidth() {
        return width;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int l) {
        level = l;
    }

    public static void setMode(int m) {
        mode = m;
    }

    public static int getMode() {
        return mode;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        App.difficulty = difficulty;
    }

    public static int getMoves() {
        return difficultyMatrix[level - 4][difficulty];
    }

    public static boolean isSound() {
        return sound;
    }

    public static void setSound(boolean sound) {
        App.sound = sound;
    }

    public static boolean isVibration() {
        return vibration;
    }

    public static void setVibration(boolean vibration) {
        App.vibration = vibration;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        App.playerName = playerName;
    }
}
