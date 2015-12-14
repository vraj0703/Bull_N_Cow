package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import raj.bullncow.App;
import raj.bullncow.MainActivity;
import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.FlatButton;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 6:02 AM
 */
public class DifficultyFragment extends Fragment implements View.OnClickListener, NumberPicker.OnScrollListener {

    NumberPicker np;
    CTextView heading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level, container, false);
        np = (NumberPicker) v.findViewById(R.id.picker);
        heading = (CTextView) v.findViewById(R.id.level_moves);

        heading.setText("Select Difficulty and Press Continue");
        np.setMinValue(0);
        np.setMaxValue(2);
        np.setDisplayedValues(new String[]{"Easy", "Medium", "Hard"});
        np.setOnScrollListener(this);

        FlatButton fb = (FlatButton) v.findViewById(R.id.level_ok);
        fb.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        App.setDifficulty(np.getValue());
        ((MainActivity) getActivity()).startGame();

    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_SCROLL);
    }
}
