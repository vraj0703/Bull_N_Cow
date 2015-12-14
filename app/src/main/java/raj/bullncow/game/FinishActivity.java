package raj.bullncow.game;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import raj.bullncow.App;
import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.FlatButton;
import raj.bullncow.util.SoundEffects;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {

    CTextView number, moves, movesText, message;
    FlatButton home;
    int mode, noMoves;
    boolean success;
    String noGuess;
    boolean activity_finish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_finish);
        init();
        getInfo();
        displayInfo();
    }

    private void displayInfo() {
        if (success) {
            updateHighScore();
            message.setText("Congratulations");
        } else
            message.setText("Oops");

        number.setText(noGuess);

        if (mode == App.MODE_MOVE) {
            movesText.setVisibility(View.GONE);
            moves.setVisibility(View.GONE);
        } else
            moves.setText(noMoves + "");
    }

    private void updateHighScore() {
        SQLiteDatabase db1 = openOrCreateDatabase("HighScore", MODE_PRIVATE, null);
        Cursor c;
        String table = "level" + App.getLevel();
        String score = noMoves + "";
        if (score.length() == 1)
            score = "0" + score;

        c = db1.rawQuery("Select * from " + table, null);
        c.moveToFirst();
        int k = c.getCount();
        if (k < 11) {
            k++;
            db1.execSQL("INSERT INTO " + table + " VALUES( " + k
                    + " , '" + App.getPlayerName() + "' , '" + score
                    + "' );");

        } else {
            String sc = noMoves + "";
            String max = c.getString(2);
            int j = c.getInt(0);
            while (c.moveToNext()) {
                String dbsc = c.getString(2);
                if (dbsc.compareTo(max) > 0) {
                    max = c.getString(2);
                    j = c.getInt(0);
                }
            }
            if (max.compareTo(sc) > 0) {
                db1.execSQL("DELETE FROM " + table + " WHERE id = " + j);
                db1.execSQL("INSERT INTO " + table + " VALUES( " + j
                        + " , '" + App.getPlayerName() + "' , '" + score
                        + "' );");
            }
        }

    }

    private void getInfo() {
        Bundle b = getIntent().getExtras();
        noGuess = b.getString("guess");
        success = b.getBoolean("success");
        noMoves = b.getInt("gCounter") + 1;
        mode = App.getMode();
    }

    private void init() {
        number = (CTextView) findViewById(R.id.guess_finish);
        moves = (CTextView) findViewById(R.id.guess_moves);
        message = (CTextView) findViewById(R.id.message_finish);
        movesText = (CTextView) findViewById(R.id.guess_moves_text);
        home = (FlatButton) findViewById(R.id.home_finish);

        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        switch (v.getId()) {
            case R.id.home_finish:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (activity_finish)
            finish();
    }
}
