package raj.bullncow;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import raj.bullncow.fragments.AboutFragment;
import raj.bullncow.fragments.BeerFragment;
import raj.bullncow.fragments.DifficultyFragment;
import raj.bullncow.fragments.HelpFragment;
import raj.bullncow.fragments.HighScoreFragment;
import raj.bullncow.fragments.LevelFragment;
import raj.bullncow.fragments.MainFragment;
import raj.bullncow.fragments.SettingFragment;
import raj.bullncow.game.GameActivity;
import raj.bullncow.util.SoundEffects;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    int stackLevel;
    SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        loadMainFragment();
        createHighScoreDatabaseNTable();
        createSharedPref();
        SoundEffects.getInstance().init(this);
    }

    private void createSharedPref() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getBoolean("is_first_run", true)) {
            editor.putBoolean("sound", true);
            editor.putBoolean("vibration", true);
            editor.putBoolean("is_first_run", false);
            editor.putString("playerName", "Enigma");
            editor.commit();
        }
        App.setPlayerName(sharedPref.getString("playerName", "Enigma"));
        App.setSound(sharedPref.getBoolean("sound", true));
        App.setVibration(sharedPref.getBoolean("vibration", true));

    }

    private void createHighScoreDatabaseNTable() {
        SQLiteDatabase db1 = openOrCreateDatabase("HighScore", MODE_PRIVATE, null);
        for (int i = 4; i <= 9; i++)
            db1.execSQL("CREATE TABLE IF NOT EXISTS level" + i + "(id INTEGER PRIMARY KEY, name VARCHAR, score VARCHAR);");
    }

    public void loadLevelFragment() {
        stackLevel = 1;
        LevelFragment levelFragment = new LevelFragment();
        fm.beginTransaction().replace(R.id.main_container, levelFragment).commit();
    }

    public void loadHelpFragment() {
        stackLevel = 1;
        HelpFragment helpFragment = new HelpFragment();
        fm.beginTransaction().replace(R.id.main_container, helpFragment).commit();
    }

    public void loadAboutFragment() {
        stackLevel = 1;
        AboutFragment aboutFragment = new AboutFragment();
        fm.beginTransaction().replace(R.id.main_container, aboutFragment).commit();
    }

    public void loadHighScoreFragment() {
        stackLevel = 1;
        HighScoreFragment highScoreFragmentFragment = new HighScoreFragment();
        fm.beginTransaction().replace(R.id.main_container, highScoreFragmentFragment).commit();
    }

    public void loadSettingFragment() {
        stackLevel = 4;//only for setting
        settingFragment = new SettingFragment();
        fm.beginTransaction().replace(R.id.main_container, settingFragment).commit();
    }

    public void loadDifficultyFragment() {
        stackLevel = 2;
        DifficultyFragment difficultyFragment = new DifficultyFragment();
        fm.beginTransaction().replace(R.id.main_container, difficultyFragment).commit();
    }

    public void loadMainFragment() {
        stackLevel = 0;
        MainFragment mainFragment = new MainFragment();
        fm.beginTransaction().replace(R.id.main_container, mainFragment).commit();
    }

    public void loadBeerFragment() {
        stackLevel = 3;
        BeerFragment beerFragment = new BeerFragment();
        fm.beginTransaction().replace(R.id.main_container, beerFragment).commit();
    }


    public void startGame() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMainFragment();
    }

    @Override
    public void onBackPressed() {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        if (stackLevel == 1)
            loadMainFragment();
        else if (stackLevel == 2)
            loadLevelFragment();
        else if (stackLevel == 3)
            loadMainFragment();
        else if (stackLevel == 4) {
            saveSharedPref();
            loadMainFragment();
        } else if (stackLevel == 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MainActivity.this);

            // set title
            alertDialogBuilder.setTitle("Exit");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Really want to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            SoundEffects.getInstance().playSound(SoundEffects.SOUND_ERROR);
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show alert dialog
            alertDialog.show();
        }
        //super.onBackPressed();
    }

    private void saveSharedPref() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("sound", settingFragment.isSound);
        editor.putBoolean("vibration", settingFragment.isVibration);
        editor.putString("playerName", settingFragment.playerName.getText().toString());
        editor.commit();

        App.setPlayerName(sharedPref.getString("playerName", "Enigma"));
        App.setSound(sharedPref.getBoolean("sound", true));
        App.setVibration(sharedPref.getBoolean("vibration", true));
    }

}
