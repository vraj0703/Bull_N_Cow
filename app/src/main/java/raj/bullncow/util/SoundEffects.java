package raj.bullncow.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

import raj.bullncow.App;
import raj.bullncow.R;

public class SoundEffects {

    public static final String TAG = SoundEffects.class.toString();

    public static final SoundEffects INSTANCE = new SoundEffects();

    // Sound ID can't be 0 (zero)
    public static final int SOUND_CLICK = 1, SOUND_ERROR = 2, SOUND_LOSE = 3,
            SOUND_WIN = 4, SOUND_YES = 5, SOUND_SCROLL = 6, SOUND_SWIPE = 7;

    public SoundEffects() {

    }

    public static SoundEffects getInstance() {
        return INSTANCE;
    }

    public SoundPool soundPool;
    public HashMap<Integer, Integer> soundPoolMap;
    int priority = 1;
    int no_loop = 0;
    public int volume;
    float normal_playback_rate = 1f;

    public Context context;

    public void init(Context context) {
        this.context = context;
        soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap
                .put(SOUND_CLICK, soundPool.load(context, R.raw.click, 1));
        soundPoolMap.put(SOUND_ERROR, soundPool.load(context, R.raw.error, 1));
        soundPoolMap.put(SOUND_LOSE, soundPool.load(context, R.raw.lose, 1));
        soundPoolMap.put(SOUND_WIN, soundPool.load(context, R.raw.win, 1));
        soundPoolMap.put(SOUND_YES, soundPool.load(context, R.raw.yes, 1));
        soundPoolMap.put(SOUND_SCROLL, soundPool.load(context, R.raw.scroll, 1));
        soundPoolMap.put(SOUND_SWIPE, soundPool.load(context, R.raw.swipe, 1));
        AudioManager audioManager = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public void playSound(int soundId) {
        if (App.isSound())
            soundPool.play(soundId, volume, volume, priority, no_loop,
                    normal_playback_rate);
    }

    public void playSound(int soundId, boolean really) {
        // Log.i(TAG, "!!!!!!!!!!!!!! playSound_1 !!!!!!!!!!");
        if (really)
            soundPool.play(soundId, volume, volume, priority, no_loop,
                    normal_playback_rate);
        // Toast.makeText(context, "Now you can hear sound effect!",
        // Toast.LENGTH_LONG).show();
    }
}
