package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.library.RippleView;

import raj.bullncow.App;
import raj.bullncow.MainActivity;
import raj.bullncow.R;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 1:18 AM
 */
public class MainFragment extends Fragment implements View.OnClickListener, RippleView.OnRippleCompleteListener {

    //ImageView
    RippleView classical, moves,highscore, setting, help, about;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        classical = (RippleView) v.findViewById(R.id.classical);
        moves = (RippleView) v.findViewById(R.id.moves);
        highscore = (RippleView) v.findViewById(R.id.highscore);
        setting = (RippleView) v.findViewById(R.id.settings);
        help = (RippleView) v.findViewById(R.id.help);
        about = (RippleView) v.findViewById(R.id.about);

        classical.setOnRippleCompleteListener(this);
        moves.setOnRippleCompleteListener(this);
        help.setOnRippleCompleteListener(this);
        highscore.setOnRippleCompleteListener(this);
        setting.setOnRippleCompleteListener(this);
        about.setOnRippleCompleteListener(this);

        classical.setOnClickListener(this);
        moves.setOnClickListener(this);
        highscore.setOnClickListener(this);
        help.setOnClickListener(this);
        setting.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_SWIPE);
    }

    @Override
    public void onComplete(RippleView v) {
        MainActivity ma = ((MainActivity) getActivity());
        switch (v.getId()) {
            case R.id.classical:
                App.setMode(App.MODE_CLASSICAL);
                ma.loadLevelFragment();
                break;
            case R.id.moves:
                App.setMode(App.MODE_MOVE);
                ma.loadLevelFragment();
                break;
            case R.id.help:
                ma.loadHelpFragment();
                break;
            case R.id.highscore:
                ma.loadHighScoreFragment();
                break;
            case R.id.settings:
                ma.loadSettingFragment();
                break;
            case R.id.about:
                ma.loadAboutFragment();
                break;
        }
    }
}
