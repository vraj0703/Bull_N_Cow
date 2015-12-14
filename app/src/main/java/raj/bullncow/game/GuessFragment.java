package raj.bullncow.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Arrays;

import raj.bullncow.App;
import raj.bullncow.R;
import raj.bullncow.object_package.GenNumber;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.CustomToast;
import raj.bullncow.util.FlatButton;
import raj.bullncow.util.SoundEffects;
import raj.bullncow.util.Vibration;

/**
 * Created by Vishal Raj on
 * 12/8/2015
 * 2:28 PM
 */
public class GuessFragment extends Fragment implements View.OnClickListener {

    FlatButton check;
    EditText guess;
    CTextView noOfGuesses, turnLeftText, turnLeft, giveUp;
    GenNumber genNumber;
    int level;
    int guessCounter = 0;
    boolean modeMoves = false;
    int movesLeft;

    public GuessFragment() {
    }

    public GuessFragment(GenNumber genNumber) {
        this.genNumber = genNumber;
        level = App.getLevel();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guess, container, false);
        check = (FlatButton) v.findViewById(R.id.guess_ok);
        guess = (EditText) v.findViewById(R.id.guess);
        noOfGuesses = (CTextView) v.findViewById(R.id.no_of_guess);
        turnLeft = (CTextView) v.findViewById(R.id.turn_left);
        turnLeftText = (CTextView) v.findViewById(R.id.turn_left_text);
        giveUp = (CTextView) v.findViewById(R.id.give_up);

        setVisibility();

        check.setOnClickListener(this);
        giveUp.setOnClickListener(this);

        guess.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_ENTER) { //Whenever you got user click enter. Get text in edittext and check it equal test1. If it's true do your code in listenerevent of button3
                    String k = guess.getText().toString();
                    if (!noError(k)) {
                        displayBullnCow(k);
                    }
                }
                return false;
            }
        });

        return v;
    }

    private void setVisibility() {
        if (App.getMode() == App.MODE_CLASSICAL)
            giveUp.setVisibility(View.VISIBLE);
        else {
            turnLeft.setVisibility(View.VISIBLE);
            turnLeftText.setVisibility(View.VISIBLE);
            modeMoves = true;
            movesLeft = App.getMoves();
            turnLeft.setText(movesLeft + "");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.guess_ok) {
            String k = guess.getText().toString();
            if (!noError(k)) {
                displayBullnCow(k);
                SoundEffects.getInstance().playSound(SoundEffects.SOUND_YES);
            } else
                SoundEffects.getInstance().playSound(SoundEffects.SOUND_ERROR);
        } else if (v.getId() == R.id.give_up)
            startFinishActivity(genNumber.getNumber_String(), false);
    }

    private void displayBullnCow(String k) {
        int bull = genNumber.getBull(k);
        int cow = genNumber.getCow(k);
        if (bull == App.getLevel()) {
            startFinishActivity(k, true);
        } else if (modeMoves && movesLeft == 1) {
            startFinishActivity(genNumber.getNumber_String(), false);
        }
        ((GameActivity) getActivity()).setBullnCow(bull, cow, k);
        guessCounter++;
        noOfGuesses.setText(guessCounter + "");
        if (modeMoves) {
            movesLeft--;
            turnLeft.setText(movesLeft + "");
        }
    }

    private void startFinishActivity(String k, boolean success) {
        playSoundVibration(success);
        ((GameActivity) getActivity()).setActivity_finish(true);
        Intent i = new Intent(getActivity(), FinishActivity.class);
        Bundle b = new Bundle();
        b.putString("guess", k);
        b.putInt("gCounter", guessCounter);
        b.putBoolean("success", success);
        i.putExtras(b);
        getActivity().startActivity(i);
    }

    private void playSoundVibration(boolean success) {
        if (success) {
            long[] v = {200, 100, 200, 275, 425, 100, 200, 100, 200, 275, 425, 100, 75, 25, 75, 125, 75, 25, 75, 125, 100, 100};
            Vibration.vibrate(getActivity(), v, -1);
            SoundEffects.getInstance().playSound(SoundEffects.SOUND_WIN);
        } else {
            long[] v = {500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500};
            Vibration.vibrate(getActivity(), v, -1);
            SoundEffects.getInstance().playSound(SoundEffects.SOUND_LOSE);
        }
    }

    private boolean noError(String k) {
        if (k.length() < App.getLevel()) {
            new CustomToast(getActivity(), "Guess is too short");
            return true;
        } else if (k.length() > App.getLevel()) {
            new CustomToast(getActivity(), "Guess is too long");
            return true;
        } else if (hasDuplicate(k)) {
            new CustomToast(getActivity(), "No Repetition, you know the rule");
            return true;
        } else if (hasZero(k)) {
            new CustomToast(getActivity(), "No zero, this is not your answer sheet");
            return true;
        } else if (((GameActivity) getActivity()).alreadyPresent(k)) {
            new CustomToast(getActivity(), "Come up with new number");
            return true;
        }
        return false;
    }

    private boolean hasDuplicate(String k) {
        int[] hash = new int[10];
        Arrays.fill(hash, 0);
        for (int i = 0; i < level; i++) {
            if (hash[Integer.parseInt(k.charAt(i) + "")] == 1)
                return true;
            else
                hash[Integer.parseInt(k.charAt(i) + "")] = 1;
        }
        return false;
    }

    private boolean hasZero(String k) {
        for (int i = 0; i < level; i++) {
            if (Integer.parseInt(k.charAt(i) + "") == 0)
                return true;
        }
        return false;
    }
}
