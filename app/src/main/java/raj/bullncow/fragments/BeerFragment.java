package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.library.RippleView;

import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 7:27 PM
 */
public class BeerFragment extends Fragment {

    CTextView heading;
    private static final String TAG = "Android BillingService";
    // mService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_lots, container, false);
        heading = (CTextView) v.findViewById(R.id.heading);
        heading.setText("Buy me a Beer");
        ThisFragment thisFragment = new ThisFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lots_container, thisFragment).commit();
        return v;
    }

    public class ThisFragment extends Fragment implements RippleView.OnRippleCompleteListener,View.OnClickListener {

        RippleView can, bottle, pack, barrel;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_beer, container, false);
            can = (RippleView) v.findViewById(R.id.can);
            bottle = (RippleView) v.findViewById(R.id.bottle);
            pack = (RippleView) v.findViewById(R.id.pack);
            barrel = (RippleView) v.findViewById(R.id.barrel);

            can.setOnClickListener(this);
            bottle.setOnClickListener(this);
            pack.setOnClickListener(this);
            barrel.setOnClickListener(this);

            return v;
        }

        @Override
        public void onComplete(RippleView v) {
            switch (v.getId()) {
                case R.id.can:
                    break;
                case R.id.bottle:
                    break;
                case R.id.pack:
                    break;
                case R.id.barrel:
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        }
    }
}