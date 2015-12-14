package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.relex.circleindicator.CircleIndicator;
import raj.bullncow.R;
import raj.bullncow.util.HighScorePagerAdapter;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 3:53 AM
 */
public class HighScoreFragment extends Fragment implements ViewPager.OnPageChangeListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        viewpager.setAdapter(new HighScorePagerAdapter(getActivity().getSupportFragmentManager()));
        indicator.setViewPager(viewpager);
        viewpager.setOnPageChangeListener(this);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        SoundEffects.getInstance().playSound(SoundEffects.SOUND_SWIPE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
