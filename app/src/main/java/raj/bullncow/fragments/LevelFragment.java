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
 * 12/8/2015
 * 1:09 PM
 */
public class LevelFragment extends Fragment implements View.OnClickListener, NumberPicker.OnScrollListener {

    NumberPicker np;
    CTextView heading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level, container, false);
        np = (NumberPicker) v.findViewById(R.id.picker);
        heading = (CTextView) v.findViewById(R.id.level_moves);

        heading.setText("Select Level and Press Continue");

        np.setMinValue(4);
        np.setMaxValue(9);
        np.setOnScrollListener(this);

        FlatButton fb = (FlatButton) v.findViewById(R.id.level_ok);
        fb.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        if (App.getMode() == App.MODE_CLASSICAL) {
            App.setLevel(np.getValue());
            //new CustomToast(getActivity(), np.getValue() + "");
            ((MainActivity) getActivity()).startGame();
        } else {
            App.setLevel(np.getValue());
            ((MainActivity) getActivity()).loadDifficultyFragment();
        }
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_SCROLL);
    }
}
