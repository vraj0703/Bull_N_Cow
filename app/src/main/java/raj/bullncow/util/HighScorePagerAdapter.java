package raj.bullncow.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import raj.bullncow.fragments.ViewHighScoreFragment;

/**
 * Created by Vishal Raj on
 * 12/12/2015
 * 12:30 PM
 */


public class HighScorePagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 6;

    public HighScorePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return new ViewHighScoreFragment(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}