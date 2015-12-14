package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raj.bullncow.R;
import raj.bullncow.util.CTextView;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 3:53 AM
 */
public class HelpFragment extends Fragment {
    CTextView heading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_lots, container, false);
        heading = (CTextView) v.findViewById(R.id.heading);
        heading.setText("Help");
        ThisFragment thisFragment = new ThisFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lots_container, thisFragment).commit();
        return v;
    }

    public class ThisFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_help, container, false);
            return v;
        }
    }
}
