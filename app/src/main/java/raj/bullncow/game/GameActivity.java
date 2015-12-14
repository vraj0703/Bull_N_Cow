package raj.bullncow.game;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.jorgecastilloprz.expandablepanel.ExpandablePanelView;
import com.jorgecastilloprz.expandablepanel.listeners.ExpandableListener;

import raj.bullncow.App;
import raj.bullncow.R;
import raj.bullncow.object_package.GenNumber;
import raj.bullncow.object_package.ListArray;
import raj.bullncow.object_package.listItem;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/8/2015
 * 1:31 PM
 */
public class GameActivity extends AppCompatActivity implements ExpandableListener {

    int level;
    GenNumber genNumber;
    CTextView guessNo, bullNo, cowNo;
    ExpandablePanelView expandablePanelView;
    PrevGuessFragment prevGuessFragment;
    ListArray preG;
    FrameLayout bncLayout;
    boolean firstOpen = false;
    boolean activity_finish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        init();
    }

    private void init() {
        //set level for the game
        level = App.getLevel();
        genNumber = new GenNumber(level);
        //new CustomToast(this, genNumber.numberToString());

        FragmentManager fm = getSupportFragmentManager();
        GuessFragment guessFragment = new GuessFragment(genNumber);
        fm.beginTransaction().replace(R.id.game_container, guessFragment).commit();

        preG = new ListArray();

        guessNo = (CTextView) findViewById(R.id.guess_no);
        bullNo = (CTextView) findViewById(R.id.bull_text);
        cowNo = (CTextView) findViewById(R.id.cow_text);
        expandablePanelView = (ExpandablePanelView) findViewById(R.id.expandablePanelView);
        bncLayout = (FrameLayout) findViewById(R.id.bnc_container);

//        expandablePanelView.setBounceEnabled(true);
        expandablePanelView.attachExpandableListener(this);

    }

    public void setBullnCow(int bull, int cow, String guess) {
        guessNo.setText(guess);
        bullNo.setText(bull + "");
        cowNo.setText(cow + "");
        insertNewEntry(guess, bull, cow);
    }


    @Override
    public void onExpandingStarted() {
       // SoundEffects.getInstance().playSound(SoundEffects.SOUND_EXPAND);
        firstOpen = true;
        FragmentManager fm = getSupportFragmentManager();
        prevGuessFragment = new PrevGuessFragment(preG);
        fm.beginTransaction().replace(R.id.bnc_container, prevGuessFragment).commit();
    }

    @Override
    public void onShrinkFinished() {
       /* FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().remove(prevGuessFragment).commit();*/
    }

    public void insertNewEntry(String guess, int bull, int cow) {
        listItem new_item = new listItem(bull, cow, guess);
        if (firstOpen)
            prevGuessFragment.infoNewItemAdded(new_item);
        else
            preG.add(new_item);
    }

    public boolean alreadyPresent(String guess) {
        return preG.contain(guess);
    }

    @Override
    public void onExpandingFinished() {
    }

    @Override
    public void onShrinkStarted() {
        //SoundEffects.getInstance().playSound(SoundEffects.SOUND_EXPAND);
    }

    @Override
    public void onExpandingTouchEvent(MotionEvent motionEvent) {
    }

    @Override
    public void onBackPressed() {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                GameActivity.this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder
                .setMessage("Exit to Main page!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SoundEffects.getInstance().playSound(SoundEffects.SOUND_ERROR);
                        // if this button is clicked, close
                        // current activity
                        GameActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert dialog
        alertDialog.show();
    }

    public void setActivity_finish(boolean activity_finish) {
        this.activity_finish = activity_finish;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (activity_finish)
            finish();
    }
}
